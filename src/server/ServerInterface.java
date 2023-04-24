package server;

public class ServerInterface {
    public static Server server;

    public static boolean startServer(int port){
        if(server != null){
            return false;
        }
        server = new Server(port);
        server.start();
        return true;
    }
}
