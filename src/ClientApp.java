import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientApp extends Application {

    private TextArea msg = new TextArea();
    private NetworkConnectionClient networkConnection =  createClient();

    private Parent createContent(){
        msg.setPrefHeight(550);

        TextField input = new TextField();
        input.setOnAction(event -> {
            String message = "" ;
            message = input.getText();
            input.clear();

            msg.appendText(message);
            try{
                networkConnection.send(message);
            }catch(Exception e){
                msg.appendText("Failed to send message\n");
            }
        });
        //Image background = new Image("file:bg.jpg");
        //ImageView mv = new ImageView(background);
        VBox root = new VBox(20,msg,input);
        root.setPrefSize(600,600);
        //root.getChildren().addAll(mv);
        return root;
    }
    @Override
    public void init() throws Exception{
        networkConnection.startConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.setTitle("Crazy Eigths");
        primaryStage.setIconified(false);
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }
    @Override
    public void stop() throws Exception{
        networkConnection.closeConnection();
    }

    private Server createServer(){
        return new Server(55555, data-> {
            Platform.runLater(() -> {
                msg.appendText(data.toString() + "\n");
            });
        });
    }



    private Client createClient(){
        return new Client("127.0.0.1", 55555, data -> {
            Platform.runLater(() -> {
                msg.appendText(data.toString() + "\n");
            });
        });
    }

/*    public void play(){
        InetAddress ip;
        Socket s;
        Scanner in;
        ObjectOutputStream out;

        try
        {

            ip = InetAddress.getByName("localhost");
            s = new Socket(ip, 16421);
            System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");

            Scanner scanner = new Scanner(System.in);

            in = new Scanner(s.getInputStream());
            out = new ObjectOutputStream(s.getOutputStream(),true);
            String msgFromServer;
            while (true) {
                msgFromServer = in.nextLine();
                System.out.println(msgFromServer);
                out.println(scanner.nextLine());

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
*/
    public static void main(String[] args) {launch(args);
}
}