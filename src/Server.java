
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

        public static void main(String[] args) {

            ServerSocket ss = null;
            try {
                ss = new ServerSocket(16421);
            } catch (IOException e) {
                e.printStackTrace();
            }




            // Main loop
            while (true)
            {
                Socket s = null;

                try {

                    s = ss.accept();

                    System.out.println("New client connected on: " + s);
                    PrintWriter output = new PrintWriter(s.getOutputStream(),true);
                    output.println("Podaj nick");
                    Scanner input = new Scanner(s.getInputStream());


                    while (true) {
                        Player player = new Player(input.nextLine(),output,input);
                        System.out.println("Twoj nick to: " + player.getPlayerName());
                        output.println("Game is already running.");
                        Eights game = new Eights(player,3,output);
                        game.playGame();
                    }


                } catch (Exception e){
                    System.out.println(e.getMessage());
                    try {
                        s.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
}
