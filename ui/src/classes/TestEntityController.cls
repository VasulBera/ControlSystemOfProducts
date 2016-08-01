@isTest
public class TestEntityController {
    
    @isTest static void testShow(){
        EntityController entityController = new EntityController();
        entityController.show();
        Boolean expected = true;
        Boolean actual = entityController.showSection;
        System.assertEquals(expected, actual);
        entityController.show();
        Boolean expected1 = false;
        Boolean actual1 = entityController.showSection;
        System.assertEquals(expected1, actual1);
    }
    
    @isTest static void testCreateField(){
        EntityController entityController = new EntityController();
        Integer fieldId = 1;
        List<Field__c> expected = new List<Field__c>();
        expected.add(new Field__c(Id__c = String.valueOf(fieldId), Field_Type__c = 'NVARCHAR'));
        entityController.fakeId = fieldId;
        
        Test.startTest();
        entityController.createField();
        Test.stopTest();
        
        List<Field__c> actual = entityController.createdFields;
        System.assertEquals(expected, actual);
    }
    
    @isTest static void testDeleteField(){
        EntityController entityController = new EntityController();
        Integer fieldId = 1;
        List<Field__c> fields = new List<Field__c>();
        fields.add(new Field__c(Id__c = String.valueOf(fieldId)));
        entityController.fieldId = String.valueOf(fieldId);
        entityController.createdFields = fields;
        
        Test.startTest();
        entityController.deleteField();
        Test.stopTest();
        
        List<Field__c> expected = new List<Field__c>();
        List<Field__c> actual =  entityController.createdFields;
        System.assertEquals(expected, actual);
    }
    
    @isTest static void testDeleteEntity(){
        EntityController entityController = new EntityController();
        Entity__c entity = new Entity__c( Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK' );
        insert entity;
        List<Entity__c> entities = new List<Entity__c>();
        entities.add(entity);
        String entityId = entity.Id;
        entityController.entities = entities;
        entityController.entityId = entityId;
        
        Test.startTest();
        entityController.deleteEntity();
        Test.stopTest();
        
        List<Entity__c> expected = new List<Entity__c>();
        List<Entity__c> actual = entityController.entities;
        System.assertEquals(expected, actual);
    }
    
    @isTest static void testCancelSavingEntity(){
       EntityController entityController = new EntityController();
       Entity__c entity = new Entity__c( Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK' );
       Field__c field = new Field__c(Name__c = 'date', Column_Name__c = 'Date', Field_Type__c = 'NVARCHAR'); 
       List<Field__c> fields = new List<Field__c>();
       fields.add(field);
       entityController.createdFields = fields;
       entityController.createdEntity = entity;
        
       Test.startTest(); 
       entityController.cancelSavingEntity();
       Test.stopTest();
        
       Entity__c expectedEntity = new Entity__c();
       List<Field__c> expectedFields = new List<Field__c>();
       Entity__c actualEntity = entityController.createdEntity;
       List<Field__c> actualFields = entityController.createdFields;
       System.assertEquals(expectedEntity, actualEntity);
       System.assertEquals(expectedFields, actualFields);
    }
     
    @isTest static void testGetEntityEditPage(){
       EntityController entityController = new EntityController();
       String entityId = '2';
       entityController.entityId = entityId; 
       PageReference redirectEx = new PageReference('/apex/Entity_Edit');
       redirectEx.getParameters().put('entityID', entityId);
        
       Test.startTest();
       PageReference redirectAc = entityController.getEntityEditPage();
       Test.stopTest();
        
       String expected = redirectEx.getUrl();
       String actual =  redirectAc.getUrl();   
       System.assertEquals(expected, actual);
    }
    
    @isTest static void testSaveEntity(){
        EntityController entityController = new EntityController();
        Entity__c createdEntity = new Entity__c( Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK' );
        List<Field__c> createdFields = new List<field__c>();
        Field__c field = new Field__c(Name__c = 'testDate', Column_Name__c = 'TESTDATE', Field_Type__c = 'NVARCHAR', Length__c = 45);
        createdFields.add(field);
        String entityId = createdEntity.Id__c;
        entityController.entityId = entityId;
        entityController.createdEntity = createdEntity;
        entityController.createdFields = createdFields;
        
        Test.startTest();
        entityController.saveEntity();
        Test.stopTest();
        
        Entity__c expectedEntity = new Entity__c();
        Entity__c actualEntity = entityController.createdEntity;
        System.assertEquals(expectedEntity, actualEntity);
        List<Field__c> expectedFields = new List<Field__c>();
        List<Field__c> actualFields = entityController.createdFields;
        System.assertEquals(expectedFields, actualFields);
    }
    
    @isTest static void testSaveEntityGetException(){
       EntityController entityController = new EntityController();
       Entity__c createdEntity = new Entity__c( Name__c = 'Barvinok', Schema_Name__c = 'BARVINOK', Table_Name__c = 'TABLE_BARVINOK' );
        insert createdEntity;
        entityController.createdEntity = createdEntity;
        
        Test.startTest();
        entityController.saveEntity();
        Test.stopTest();
        
        String expected = 'cannot specify Id in an insert call';
        String actual = ApexPages.getMessages().get(0).getDetail();
        System.assertEquals(expected, actual);
    }
}