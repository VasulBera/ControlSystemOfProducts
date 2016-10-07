public class EditEntityController {

    public String entityId{get; set;}
    public Entity__c entity{get; set;}
    public List<Field__c> fields{get; set;}
    public String fieldId{get; set;}
    public Integer fakeId{get; set;}
    Entity customEntity{get; set;}
    public Boolean fullUploadData{get; set;}
    public String fileLoadType{get; set;}
    public SelectOption[] loadFileTypeOptions{get; set;}
    
    public EditEntityController(){
        entityId = apexpages.currentpage().getparameters().get('entityID');
        customEntity = HttpService.getEntity(entityId);
        entity = customEntity.convertIntoSEntity();
        fields = customEntity.getListSFields();
        fullUploadData = customEntity.fullUploadData;
        fakeId = 1;
        loadFileTypeOptions = new SelectOption[]{};
        loadFileTypeOptions.add(new SelectOption('false', 'Increase'));
        loadFileTypeOptions.add(new SelectOption('true', 'Full load'));
        fileLoadType = String.valueOf(customEntity.fullUploadData);
    }
    
    public void createField(){
        Field__c field = new Field__c(Entity_id__c = entity.Id, Id__c = String.valueOf(fakeId++), Field_Type__c = 'NVARCHAR');
        fields.add(field);
    }
    
    public void save(){
     
        try{
            customEntity.name = entity.Name__c;
            customEntity.schemaName = entity.Schema_Name__c;
            customEntity.tableName = entity.Table_Name__c;    
            customEntity.addFileds(fields);
            customEntity.fullUploadData = Boolean.valueOf(fileLoadType);
            String info = EntityService.checkCorrectDataUpdate(customEntity);
            if(!String.isEmpty(info)){
                throw new EntityExistedException(info);
            }
            String data = JSON.serialize(customEntity);
            HttpService.updateData(data);
        }catch(Exception ex){
             ApexPages.addMessages(ex);
        }
    }
    
    public void cancle(){
        customEntity = HttpService.getEntity(entityId);
        entity = customEntity.convertIntoSEntity();
        fields = customEntity.getListSFields();
    }
    
    public void deleteField(){
       List<Field__c> updateFields = customEntity.getListSFields(); 
       for(Integer i = 0; i < fields.size(); i++){
            if(fields.get(i).Id__c == fieldId){
              fields.remove(i); 
            }
        }
    }
    
    public void updateTable(){
        String userName = UserInfo.getFirstName() + ' ' + UserInfo.getLastName();
        RequestJob job = new RequestJob(userName, entityId, 'Apply_metadata');
        String data = JSON.serialize(job);
        HttpService.createRequestTask(data);
    }
    
    public PageReference chackTakenRequest(){
        if(Taken_request__c.getOrgDefaults().request__c){
            PageReference redirect = new PageReference('/apex/Entity_Edit?entityID=' + entityId);
            redirect.setRedirect(true);
            Taken_request__c tr = Taken_request__c.getOrgDefaults();
            tr.request__c = false;
            update tr; 
            return redirect;
        }
        return null;
    }
}