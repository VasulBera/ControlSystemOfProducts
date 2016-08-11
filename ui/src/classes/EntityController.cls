public class EntityController {
  
    public Boolean showSection{get;set;}
    public Field__c field{get; set;}
    
    List<Entity> entities{get; set;}
    
    public String entityId{get; set;}
    public Entity__c createdEntity{get; set;}
    public List<Field__c> createdFields{get; set;}
    public Integer fakeId {get; set;}
    public String fieldId {get; set;}
    public String entityName {get; set;}
    
    public EntityController (){
        entities = HttpService.getAllEntities();
        showSection = false;
        createdEntity = new Entity__c();
        createdFields = new List<Field__c>();
        fakeId = 1;
    }
    public List<Entity> getEntities(){
        return entities;
    }

    public void deleteEntity(){
        for(Integer i = 0; I < entities.size(); i++){
            if(entities.get(i).id == entityId){
                entities.remove(i);
            }
        }
        HttpService.deleteData(entityId);
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
             this.cancelSavingEntity();
         }      
    }
    
    public void saveEntity(){
        try{
            Entity entity = new Entity(createdEntity);
            entity.addFileds(createdFields);
            String info = EntityService.checkCorrectDataInsert(entity);
            if(!String.isEmpty(info)){
                throw new EntityExistedException(info);
            }
            String data = JSON.serialize(entity);
            entities.add(entity);
            HttpService.insertData(data);
            this.cancelSavingEntity();
        }catch(Exception ex){
             ApexPages.addMessages(ex);
        }
    }
    
    public void cancelSavingEntity(){
       createdEntity = new Entity__c();
       createdFields = new List<Field__c>(); 
    }
    
    public void createField(){
        Field__C field = new Field__c(Id__c = String.valueOf(fakeId++), Field_Type__c = 'NVARCHAR');/*, Length__c = 1*/
        createdFields.add(field);
    }
    
    public void deleteField(){
        System.debug('id = ' + entityId);
        for(Integer i = 0; i < createdFields.size(); i++){
            if(createdFields.get(i).Id__c == fieldId){
              createdFields.remove(i); 
            }
        }
    }
    
}