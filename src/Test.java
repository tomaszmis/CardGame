/**
 * This class tests the game.
 * @author Tomasz Mi�
 * @version 1.0
 */
/*
public class Test  {
/*
 * This method plays a game.
 * @param args - String which can be take from console.
 *//*
    public static void main(String[] args) {

        String nick;
        int numberOfPlayers;
        System.out.println("Podaj nick: ");
        Scanner in = new Scanner(System.in);
        nick = in.nextLine();
        System.out.println("Podaj liczb� graczy");
        numberOfPlayers = in.nextInt();
        in.close();


                Runnable gamethread = () -> {
                    Player player = new Player(nick);
                    System.out.println("Game is already running.");
                    //Eights game = new Eights(player,numberOfPlayers);
                    //game.playGame();
                };
                new Thread(gamethread).start();
    }
}*/