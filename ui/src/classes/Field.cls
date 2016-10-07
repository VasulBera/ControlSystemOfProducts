public class Field {
    public String id{get; set;}
	public String name{get; set;}
    public String ColumnName{get; set;}
    public String type{get; set;}
    public Integer length{get; set;}
    public Boolean unique{get; set;}
    
    public Field(Field__c field){
        name = field.Name__c;
        ColumnName = field.Column_Name__c;
        type = field.Field_Type__c;
        unique = field.isUnique__c;
        if(!String.isEmpty(String.valueOf(field.Length__c)) && field.Field_Type__c == 'NVARCHAR'){
        	length = field.Length__c.intValue(); 
        }else{
            length = 0;
        }
    }
    
    public Field__c convertIntoSField(){
        Field__c field = new Field__c();
        field.Id__c = this.id;
        field.Name__c = this.name;
        field.Column_Name__c = this.ColumnName;
        field.Field_Type__c = this.type;
        field.isUnique__c = this.unique;
        if(this.length > 0){
           field.Length__c = this.length;    
        }else{
           field.Length__c = null; 
        }

        return field;
    }
    public Field(){
        
    }
}