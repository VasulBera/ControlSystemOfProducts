package com.softserveinc.trainee.applicationConfig;

import com.softserveinc.trainee.entity.metadata.Field;

import java.util.Comparator;

public class FieldComparator implements Comparator<Field> {


    @Override
    public int compare(Field fieldFirst, Field fieldSecond) {
        if(fieldFirst.getCreatedDate().getNanos() > fieldSecond.getCreatedDate().getNanos()){
            return 1;
        }else if(fieldFirst.getCreatedDate().getNanos() < fieldSecond.getCreatedDate().getNanos()){
            return -1;
        }
        return 0;
    }
}
