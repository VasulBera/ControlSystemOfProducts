public class HttpService {
    
    @future(callout=true)
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
    
    @future(callout=true)
    public static void updateData(String data){
        Remote_Connection__c rc = Remote_Connection__c.getInstance('Static_IP');
        String ip = rc.Public_IP__c;
        String url = 'http://' + ip + ':8080/service/rest/entity';
        
        HttpRequest request = new HttpRequest();
        request.setEndpoint(url);
        request.setMethod('PUT');
        request.setHeader('content-type', 'application/json');
        request.setBody(data);
        System.debug('data -> ' + data);
		System.debug('request -> ' + request.toString());
        Http http = new Http();
        HttpResponse response = new HttpResponse();
        response = http.send(request);
        System.debug('responce -> ' + response.toString());
    }
    
    @future(callout=true)
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
    
    @future(callout=true)
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
}