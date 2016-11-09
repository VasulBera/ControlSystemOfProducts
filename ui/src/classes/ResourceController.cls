public with sharing class ResourceController {
    public String MAX_VALUE{get; set;}
    public List<Line> lines{get; set;}
    public List<Entity> entities{get; set;}
    public Entity entity{get; set;}
    public Integer index = 0;
    public Boolean nextLinesDisable{get; set;}
    public Boolean previusLinesDisable{get; set;}
    public Boolean FirstLinesDisable{get; set;}
    public Boolean LastLinesDisable{get; set;}
    public SelectOption[] entitiesOptions{get; set;}
    public SelectOption[] countRow{get; set;}
    public String entityName{get; set;}
    public Integer count{get; set;}
    
    
    public String jsonChanged{get; set;}
    
    public ResourceController(){
        entities = HttpService.getAllEntityWithSchemaNameClient();
        entitiesOptions = new SelectOption[]{};
        for(Entity entity: entities){
            entitiesOptions.add(new SelectOption(entity.id, entity.name));
        }
        countRow = new SelectOption[]{};
        countRow.add(new SelectOption('5', '5'));
        countRow.add(new SelectOption('10', '10'));
        countRow.add(new SelectOption('25', '25'));
        countRow.add(new SelectOption('50', '50'));
        countRow.add(new SelectOption('100', '100'));
        MAX_VALUE = '5';
        entity = entities.get(0);
        entityName = entities.get(0).id;
        lines = new List<Line>();
        count = HttpService.getRowNumber(entityName);
        getFirstLine();
    }
    
    public void getNextLines(){
        index +=  Integer.valueof(MAX_VALUE);
        updateLines();
        if(index + Integer.valueof(MAX_VALUE) >= count){
            LastLinesDisable = true;
            nextLinesDisable = true;
            FirstLinesDisable = false;
            previusLinesDisable = false;
        }else{
            FirstLinesDisable = false;
            previusLinesDisable = false;
        }        
    }
    
    public void getPreviusLines(){
        index -=  Integer.valueof(MAX_VALUE);
        updateLines();
        if(index <= 0){
            LastLinesDisable = false;
            nextLinesDisable = false;
            FirstLinesDisable = true;
            previusLinesDisable = true;
        }else{
            LastLinesDisable = false;
            nextLinesDisable = false;
        }
    }
    
    public void getFirstLine(){
        index = 0;
        updateLines();
        FirstLinesDisable = true;
        previusLinesDisable = true;
        LastLinesDisable = false;
        nextLinesDisable = false;
    }
    
    public void getLastLine(){
        if(Math.mod(count, Integer.valueof(MAX_VALUE)) == 0){
            index = count - Integer.valueof(MAX_VALUE);
        }else{
            index = count - Math.mod(count, Integer.valueof(MAX_VALUE));
        }
        updateLines();
        LastLinesDisable = true;
        nextLinesDisable = true;
        FirstLinesDisable = false;
        previusLinesDisable = false;
    }
    
    public void setMaxValue(){
       
    }
    
    public void setEntityId(){
        entity = HttpService.getEntity(entityName);
        count = HttpService.getRowNumber(entityName);
        updateLines();
    }
    
    public void setRowCount(){
       index = 0;
       updateLines();
    }
    
    public void sendJson(){
        HttpService.updateCustomTableRow(entityName, jsonChanged);
    }
    
    public void updateLines(){
        lines = HttpService.getAllCustomData(entityName, index, MAX_VALUE);
    }
}