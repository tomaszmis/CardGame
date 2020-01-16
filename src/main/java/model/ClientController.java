package model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientController {
    @FXML
    private Button startButton;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField textField;
    @FXML
    private Button backButton;

    public void setbackButAction(ActionEvent actionEvent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    //public void play(ActionEvent actionEvent){
        //TODO
    //}


   synchronized public void play(ActionEvent actionEvent) throws IOException {
        Thread thread = new Thread(() ->{
            InetAddress ip;
            Socket s;
            Scanner in;
            PrintWriter out;

            textArea.setEditable(false);
            try
            {

                ip = InetAddress.getByName("localhost");
                s = new Socket(ip, 16421);
                Scanner scanner = new Scanner(System.in);

                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream(),true);
                String msgFromServer;

                while (true) {
                    while(in.hasNextLine()){
                        msgFromServer = in.nextLine();
                        System.out.println(msgFromServer);
                        textArea.appendText(msgFromServer + "\n");
                        if(msgFromServer.equalsIgnoreCase("Podaj nick")){
                            textField.setOnAction(event -> {
                                String message = "" ;
                                message = textField.getText();
                                textField.clear();
                                try{
                                    out.println(message);
                                }catch(Exception e){
                                    textArea.appendText("Failed to send message\n");
                                }});


                        }
                        if(msgFromServer.equalsIgnoreCase( "Twoja tura, wybierz numer karty")){
                            textField.setOnAction(event -> {
                                String message = "" ;
                                message = textField.getText();
                                textField.clear();
                                try{
                                    out.println(Integer.parseInt(message));
                                }catch(Exception e){
                                    textArea.appendText("Failed to send message\n");
                                }});
                        }else continue;
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }});
        thread.start();

        }


}