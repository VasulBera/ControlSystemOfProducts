package com.softserveinc.trainee.customObject;

import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenericTableRowGenerator {


    public Line extractGenericTableRowFromRs(Entity entity, ResultSet rs) throws SQLException, IllegalAccessException {
        Line line = new Line();
        java.lang.reflect.Field[] fields = FieldUtils.getAllFields(Line.class);
        for(Field field: entity.getFieldList()){
            Object value = rs.getObject(field.getColumnName());
            for(java.lang.reflect.Field rowField: fields){
                rowField.setAccessible(true);
                if(rowField.get(line) == null){
                    rowField.set(line, value);
                    break;
                }
            }
        }
        return line;
    }



/*
    public GenericTableRow extractGenericTableRowFromRs(Entity entity, ResultSet rs) throws SQLException, IllegalAccessException {
        java.lang.reflect.Field[] fields = FieldUtils.getAllFields(GenericTableRow.class);
        GenericTableRow row = new GenericTableRow();
        for(Field field: entity.getFieldList()){
            Object o = rs.getObject(field.getColumnName());
            for(java.lang.reflect.Field rowField: fields){
                rowField.setAccessible(true);
                if(o.getClass().equals(rowField.getType()) && rowField.get(row) == null){
                    rowField.set(row, o);
                    break;
                }
            }
        }
        return row;
    }
*/
    public List<Line> getGenericTableRowList(Entity entity, ResultSet rs) throws SQLException, IllegalAccessException {
        List<Line> list = new ArrayList();
        //while(rs.next()){
            list.add(extractGenericTableRowFromRs(entity, rs));
        //}
        return list;
    }
}
