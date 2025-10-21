class Basicphone{
     int phonenumber = 123456789;
     String message  = "hii,hello";
     String photo  = "jpg1.file";
     
    void MakeCall() {
       
        System.out.println("call phonenumber = " + phonenumber );

    }
   
    void sendMessage() {
         
        System.out.println("sending a message = " + message);
    }
}

class Smartphone extends Basicphone{
    void takephoto() {

        System.out.println("Taking a photo = " + photo);
    }
}

public class Singleinheritance{
    public static void main(String[] args){
        Smartphone o1 = new Smartphone();

        o1.MakeCall();
        o1.sendMessage();
       o1.takephoto();
    }
}