/**
 * This class tests the game.
 * @author Tomasz Miœ
 * @version 1.0
 */
public class Test  {
/**
 * This method plays a game.
 * @param args - String which can be take from console.
 */
    public static void main(String[] args) {

                Runnable gamethread = () -> {
                    System.out.println("Game is already running.");
                    Eights game = new Eights();
                    game.playGame();
                };
                new Thread(gamethread).start();
    }
}