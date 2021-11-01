import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
    public static void main (String[] args) {
        Socket s = null;
        try{
            s = new Socket("localhost",7896);

            DataInputStream in = new DataInputStream( s.getInputStream());
            DataOutputStream out =new DataOutputStream( s.getOutputStream());

            String connect = in.readUTF();
            System.out.println("" + connect);

            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();

            while(!text.equals("Exit")) {
                out.writeUTF(text); // UTF is a string encoding
                String data = in.readUTF();        // read a line of data from the stream
                System.out.println("" + data);
                text = sc.nextLine();
            }
            out.writeUTF("Exit");
        }catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("readline:"+e.getMessage());
        }finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
    }
}
