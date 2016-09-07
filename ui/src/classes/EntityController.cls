@RestResource(urlMapping='/task/*')
global with sharing class EntityController {
    public Boolean showSection{get;set;}
    public Field__c field{get; set;}
    List<Entity> entities{get; set;}
    public String entityId{get; set;}
    public String requestName{get; set;}
    public Entity__c createdEntity{get; set;}
    public List<Field__c> createdFields{get; set;}
    public Integer fakeId {get; set;}
    public String fieldId {get; set;}
    public String entityName {get; set;}
    public SelectOption[] requestOptions{get; set;}
    
    public EntityController (){
        List<Request_job__c> listRequest = Request_job__c.getAll().values();
        requestOptions = new SelectOption[]{};
        for(Request_job__c requestJob : listRequest){
            requestOptions.add(new SelectOption(requestJob.Job_type__c, requestJob.Job_type__c));
        }
        entities = HttpService.getAllEntities();
        showSection = false;
        createdEntity = new Entity__c();
        createdFields = new List<Field__c>();
        fakeId = 1;
        requestName = 'Apply_metadata';
       
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
    
    public void requestApply(){
        String userName = UserInfo.getFirstName() + UserInfo.getLastName();
        RequestJob job = new RequestJob(userName, 'all_entities', requestName);
        String data = JSON.serialize(job);
        HttpService.createRequestTask(data);
    }
    
    public PageReference chackTakenRequest(){
        if(Taken_request__c.getOrgDefaults().request__c){
            PageReference redirect = new PageReference('/apex/Entity_List');
            redirect.setRedirect(true);
            Taken_request__c tr = Taken_request__c.getOrgDefaults();
            tr.request__c = false;
            update tr; 
            return redirect;
        }
        return null;
    }
    
    public static PageReference changeTakenRequest(){
        PageReference redirect = new PageReference('http://www.google.com');
        redirect.setRedirect(true);
        return redirect;
    }
    
    @HttpGet
    global static void setRequestJodDone(){
        Taken_request__c tr = Taken_request__c.getOrgDefaults();
        tr.request__c = true;
        update tr; 
    }
    
    public static PageReference RequestJobRedirect(){
        PageReference redirect = new PageReference('http://www.google.com');
        return redirect;
    }
    
}