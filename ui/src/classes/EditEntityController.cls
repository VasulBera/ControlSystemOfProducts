public class EditEntityController {

    public String entityId{get; set;}
    public Entity__c entity{get; set;}
    public List<Field__c> fields{get; set;}
    public String fieldId{get; set;}
    public Integer fakeId{get; set;}
    
    public EditEntityController(){
        entityId = apexpages.currentpage().getparameters().get('entityID');
        entity = [SELECT Id__c, Name__c, Schema_Name__c, Table_Name__c FROM Entity__c WHERE Id =: entityId];
        fields = [SELECT Id__c, Name__c, Field_Type__c, Length__c, Column_Name__c, Entity_id__c FROM Field__c WHERE Entity_id__c =: entity.Id];
        fakeId = 1;
    }
    
    public void createField(){
        Field__c field = new Field__c(Entity_id__c = entity.Id, Id__c = String.valueOf(fakeId++), Field_Type__c = 'NVARCHAR');
        System.debug('entity.Id__c ->' + entity.Id__c);
        System.debug('entity.Id ->' + entity.Id);
        fields.add(field);
    }
    
    public void save(){
        List<Field__c> updatedFields = new List<Field__c>();
        List<Field__c> insertedFields = new List<Field__c>();
        try{
            update entity;
            for(Integer i = 0; i < fields.size(); i++){
                if(fields.get(i).Id == null){
                  fields.get(i).Id__c = (entity.Id__c + fields.get(i).Name__c).toUpperCase();
                  /*insert fields.get(i);*/
                  insertedFields.add(fields.get(i));  
                }else{
                  updatedFields.add(fields.get(i));
                }
            }
            /*update fields;*/
            insert insertedFields;
            update updatedFields;
        }catch(Exception ex){
            ApexPages.addMessages(ex);  
        }
    }
    
    public void cancle(){
        entity = [SELECT Id__c, Name__c, Schema_Name__c, Table_Name__c FROM Entity__c WHERE Id =: entityId];
        fields = [SELECT Id__c, Name__c, Field_Type__c, Length__c, Column_Name__c, Entity_id__c FROM Field__c WHERE Entity_id__c =: entity.Id];
    }
    
    public void deleteField(){
        List<Field__c> field = [SELECT Name__c, Field_Type__c, Length__c, Column_Name__c, Entity_id__c FROM Field__c WHERE Id__c =: fieldId];
        for(Integer i = 0; i < fields.size(); i++){
            if(fields.get(i).Id__c == fieldId){
              fields.remove(i); 
            }
        }
        if(!field.isEmpty()){
            delete field[0];
        }
    }
    
    public PageReference  getEntityListPage(){
        PageReference redirect = new PageReference('/apex/Entity_List');
        redirect.setRedirect(true);
        return redirect;
    }
}