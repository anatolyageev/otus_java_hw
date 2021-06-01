package ru.otus.appcontainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.function.Function;
import java.util.stream.Stream;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.util.*;


import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?>... initialConfigClasses) {
        var configList = Arrays.stream(initialConfigClasses)
                .filter(Objects::nonNull)
                .collect(toList());
        if (configList.isEmpty()) {
            throw new IllegalArgumentException("App should contain at least 1 configuration class");
        }
        processConfigList(configList);
    }

    public AppComponentsContainerImpl(String packageToScan) {
        this(new Reflections(packageToScan)
                .getTypesAnnotatedWith(AppComponentsContainerConfig.class)
                .toArray(new Class<?>[0]));
    }

    private void processConfigList(List<Class<?>> configClasses) {
        configClasses.forEach(this::checkConfigClass);
        var configMap = configClasses.stream()
                .collect(toMap(Function.identity(), this::create));

        configClasses.stream()
                .sorted(Comparator
                        .comparingInt(aClass -> aClass.getAnnotation(AppComponentsContainerConfig.class)
                        .order()))
                .forEachOrdered(aClass -> processConfig(configMap.get(aClass), aClass));
    }

    private Object create(Class<?> configClass) {
        try {
            return configClass.getConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new IllegalArgumentException(String.format("Class %s don't contained default constructor ", configClass.getName()));
        }
    }

    private void processConfig(Object config, Class<?> configClass) {
        // You code here...
        ReflectionUtils
                .getMethods(configClass, method -> method.isAnnotationPresent(AppComponent.class)).stream()
                .sorted(Comparator.comparingInt(method -> method.getAnnotation(AppComponent.class).order()))
                .forEachOrdered(method -> createComponent(method, config));

    }

    private void createComponent(Method method, Object config) {
        final List<Object> params = Stream.of(method.getParameters())
                .map(Parameter::getType)
                .map(this::getAppComponent)
                .filter(Objects::nonNull)
                .collect(toList());
        if (params.size() != method.getParameters().length)
            throw new IllegalArgumentException(String.format("Unable to create component %s. Dependencies are missing", method.getReturnType().getName()));
        var component = invoke(method, config, params);
        appComponents.add(component);
        appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), component);
    }

    private static Object invoke(Method method, Object object, List<Object> params) {
        try {
            return method.invoke(object, params.toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(String.format("Unable to create component %s. Instantiation error", method.getReturnType().getName()), e);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) appComponents.stream()
                .filter(item -> componentClass.isAssignableFrom(item.getClass()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Component not found - " + componentClass));
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
