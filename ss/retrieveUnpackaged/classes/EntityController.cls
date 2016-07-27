public class EntityController {

    public List<Entity__c> entities {get; set;}
    public Entity__c entity {get; set;}
    
    public Entity__c createdEntity {get; set;}
    public List<Field__c> createdFields{get; set;}
    public String fieldId {get; set;}
    public Integer fakeId {get; set;}
    
    public Boolean showSection{get;set;}
    public String entityId{get; set;}
    
    public EntityController (){
       entities = [SELECT Name__c, Schema_Name__c, Table_Name__c FROM Entity__c];
       entity = new Entity__c();
       createdEntity = new Entity__c();
       createdFields = new List<Field__c>();
       showSection = false;
       fakeId = 1; 
    }
    
    public void deleteEntity(){
        Entity__c deleteEntity = [SELECT Id, Name__c, Schema_Name__c, Table_Name__c FROM Entity__c WHERE Id =: entityId];
        for(Integer i = 0; i < entities.size(); i++){
            if(entities.get(i).Id == deleteEntity.Id){
              entities.remove(i); 
            }
        }
        delete deleteEntity;
    }
    
    public PageReference  getEntityEditPage(){
        PageReference redirect = new PageReference('/apex/Entity_Edit');
        redirect.getParameters().put('entityID', entityId);
        return redirect;
    }
    
    public void show() {
         if(showSection){
             showSection = false;
         }else{
             showSection = true;
         }      
    }
    
    public void saveEntity(){
    try{
           insert createdEntity;
           entities.add(createdEntity); 
               for(Integer i = 0; i < createdFields.size(); i++){
                    createdFields.get(i).id__c = (entityId + createdFields.get(i).Column_Name__c).toUpperCase();
                    createdFields.get(i).Entity_id__c = createdEntity.Id;
               }
           insert createdFields;
           createdEntity = new Entity__c();
           createdFields = new List<Field__c>();
       }catch(Exception ex){
         ApexPages.addMessages(ex);  
       } 
    }
    
    public void cancelSavingEntity(){
       createdEntity = new Entity__c();
       createdFields = new List<Field__c>(); 
    }
    
    public void createField(){
        Field__C field = new Field__c(Id__c = String.valueOf(fakeId++), Field_Type__c = 'NVARCHAR');
        createdFields.add(field);
    }
    
    public void deleteField(){
        for(Integer i = 0; i < createdFields.size(); i++){
            if(createdFields.get(i).Id__c == fieldId){
              createdFields.remove(i); 
            }
        }
    }
}