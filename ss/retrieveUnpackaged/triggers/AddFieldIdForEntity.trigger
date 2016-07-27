trigger AddFieldIdForEntity on Entity__c (before insert) {
    List<Entity__c> entities = Trigger.new;
    for(Entity__c entity: Trigger.new){
        String id = (entity.Schema_Name__c + entity.Table_Name__c).toUpperCase();
        List<Entity__c> entityDb = [SELECT Name__c FROM Entity__c WHERE Id__c =: id];
        if(!entityDb.isEmpty()){
            throw new EntityExistedException('Entity with Id__c = ' + id + 'has already existed');
        }
        entity.Id__c = id; 
    }
}