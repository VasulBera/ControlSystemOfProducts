package com.softserveinc.trainee.test;


import com.softserveinc.trainee.applicationConfig.FieldComparator;
import com.softserveinc.trainee.entity.metadata.Field;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;

public class TestFieldComparator {

    FieldComparator fieldComparator = new FieldComparator();

    @Test
    public void testCompareTheSameField(){
        Timestamp timestamp = new Timestamp(123456789);
        Field fieldFirst = new Field();
        fieldFirst.setCreatedDate(timestamp);

        Field fieldSecond = new Field();
        fieldSecond.setCreatedDate(timestamp);
        int expected = 0;
        int actually = fieldComparator.compare(fieldFirst, fieldSecond);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testCompareFirstFieldBigger(){
        Timestamp timestamp = new Timestamp(123456789);
        Timestamp timestampSecond = new Timestamp(123456788);
        Field fieldFirst = new Field();
        fieldFirst.setCreatedDate(timestamp);

        Field fieldSecond = new Field();
        fieldSecond.setCreatedDate(timestampSecond);
        int expected = 1;
        int actually = fieldComparator.compare(fieldFirst, fieldSecond);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testCompareSecondFieldBigger(){
        Timestamp timestamp = new Timestamp(123456787);
        Timestamp timestampSecond = new Timestamp(123456788);
        Field fieldFirst = new Field();
        fieldFirst.setCreatedDate(timestamp);

        Field fieldSecond = new Field();
        fieldSecond.setCreatedDate(timestampSecond);
        int expected = -1;
        int actually = fieldComparator.compare(fieldFirst, fieldSecond);
        Assert.assertEquals(expected, actually);
    }


}
