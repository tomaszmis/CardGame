package model;
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
                Scanner input = new Scanner(s.getInputStream());
                output.println("Podaj nick");
                String nick = input.nextLine();

                while (true) {
                    Player player = new Player(nick,output,input);
                    output.println("Twoj nick to: " + player.getPlayerName());
                    output.println("Jeśli nie masz odpowiedniej karty w talji podaj 99. Zaczynamy!");
                    Eights game = new Eights(player,3,output);
                    //Eights game = new Eights(output);
                    game.playGame();
                    if(player.score() == 0){
                        output.println("Congratulations!!!");
                    }else
                    output.println("GAME OVER");
                    break;
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