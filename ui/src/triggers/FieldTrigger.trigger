trigger FieldTrigger on Field__c (before insert, before update, after insert,  after update, after delete, after undelete) {
    if(trigger.isBefore){
        if(trigger.isInsert){
            FieldService.setFieldId(trigger.new);
        }
    }
    if(trigger.isAfter){
        if(trigger.isInsert){
            FieldService.insertFieldIntoRemoteService(trigger.new);
        }
        if(trigger.isUpdate){
            FieldService.updateFieldIntoRemoteService(trigger.new);
        }
        if(trigger.isDelete){
            FieldService.deleteFieldIntoRemoteService(trigger.old);
        }
    }
}