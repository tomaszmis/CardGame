
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        InetAddress ip;
        Socket s;
        Scanner in;
        PrintWriter out;




        try
        {

            ip = InetAddress.getByName("localhost");
            s = new Socket(ip, 16421);
            System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");

            Scanner scanner = new Scanner(System.in);

            in = new Scanner(s.getInputStream());
            out = new PrintWriter(s.getOutputStream(),true);
            String msgFromServer;
            while (true) {
                msgFromServer = in.nextLine();
                System.out.println(msgFromServer);
                    if(msgFromServer == "Podaj nick") {
                        out.println(scanner.nextLine());
                    } else if(msgFromServer == "Twoja tura, wybierz numer karty"){

                    }else continue;
            }




        }catch(Exception e){
            e.printStackTrace();
        }

        // Main loop





    }
}