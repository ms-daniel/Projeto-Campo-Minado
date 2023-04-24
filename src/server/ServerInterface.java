package server;

public class ServerInterface {
    public static Server server;
    public static String ipServer;


    public static String startServer(int port){
        server = new Server(port);
        ipServer = server.runServer(port);
        if(ipServer != null){
            server.start();
            return ipServer;
        }
        server = null; 
        return null;
    }
}
