public class RequestJob {
    
    private String owner{get; set;}
    private String aim{get; set;}
    private String description{get; set;}
    
    public RequestJob(String owner, String aim, String description){
        this.owner = owner;
        this.aim = aim;
        this.description = description;
    }
}