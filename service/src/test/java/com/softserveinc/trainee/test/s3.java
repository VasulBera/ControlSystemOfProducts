package com.softserveinc.trainee.test;

import com.google.gson.Gson;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class s3 {

    private String name;
    private int a = 9;

    public void test(){
        List<List<String>> data = new ArrayList();

        for(int i = 0; i < 10; i++){
            List<String> row = new ArrayList(Arrays.asList("color", "price", "produsing_country"));
            data.add(row);
        }

        Gson gson = new Gson();
        String json = gson.toJson(data);
        List<List<String>> back = gson.fromJson(json, List.class);
        System.out.println(back);
    }

    @Test
    public void testField() throws IllegalAccessException {
        s3 s = new s3();
        Field[] fields = s.getClass().getDeclaredFields();
        for(Field field: fields){
            /*if(field.getClass().equals(String.class)){
                System.out.println("String");
            }*/
            if(field.get(s) == null){
                field.set(s, "sdfsfadsf");
            }
        }

        System.out.println(s.a);
        System.out.println(s.name);
    }
}
