package com.softserveinc.trainee.customObject;

import java.util.ArrayList;
import java.util.List;

public class GenericTableRow {

    private List<Object> columns;

    public GenericTableRow() {
        columns = new ArrayList<>();
    }

    public List<Object> getColumns() {
        return columns;
    }

    public void setColumns(List<Object> columns) {
        this.columns = columns;
    }
}
