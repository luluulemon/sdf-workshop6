package vtp2022.workshop;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class cookieClientHandler implements Runnable{
    
    Socket port;
    String cookieFile;

    // constructor
    public cookieClientHandler(Socket sock, String cookieFile)
        {   port = sock;
            this.cookieFile = cookieFile;   }


    @Override
    public void run(){

        boolean stop = false;

        try
        {   
            while(!stop)
            {   
                System.out.println("Awaiting client cmd ... ");

                InputStream is = port.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                String input = dis.readUTF();

                OutputStream os = port.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                if(input.contains("get-cookie")){

                    String fortuneCookie = cookie.cookieGetter(cookieFile);
                    System.out.println(fortuneCookie);
                    dos.writeUTF("cookie-text:" + fortuneCookie);
                    dos.flush();
                }

                else if(input.equals("stop"))
                    {   dos.writeUTF("stopping connection");
                        dos.flush();
                        stop = true;    }

                else{   dos.writeUTF("invalid cmd laaa");
                        dos.flush();                            }

            }
        }
        catch(IOException e){}
        


    }
}

