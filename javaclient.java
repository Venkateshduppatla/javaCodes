import java.io.*;  
import java.net.*; 
import java.lang.*;
import java.util.*;

class client 
{
    public static void main(String[] args) 
    {
        try{      
            Socket socket = new Socket("localhost",25955); 
            DataOutputStream dout=new DataOutputStream(socket.getOutputStream());  
            DataInputStream din=new DataInputStream(socket.getInputStream());
            while (true)
                {
                    String message=(String)din.readUTF();
                    System.out.println("" + message);
                    
                    Scanner read = new Scanner(System.in);
                    System.out.print("Enter the message: ");
                    String toServer = read.nextLine();
                    dout.writeUTF(toServer);

                    String fromServer = (String)din.readUTF();
                    System.out.println("Server: " + fromServer);
            // socket.close();
                }
            }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
