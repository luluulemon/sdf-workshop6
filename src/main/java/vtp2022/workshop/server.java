package vtp2022.workshop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {
    

    public static void main(String[] args) {
        
        int port  = 8888;
        String cookieFile = "cookieFile.txt";
        if (args.length > 0)
            {   port = Integer.parseInt(args[0]);
                cookieFile = args[1];               }
        ExecutorService threadpool = Executors.newFixedThreadPool(3);
        
        
        try (ServerSocket server = new ServerSocket(port); )
        {   System.out.println("Server up, listening for connection on "+port);                                                                      
            while(true){
                Socket sock = server.accept();
                cookieClientHandler thr = new cookieClientHandler(sock, cookieFile);
                threadpool.submit(thr);
            }
        }
        catch(IOException e)
        {   System.err.println(e);}

    }
}
