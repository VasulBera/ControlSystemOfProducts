public class EditEntityController {

    public String entityId{get; set;}
    public Entity__c entity{get; set;}
    public List<Field__c> fields{get; set;}
    public String fieldId{get; set;}
    public Integer fakeId{get; set;}
    Entity customEntity{get; set;}
    
    public EditEntityController(){
        entityId = apexpages.currentpage().getparameters().get('entityID');
        customEntity = HttpService.getEntity(entityId);
        entity = customEntity.convertIntoSEntity();
        fields = customEntity.getListSFields();
        System.debug(fields);
        fakeId = 1;
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
    
    public void createTable(){
        HttpService.CreateTable(entityId);
    }
}