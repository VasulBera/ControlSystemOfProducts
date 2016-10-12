package com.softserveinc.trainee.generic;

import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowCountCallbackHandler implements RowCallbackHandler {

    private int count;

    private static final int NUMBER_OF_COUNT_COLUMN = 1;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        count = rs.getInt(NUMBER_OF_COUNT_COLUMN);
    }
}
