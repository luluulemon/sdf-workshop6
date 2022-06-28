package vtp2022.workshop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
//import java.util.Scanner;

public class cookie {
    
    public static String cookieGetter(String cookieFile){

        List<String> cookieList = new LinkedList<>();
        int cookieCount = 0;
        String fortuneCookie = null;

        // try(    Scanner scan = new Scanner(cookieFile); )
        // {while( scan.hasNextLine())
        //     {   String line = scan.nextLine();    
        //         cookieList.add(line);            
        //         cookieCount ++ ;                }

        String line;
        try(    Reader reader = new FileReader(cookieFile);
                BufferedReader br = new BufferedReader(reader);     )
            {   while(  (line=br.readLine())!= null  )
                {   cookieList.add(line);
                    cookieCount++;          }
        
            Random rand = new Random();
            fortuneCookie = cookieList.get( rand.nextInt(cookieCount) );

            return fortuneCookie;   }
            catch(NullPointerException e){  System.err.println(e);  }
            catch(FileNotFoundException e){ System.err.println(e);  }
            catch(IOException e){   System.err.println(e);}

        return fortuneCookie;
    }
}
