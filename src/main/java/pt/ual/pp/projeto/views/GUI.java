package pt.ual.pp.projeto.views;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Projecto Paradigmas Programação");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();
    }

    @FXML
    public TextField tempoSimulacao;

    public void startSimulation() {
        System.out.println("Simulacao iniciada");
        String tempString = tempoSimulacao.getText();
        //new Controller(Integer.parseInt(tempString));
        System.out.println("Simulação decorrida em " + tempString + " segundos.");
    }




    public static void main(String[] args) {
        launch(args);
    }
}