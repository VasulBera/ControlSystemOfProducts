public class EntityService {
    
    public static String checkCorrectData(Entity entity){
        String result = '';
        if(!Pattern.matches('\\w+', entity.schemaName)){
              result += 'Entity Schema name can contain only symbols, numbers and underscore. Pleas check your data! <br/>';
        }
        if(!Pattern.matches('\\w+', entity.tableName)){
              result += 'Entity Table name can contain only symbols, numbers and underscore. Pleas check your data! <br/>';
        }
        if(entity.name.length() > 128){
            result += 'Entity name length can be less then 128 symbols. Pleas check your data! <br/>';
        }
        if(entity.schemaName.length() > 128){
            result += 'Entity schema name length can be less then 128 symbols. Pleas check your data! <br/>';
        }
        if(entity.tableName.length() > 128){
            result += 'Entity table name length can be less then 128 symbols. Pleas check your data! <br/>';
        }
        for(Field field: entity.fieldList){
            if(String.isEmpty(field.type)){
               result += 'Field \"' + field.name + '\" has empty type. Pleas check your data! <br/>';
            }
            if(field.length < 1 && field.type == 'NVARCHAR'){
              result += 'Field \"' + field.name + '\" with type \"NVARCHAR\" has lenght less then one (' + field.length + ').' + 'Pleas check your data! <br/>';
            }
            if(!Pattern.matches('\\w+', field.ColumnName)){
              result += 'Field Column name of \"' + field.name + '\" can contain only symbols, numbers and underscore. Pleas check your data! <br/>';
            }
            if(field.name.length() > 128){
                result += 'Field name of \"' + field.name + '\" length can be less then 128 symbols. Pleas check your data! <br/>';
            }
            if(field.ColumnName.length() > 128){
                result += 'Field column name of \"' + field.name + '\" length can be less then 128 symbols. Pleas check your data! <br/>';
            }
            Integer count = 0;
            for(Field fieldCheck: entity.fieldList){
                if(field.ColumnName == fieldCheck.ColumnName){
                    count++;
                }
            }
            if(count > 1){
                result += 'Field \"' + field.name + '\" column name has duplicate value. Pleas check your data! <br/>';
            }
        }
        return result.removeEnd('<br/>');
    }
    
    public static String checkCorrectDataInsert(Entity entity){
		   String result = checkCorrectData(entity);
           Entity entityDb = HttpService.getEntity(entity.id);
           if(entityDb != null){
              result += 'Entity with schema name \"' + entity.schemaName + '\" and table name \"' + entity.tableName + '\" is exists. Pleas check your data! <br/>';
           }
           return result.removeEnd('<br/>');
    }
    public static String checkCorrectDataUpdate(Entity entity){
        return checkCorrectData(entity);
    }
}