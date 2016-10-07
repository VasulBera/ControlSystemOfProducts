public class requestStatusController {

    public static PageReference RequestJobRedirect(){
        PageReference redirect = new PageReference('/apex/Entity_List');
        return redirect;
    }

}