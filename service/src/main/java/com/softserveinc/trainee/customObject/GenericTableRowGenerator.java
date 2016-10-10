package com.softserveinc.trainee.customObject;

import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenericTableRowGenerator {

    public GenericTableRow extractGenericTableRowFromRs(Entity entity, ResultSet rs) throws SQLException, IllegalAccessException {
        GenericTableRow tableRow = new GenericTableRow();
        List<Object> elements = new ArrayList();
        for(Field field: entity.getFieldList()){
            Object value = rs.getObject(field.getColumnName());
            elements.add(value);
        }
        tableRow.setColumns(elements);
        return tableRow;
    }

    public List<GenericTableRow> getGenericTableRowList(Entity entity, ResultSet rs) throws SQLException, IllegalAccessException {
        List<GenericTableRow> tableRows = new ArrayList();
        tableRows.add(extractGenericTableRowFromRs(entity, rs));
        while(rs.next()){
            tableRows.add(extractGenericTableRowFromRs(entity, rs));
        }
        return tableRows;
    }
}
