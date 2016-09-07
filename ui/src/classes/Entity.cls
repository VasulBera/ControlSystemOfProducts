public class Entity{
    public String id{get; set;}
    public String name{get; set;}
    public String schemaName{get; set;}
    public String tableName{get; set;}
    public List<Field> fieldList{get; set;}
    public Boolean fullUploadData{get; set;}
    
    public Entity(Entity__c entity){
        id = (entity.Schema_Name__c + entity.Table_Name__c).toUpperCase();
        name = entity.Name__c;
        schemaName = entity.Schema_Name__c;
        tableName = entity.Table_Name__c;
        fullUploadData = false;
        if(!entity.Fields__r.isempty()){
            fieldList = new List<Field>();
            for(Field__c field: entity.Fields__r){
               Field fieldDto = new Field(field);
               fieldList.add(fieldDto); 
            }
        }
    }
    public Entity(){
        
    }
    public void addFileds(List<Field__c> createdFieldList){
            fieldList = new List<Field>();
            for(Field__c field: createdFieldList){
               Field fieldDto = new Field(field);
               fieldList.add(fieldDto); 
        }
    }
    
    public Entity__c convertIntoSEntity(){
        Entity__c entity = new Entity__c();
        entity.Id__c = this.id;
        entity.Name__c = this.name;
        entity.Schema_Name__c = this.schemaName;
        entity.Table_Name__c = this.tableName;
        return entity;    
    }
    
    public list<Field__c> getListSFields(){
        List<Field__c> listFields = new List<Field__c>();
        for(Field field: this.fieldList){
            Field__c sfield = field.convertIntoSField();
            listFields.add(sfield);
        }
        return listFields;
    }
}