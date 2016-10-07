public class DataController {
    public List<Object> back{get; set;}
    public String ob{get; set;}
    public DataController(){
        String data = '[["color","price","produsing_country"],["color","price","produsing_country"],["color","price","produsing_country"],["color","price","produsing_country"],["color","price","produsing_country"],["color","price","produsing_country"],["color","price","produsing_country"],["color","price","produsing_country"],["color","price","produsing_country"],["color","price","produsing_country"]]';
        back = (List<Object>)JSON.deserializeUntyped(data);
        List<Object> row = (List<Object>)back.get(0);
        ob = (String)row.get(0);
        System.debug(ob);
    }
}