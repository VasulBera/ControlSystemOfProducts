public class HttpService {
    
    public static void insertData(String data){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/entity';
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('POST');
        request.setHeader('content-type', 'application/json');
        request.setBody(data);
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
    }
    
    public static void updateData(String data){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/entity';
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('PUT');
        request.setHeader('content-type', 'application/json');
        request.setBody(data);
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
   }
    
    public static void deleteData(String id){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/entity/' + id;
        
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('DELETE');
        request.setHeader('content-type', 'application/json');
        
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
    }
    
    public static void patchData(String id, String data){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/entity/' + id;
        
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('POST');
        request.setHeader('content-type', 'application/json');
        request.setHeader('X-HTTP-Method-Override','PATCH');
        request.setBody(data);
        
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
    }
    
    public static List<Entity> getAllEntities(){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/entity';
        
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('GET');
        request.setHeader('content-type', 'application/json');
       
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
        String data = response.getBody();
        if(!String.isEmpty(data)){
        	List<Entity> entities = (List<Entity>)JSON.deserialize(data, List<Entity>.class);
            return entities;
        }else{
            return new List<Entity>();
        }
      
    }
    
    public static List<Entity> getAllEntityWithSchemaNameClient(){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/entity/all-client';
        
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('GET');
        request.setHeader('content-type', 'application/json');
       
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
        String data = response.getBody();
        if(!String.isEmpty(data)){
        	List<Entity> entities = (List<Entity>)JSON.deserialize(data, List<Entity>.class);
            return entities;
        }else{
            return new List<Entity>();
        }
    }
    
    public static List<Line> getAllCustomData(String id, Integer fromRow, String count){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/custom/' + id + '/' + fromRow + '/' + count;
        
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('GET');
        request.setHeader('content-type', 'application/json');
       
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
        String data = response.getBody();
        if(!String.isEmpty(data)){
        	List<Line> lines = (List<Line>)JSON.deserialize(data, List<Line>.class);
            return lines;
        }else{
            return new List<Line>();
        }
    }
    
    public static void  updateCustomTableRow(String id, String data){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/custom/' + id;
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('PUT');
        request.setHeader('content-type', 'application/json');
        request.setBody(data);
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
    } 
    
     public static Integer getRowNumber(String id){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/custom/count/' + id;
        
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('GET');
        request.setHeader('content-type', 'application/json');
       
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
        Integer count = Integer.valueof(response.getBody());
        return count;
    }
    
    
    
    public static Entity getEntity(String id){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/entity/' + id;
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('GET');
        request.setHeader('content-type', 'application/json');
       
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
        String data = response.getBody();
        if(!String.isEmpty(data)){
            data = '[' + data + ']';
        	List<Entity> entity = (List<Entity>)JSON.deserialize(data, List<Entity>.class);
            return entity.get(0);
        }else{
            return null;
        }
    }
    
    public static void CreateTable(String id){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/task/create/' + id;
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('GET');
        request.setHeader('content-type', 'application/json');
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
    }
    
    public static void UpdateTable(String id){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/task/update/' + id;
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('GET');
        request.setHeader('content-type', 'application/json');
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
    }
    
    public static void createRequestTask(String data){
        System.debug(data);
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/task/';
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('POST');
        request.setBody(data);
        request.setHeader('content-type', 'application/json');
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
    }
}