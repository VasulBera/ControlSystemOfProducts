package com.softserveinc.trainee.entity;

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


    /*NVARCHAR(){

        public String toString(){
            return "NVARCHAR(" + nvarcarSize + ")";
        }
    };

    private static int nvarcarSize = 45;

    public void setSize(int size){
        nvarcarSize = size;
    }*/
}
