package ru.otus.jdbc.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import ru.otus.model.Id;


import static java.util.Objects.requireNonNull;
import static ru.otus.utils.ReflectionHelper.getDefaultConstruct;
import static ru.otus.utils.ReflectionHelper.getFieldWithAnnotation;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final Class<T> classMeta;
    private final Field idField;
    private final Constructor constructor;
    private final List<Field> declaredFields;

    public EntityClassMetaDataImpl(Class<T> classMeta){
        this.classMeta = classMeta;
        this.idField = requireNonNull(getFieldWithAnnotation(Id.class,classMeta),"Class should contained Id field");
        this.constructor = requireNonNull(getDefaultConstruct(classMeta),"Class should contained default constructor");
        this.declaredFields = Arrays.asList(classMeta.getDeclaredFields());
    }

    @Override
    public String getName() {
        return classMeta.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        return (Constructor<T>) constructor;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return declaredFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return declaredFields
                .stream()
                .filter(field -> !field.equals(idField))
                .collect(Collectors.toList());
    }
}
