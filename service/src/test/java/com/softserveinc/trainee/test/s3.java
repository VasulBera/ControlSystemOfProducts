package com.softserveinc.trainee.test;

import com.softserveinc.trainee.entity.metadata.Entity;
import org.junit.Test;

public class s3 {


    @Test
    public void test(){
        Entity entity = new Entity();
        entity.setTableName("sdf");

        System.out.println(entity.getFileName());
    }
}
