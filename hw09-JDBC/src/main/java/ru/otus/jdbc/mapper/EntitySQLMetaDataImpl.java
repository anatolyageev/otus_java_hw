package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {
    private final EntityClassMetaData<?> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<?> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }


    @Override
    public String getSelectAllSql() {
        return null;
    }

    @Override
    public String getSelectByIdSql() {
        return String.format("select id, %s from %s where %s = ?"
                , getFieldsString()
                , entityClassMetaData.getName().toLowerCase()
                , entityClassMetaData.getIdField().getName()
        );
    }

    @Override
    public String getInsertSql() {
        return String.format("insert into %s (%s) values (%s)",
                entityClassMetaData.getName().toLowerCase(),
                getFieldsString(),
                entityClassMetaData.getFieldsWithoutId().stream()
                        .map(v -> "?")
                        .collect(Collectors.joining(","))
        );
    }

    private String getFieldsString() {
        return entityClassMetaData.getFieldsWithoutId().stream()
                .map(Field::getName)
                .collect(Collectors.joining(","))
                .toLowerCase();
    }

    @Override
    public String getUpdateSql() {
        return null;
    }
}
