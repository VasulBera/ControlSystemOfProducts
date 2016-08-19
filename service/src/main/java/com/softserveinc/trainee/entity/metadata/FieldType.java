package com.softserveinc.trainee.entity.metadata;

/**
 * Created by vberv on 7/13/2016.
 */
public enum FieldType {
    BIT, INT, FLOAT, DATE, NVARCHAR;
    private final int value;

    FieldType(int value) {
        this.value = value;
    }
    FieldType() {
        value = 0;
    }

}
