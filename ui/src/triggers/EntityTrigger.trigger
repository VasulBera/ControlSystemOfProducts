trigger EntityTrigger on Entity__c (before insert, before update, after insert,  after update, after delete, after undelete) {
    if( trigger.isBefore ){
        if(trigger.isInsert){
            EntityService.setEntityId(trigger.new);
        }
    }
    if( trigger.isAfter ){
        if(trigger.isInsert){
            EntityService.insertEntityIntoRemoteService(trigger.new);
        }
        if(trigger.isUpdate){
            EntityService.updateEntityIntoRemoteService(trigger.new);
        }
        if(trigger.isDelete){
            EntityService.deleteEntityIntoRemoteService(trigger.old);
        }
    }
}