public class TwoUserChatDemo{
    public static void main(String[] args) throws Exception{
        ChatUser u1 = new ChatUser("Alice");
        ChatUser u2 = new ChatUser("Bob");

        u1.setPriority(Thread.MAX_PRIORITY);
        u2.setPriority(Thread.MIN_PRIORITY);

        u1.start();
        u2.start();

        System.out.println("Alice alive?"+u1.isAlive());

        Thread.sleep(1000);
        u2.pauseChat();
        System.out.println("Bob paused");

        Thread.sleep(1000);
        u2.resumeChat();
        System.out.println("Bob was resume");

        Thread.sleep(1000);
        u1.stopChat();
        System.out.println("Alice is stop");

        u1.join();
        u2.join();

        System.out.println("Alice alive after join?" + u1.isAlive());
        System.out.println("Chat needed");

    }
}
class ChatUser extends Thread{
    private volatile boolean paused = false;
    private volatile boolean running = true;

    ChatUser(String name){
        super(name);

    }
    public void run(){
        int i = 1;
        while(running && i<=5){
            if(paused == false){
                System.out.println(getName()+"says:message"+i);
                i++;
                try{Thread.sleep(500);}catch(Exception e){}

            }
        }
        System.out.println(getName()+ "finish chatting ");
    }
    public void pauseChat() {paused = true;}
    public void resumeChat() {paused = false;}
    public void stopChat() {running = false;}

}