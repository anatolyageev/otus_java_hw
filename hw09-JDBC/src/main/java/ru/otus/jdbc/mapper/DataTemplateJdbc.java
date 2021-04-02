package ru.otus.jdbc.mapper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ru.otus.repository.DataTemplate;
import ru.otus.repository.executor.DbExecutor;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(),id,this::createObjectFromResultSet);
    }

    @Override
    public List<T> findAll(Connection connection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long insert(Connection connection, T client) {
         return  dbExecutor.executeStatement(connection,entitySQLMetaData.getInsertSql(),getFieldValuesList(client));
    }

    @Override
    public void update(Connection connection, T client) {
        throw new UnsupportedOperationException();
    }

    private List<Object> getFieldValuesList(T objectData) {
        var params = new ArrayList<>();
        for (var filed : entityClassMetaData.getFieldsWithoutId()) {
            filed.setAccessible(true);
            try {
                params.add(filed.get(objectData));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

    private T createObjectFromResultSet(ResultSet rs)  {

        try {

            final T instance = entityClassMetaData.getConstructor().newInstance();

            var idField = entityClassMetaData.getIdField();
            idField.setAccessible(true);
            idField.set(instance, rs.getObject(idField.getName()));

            for (var filed : entityClassMetaData.getFieldsWithoutId()) {
                filed.setAccessible(true);
                filed.set(instance, rs.getObject(filed.getName()));
            }

            return instance;
        } catch (IllegalAccessException | InstantiationException | SQLException | InvocationTargetException e) {
           throw  new  JdbcMapperException ("Object cannot be created");
        }
    }
}
