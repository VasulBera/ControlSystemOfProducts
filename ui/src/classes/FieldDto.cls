public class FieldDto {
    String id;
	String name;
    String ColumnName;
    String type;
    Integer length = 0;
    
    public FieldDto(Field__c field){
        id = field.Id__c;
        name = field.Name__c;
        ColumnName = field.Column_Name__c;
        type = field.Field_Type__c;
        if(!String.isEmpty(String.valueOf(field.Length__c))){
        	length = field.Length__c.intValue(); 
        }
    }
}