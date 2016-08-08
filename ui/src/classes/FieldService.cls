public class FieldService {
    public static void setFieldId(List<Field__c> fields){
        Entity__c entityDb = [
            SELECT Name__c, Schema_Name__c, Table_Name__c, Id__c 
            FROM Entity__c 
            WHERE Id =: fields.get(0).Entity_id__c
        ];
        for(Field__c field: fields){
            field.Id__c = (entityDb.Id__c + + field.Column_Name__c).toUpperCase();
        }
    }
    
    public static void insertFieldIntoRemoteService(List<Field__c> fields){
        Entity__c entityDb = [
            SELECT Name__c, Schema_Name__c, Table_Name__c, Id__c, (SELECT Id__c, Name__c, Field_Type__c, Length__c, Column_Name__c, Entity_id__c FROM Fields__r)
            FROM Entity__c 
            WHERE Id =: fields.get(0).Entity_id__c
        ];
        EntityDto entity = new EntityDto(entityDb);
        String data = JSON.serialize(entity);
        HttpService.updateData(data);
    }
    
    public static void updateFieldIntoRemoteService(List<Field__c> fields){
        Entity__c entityDb = [
            SELECT Name__c, Schema_Name__c, Table_Name__c, Id__c, (SELECT Id__c, Name__c, Field_Type__c, Length__c, Column_Name__c, Entity_id__c FROM Fields__r)
            FROM Entity__c 
            WHERE Id =: fields.get(0).Entity_id__c];
        EntityDto entity = new EntityDto(entityDb);
        String id = entity.id;
        String data = JSON.serialize(entity);
        HttpService.patchData(id, data);
    }
    
    public static void deleteFieldIntoRemoteService(List<Field__c> fields){
        Entity__c entityDb = [
            SELECT Name__c, Schema_Name__c, Table_Name__c, Id__c, (SELECT Id__c, Name__c, Field_Type__c, Length__c, Column_Name__c, Entity_id__c FROM Fields__r)
            FROM Entity__c 
            WHERE Id =: fields.get(0).Entity_id__c
        ];
        EntityDto entity = new EntityDto(entityDb);
        String data = JSON.serialize(entity);
        HttpService.updateData(data);
    }
}