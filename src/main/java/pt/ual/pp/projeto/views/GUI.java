package pt.ual.pp.projeto.views;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ual.pp.projeto.controllers.Controller;


public class GUI extends Application {

    @FXML
    public ProgressBar progressBar;

    public TextField simulationTime;

    public TextField model1MinDay;
    public TextField model1MaxDay;

    public TextField model2MinDay;
    public TextField model2MaxDay;

    public TextField model3MinDay;
    public TextField model3MaxDay;

    public TextField zone1NumberOfLines;
    public TextField zone2NumberOfLines;
    public TextField zone3NumberOfLines;

    public TextField model1Zone1AverageTime;
    public TextField model1Zone2AverageTime;
    public TextField model1Zone3AverageTime;
    public TextField model1Zone4AverageTime;
    public TextField model1Zone5AverageTime;
    public TextField model1Zone1Order;
    public TextField model1Zone2Order;
    public TextField model1Zone3Order;
    public TextField model1Zone4Order;
    public TextField model1Zone5Order;

    public TextField model2Zone1AverageTime;
    public TextField model2Zone2AverageTime;
    public TextField model2Zone3AverageTime;
    public TextField model2Zone4AverageTime;
    public TextField model2Zone5AverageTime;
    public TextField model2Zone1Order;
    public TextField model2Zone2Order;
    public TextField model2Zone3Order;
    public TextField model2Zone4Order;
    public TextField model2Zone5Order;

    public TextField model3Zone1AverageTime;
    public TextField model3Zone2AverageTime;
    public TextField model3Zone3AverageTime;
    public TextField model3Zone4AverageTime;
    public TextField model3Zone5AverageTime;
    public TextField model3Zone1Order;
    public TextField model3Zone2Order;
    public TextField model3Zone3Order;
    public TextField model3Zone4Order;
    public TextField model3Zone5Order;


    @Override
    public void start(Stage primaryStage) throws Exception {
        progressBar();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Projecto Paradigmas Programação");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.show();
    }

    //FIXME Progress bar doesnt work
    public void progressBar() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                progressBar.setProgress(i/100);
            }
        }
        ).start();
    }


    public void startSimulation(){
        System.out.println("Simulacao iniciada");

        //Initialize Controller
        Controller controller = new Controller();

        //Choose simulation time
        String tempString = simulationTime.getText();
        controller.setSimulationTime(tempString);


        //Choose min/max days--------------------------------------------------------------------------------------------------------------------
        //Min
        controller.setCarGeneratorSetMinDay("1", Integer.parseInt(model1MinDay.getText()));
        //controller.setCarGeneratorSetMinDay("2", Integer.parseInt(model2MinDay.getText()));
        //controller.setCarGeneratorSetMinDay("3", Integer.parseInt(model3MinDay.getText()));

        //Max
        controller.setCarGeneratorSetMaxDay("1", Integer.parseInt(model1MaxDay.getText()));
        //controller.setCarGeneratorSetMaxDay("2", Integer.parseInt(model2MaxDay.getText()));
        //controller.setCarGeneratorSetMaxDay("3", Integer.parseInt(model3MaxDay.getText()));
        //----------------------------------------------------------------------------------------------------------------------------------------

        //Choose number of lines per zone---------------------------------------------------------------------------------------------------------
        //controller.
        //controller.
        //controller.
        //----------------------------------------------------------------------------------------------------------------------------------------

        //Choose Model Order and Average time by Zone---------------------------------------------------------------------------------------------
        //Average Time


        //Order

        //----------------------------------------------------------------------------------------------------------------------------------------


        //Start simulation
        controller.startSimulation();


        System.out.println("Simulação decorrida em " + tempString + " segundos.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}