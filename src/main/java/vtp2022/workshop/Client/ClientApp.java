package vtp2022.workshop.Client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientApp {
    
    public static void main(String[] args) {
        
        String address = "localhost";
        int port = 8888;

        try(    Socket sock = new Socket(address, port);)
        {   
            while(true)
            {  
            Console cons = System.console();
            String input = cons.readLine("simi daiji: ");

            if(input.equals("stop"))    
            {   System.out.println("stopping connection");
                break;                                          }
            
            OutputStream os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(input);                                    
            dos.flush();                                        
            
            InputStream is = sock.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            String response = dis.readUTF();

            if (response.contains("cookie-text")){
                System.out.println("Ur fortuneCookie received is: "+ response.split(":")[1]);
            }
            else{ System.out.println(response); }
            }

        }
        catch(IOException e){   System.err.println(e);}

    }

}
