@isTest
public class TestEditEntityController {
    
    @isTest static void testCreateField(){
        Entity__c entity = new Entity__c( Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK');
        insert entity;
        apexpages.currentpage().getparameters().put('entityID' , entity.Id);
        EditEntityController editEntityController = new EditEntityController();
        List<Field__c> fields = new List<Field__c>();
        Integer fakeId = 1;
        
        Test.startTest();
        editEntityController.createField();
        Test.stopTest();
        
        List<Field__c> expected = new List<Field__c>();
        Field__c field = new Field__c(Entity_id__c = entity.Id, Id__c = String.valueOf(fakeId), Field_Type__c = 'NVARCHAR');
        expected.add(field);
        List<Field__c> actual = editEntityController.fields;
        System.assertEquals(expected, actual);
    }
    
    @isTest static void testCancle(){
        Entity__c entity = new Entity__c( Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK');
        insert entity;
        apexpages.currentpage().getparameters().put('entityID' , entity.Id);
        EditEntityController editEntityController = new EditEntityController();
        String entityId = entity.Id;
        editEntityController.entity = new Entity__c( Name__c = 'Bar', Schema_Name__c = 'BAR', Table_Name__c = 'TABLE_BAR');
        editEntityController.entityId = entityId;
        
        Test.startTest();
        editEntityController.cancle();
        Test.stopTest();
        
        Entity__c expected = [SELECT Id__c, Name__c, Schema_Name__c, Table_Name__c FROM Entity__c WHERE Id =: entityId];
        Entity__c actual = editEntityController.entity;
        System.assertEquals(expected, actual);
    }
    
    @isTest static void testDeleteField(){
       Entity__c entity = new Entity__c( Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK');
       insert entity;
       apexpages.currentpage().getparameters().put('entityID' , entity.Id);
       EditEntityController editEntityController = new EditEntityController();
       Field__c field = new Field__c(Name__c = 'testDate', Column_Name__c = 'TESTDATE', Field_Type__c = 'NVARCHAR', Length__c = 45, Entity_id__c = entity.Id, Id__c = 'TESTDATE');
       insert field;
       List<Field__c> fields = new List<Field__c>();
       fields.add(field); 
       editEntityController.fieldId = field.Id__c;
       editEntityController.fields = fields;
        
       Test.startTest(); 
       editEntityController.deleteField();
       Test.stopTest(); 
        
       List<Field__c> expected = new List<Field__c>();
       List<Field__c> actual =  editEntityController.fields;
       System.assertEquals(expected, actual); 
    }
    
    @isTest static void testSaveUpdateEntity(){
       Entity__c entity = new Entity__c(Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK');
       insert entity;
       entity.Name__c = 'Bar';
       entity.Schema_Name__c = 'BAR';
       entity.Table_Name__c = 'TABLE_BAR';
  	   apexpages.currentpage().getparameters().put('entityID' , entity.Id);
       EditEntityController editEntityController = new EditEntityController();
       editEntityController.entity = entity;
       
       Test.startTest();
       editEntityController.save(); 
       Test.stopTest();
        
       Entity__c expected = entity;
       Entity__c actual = [SELECT Name__c, Schema_Name__c, Table_Name__c FROM Entity__c WHERE Id =: entity.Id];
       System.assertEquals(expected, actual); 
    }
    
    @isTest static void testSaveField(){
        Entity__c entity = new Entity__c(Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK');
        insert entity; 
        Field__c field = new Field__c(Name__c = 'date', Column_Name__c = 'Date', Field_Type__c = 'NVARCHAR', Length__c = 45, Entity_id__c = entity.Id);
        List<Field__C> fields = new List<Field__c>();
        fields.add(field);
        apexpages.currentpage().getparameters().put('entityID' , entity.Id); 
        EditEntityController editEntityController = new EditEntityController();
        editEntityController.entity = entity;    
        editEntityController.fields = fields;
       
        Test.startTest();
        editEntityController.save();
        Test.stopTest();
        
        Field__c expected = field;
        Field__c actual = [SELECT Name__c, Column_Name__c, Field_Type__c, Length__c, Entity_id__c, Id__c FROM Field__c WHERE Id =: field.Id];
        System.assertEquals(expected, actual);
    }
    
    @isTest static void testSaveUpdateField(){
        Entity__c entity = new Entity__c(Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK');
        insert entity; 
        Field__c field = new Field__c(Name__c = 'date', Column_Name__c = 'Date', Field_Type__c = 'NVARCHAR', Length__c = 45, Entity_id__c = entity.Id, Id__c = 'TESTDATE');
        insert field;
        field.Name__c = 'changedDate';
        field.Column_Name__c = 'changedDate';
        field.Length__c = 23;
        List<Field__C> fields = new List<Field__c>();
        fields.add(field);
        apexpages.currentpage().getparameters().put('entityID' , entity.Id); 
        EditEntityController editEntityController = new EditEntityController();
        editEntityController.entity = entity;    
        editEntityController.fields = fields;
        
        Test.startTest();
        editEntityController.save();
        Test.stopTest();
        
        Field__c expected = field;
        Field__c actual = [SELECT Name__c, Column_Name__c, Field_Type__c, Length__c, Entity_id__c, Id__c FROM Field__c WHERE Id =: field.Id];
        System.assertEquals(expected, actual);
    }
    
    @isTest static void testSaveGetException(){
        Entity__c entity = new Entity__c(Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK');
        insert entity;
        entity.Schema_Name__c = 'BARVINOK+';
        apexpages.currentpage().getparameters().put('entityID' , entity.Id); 
        EditEntityController editEntityController = new EditEntityController();
        editEntityController.entity = entity;
        
        Test.startTest();
        editEntityController.save();
        Test.stopTest();
        
        String expected = 'Field \'Schema name\' can include just leters and numbers';
        String actual = ApexPages.getMessages().get(0).getDetail();
        System.assertEquals(expected, actual);
    }

}