package com.softserveinc.trainee.customObject;

import com.softserveinc.trainee.entity.metadata.Entity;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenericTableRowCallbackHandler implements RowCallbackHandler {

    private Entity entity;

    private List<Line> genericTableRows;

    private GenericTableRowGenerator rowGenerator;

    public GenericTableRowCallbackHandler(Entity entity){
        this.entity = entity;
        genericTableRows = new ArrayList();
        rowGenerator = new GenericTableRowGenerator();
    }

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        try {
            genericTableRows = rowGenerator.getGenericTableRowList(entity ,rs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public List<Line> getGenericTableRows(){
        return genericTableRows;
    }
}
