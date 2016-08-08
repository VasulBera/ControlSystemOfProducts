public class EntityService {
	
    public static void setEntityId(List<Entity__c> entities){
        for(Entity__c entity: entities){
            String id = (entity.Schema_Name__c + entity.Table_Name__c).toUpperCase();
            List<Entity__c> entityDb = [SELECT Name__c FROM Entity__c WHERE Id__c =: id];
            if(!entityDb.isEmpty()){
                throw new EntityExistedException('Entity with Id__c = ' + id + ' has already existed');
            }
            entity.Id__c = id; 
        }
    }
    
    public static void insertEntityIntoRemoteService(List<Entity__c> entities){
        for(Entity__c entity: entities){
            EntityDto entityDto = new EntityDto(entity);
            String data = JSON.serialize(entityDto);
            HttpService.insertData(data);
        }
    }
    
    public static void updateEntityIntoRemoteService(List<Entity__c> entities){
        for(Entity__c entity: entities){
            EntityDto entityDto = new EntityDto(entity);
            String id = entityDto.id;
            String data = JSON.serialize(entityDto);
            HttpService.patchData(id, data);
        }
    }
    
    public static void deleteEntityIntoRemoteService(List<Entity__c> entities){
        for(Entity__c entity: entities){
            String id = entity.Id__c;
            HttpService.deleteData(id);
        }
    }
}