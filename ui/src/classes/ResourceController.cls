public with sharing class ResourceController {
    public List<Line> lines{get; set;}
    public ResourceController()
    {
        String data = '[{"first":50,"second":"BMW","third":"z4","fourth":2016,"fifth":"sedan","sixth":"ICE","seventh":"gasoline","eighth":2.0,"ninth":160,"tenth":"6-ICE","el' +
                         + 'eventh":false,"twelfth":6,"thirteenth":220.0,"fourteenth":7.9,"fifteenth":true,"sixteenth":2,"seventeenth":4239,"eighteenth":1790,"nineteenth":1291,"t' + 
                         + 'wentieth":2496,"twentyFirst":1511,"twentySecond":1559,"twentyThird":1470,"twentyFourth":1725,"twentyFifth":180,"twentySixth":55,"twentySeventh":"Germa' + 
                         + 'ny","twentyEighth":200000.0}]';
        lines = (List<Line>)JSON.deserialize(data, List<Line>.class);
        System.debug(lines);
    }
}