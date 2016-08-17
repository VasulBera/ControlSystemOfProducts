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
    
    public static Entity getEntity(String id){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/entity/' + id;
        System.debug('url ' + url);
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
        System.debug('url ' + url);
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('GET');
        request.setHeader('content-type', 'application/json');
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
        System.debug(response);
    }
    
    public static void createRequestTask(String id, String description, String owner){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/task/' + id + '/' + description + '/' + owner;
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('GET');
        request.setHeader('content-type', 'application/json');
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
    }
}