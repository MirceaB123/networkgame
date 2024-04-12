public class Main
{
    public static void main(String[] args)
    {
        //if hosting a game run this code:
        //hostGame();
        //joinGame("localhost");

        
        //if joining a game, run this code with host's IP address:
        joinGame("10.13.46.103");
    }

    public static void hostGame()
    {
        //start server in new thread
        new Thread()
        {
            public void run()
            {
                new Server();
            }
        }.start();

        //wait 2 seconds for server to start
        try{Thread.sleep(2000);}catch(Exception e){}
    }

    public static void joinGame(String ipAddress)
    {
        new Thread()
        {
            public void run()
            {
                new Client(ipAddress);        
            }
        }.start();
    }
}