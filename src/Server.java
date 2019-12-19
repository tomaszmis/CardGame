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
                    Scanner input = new Scanner(s.getInputStream());
                    PrintWriter output = new PrintWriter(s.getOutputStream(),true);
                    while (input.hasNextLine()) {
                        output.println("Podaj nick");
                        Player player = new Player(input.nextLine());
                        output.println("Twoj nick to: " + player.getPlayerName());
                        System.out.println("Game is already running.");
                        Eights game = new Eights(player,3);
                        game.playGame();
                    }


                } catch (Exception e){
                    try {
                        s.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
}

