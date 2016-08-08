public class EntityDto {
    public String id;
    public String name;
    public String schemaName;
    public String tableName;
    public List<FieldDto> fieldList;
   
    public EntityDto(Entity__c entity){
        id = entity.Id__c;
        name = entity.Name__c;
        schemaName = entity.Schema_Name__c;
        tableName = entity.Table_Name__c;
        if(!entity.Fields__r.isempty()){
            fieldList = new List<FieldDto>();
            for(Field__c field: entity.Fields__r){
               FieldDto fieldDto = new FieldDto(field);
               fieldList.add(fieldDto); 
            }
        }
    }
    
    public void addFieldList(List<FieldDto> fields){
        fieldList = fields;
    }
}