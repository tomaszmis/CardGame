import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println("0 - wyświetl wyniki");
            System.out.println("1 - zagraj");
            int choose = scanner.nextInt();
            switch(choose){
                case 0: displayScores(); break;
                case 1:
                    while (true) {
                        while(in.hasNextLine()){
                            msgFromServer = in.nextLine();
                            System.out.println(msgFromServer);
                            if(msgFromServer.equalsIgnoreCase("Podaj nick")){
                                String stri = scanner.nextLine();
                                stri = scanner.nextLine();
                                out.println(stri);
                            }
                            if(msgFromServer.equalsIgnoreCase( "Twoja tura, wybierz numer karty")){
                                out.println(scanner.nextInt());
                            }else continue;
                        }
                    }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public  static void displayScores(){
        Connector connector = new Connector();
        List<String> list = connector.getResultsFromDataBase();
        for(String s : list){
            System.out.println(s);
        }
    }
}