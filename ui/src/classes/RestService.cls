@RestResource(urlMapping='/entity/*')
global with sharing class RestService {
    
    @HttpGet
    global static void setRequestJodDone(){
       Entity entity = new Entity();
        entity.name = 'clientTable';
        entity.tableName = 'PRODUCT';
        entity.schemaName = 'client';
        entity.fullUploadData = false;
        List<Field> fieldList = new List<Field>();
        for(Integer i = 1; i < 9; i++){
            Field field1 = new Field();
            field1.name = 'name' + i;
            field1.ColumnName = 'NAME' + i;
            field1.type = 'NVARCHAR';
            field1.length = 100;

            Field field2 = new Field();
            field2.name = 'quantity' + i;
            field2.ColumnName = 'QUANTITY' + i;
            field2.type = 'INT';
            field2.length = 0;

            Field field3 = new Field();
            field3.name  = 'isdiscount' + i;
            field3.ColumnName = 'ISDISCOUNT' + i;
            field3.type = 'BIT';
            field3.length = 0;

            Field field4 = new Field();
            field4.name = 'price' + i;
            field4.ColumnName = 'PRICE' + i;
            field4.type = 'FLOAT';
            field4.length = 0;

            fieldList.add(field1);
            fieldList.add(field2);
            fieldList.add(field3);
            fieldList.add(field4);
        }
        entity.fieldList = fieldList;
        String data = JSON.serialize(entity);
        HttpService.insertData(data);
    }
}