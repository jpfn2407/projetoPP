package pt.ual.pp.projeto.views;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import pt.ual.pp.projeto.controllers.Controller;

import java.net.URL;
import java.util.ResourceBundle;


public class GUI extends Application implements Initializable {

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
    public TextField zone4NumberOfLines;
    public TextField zone5NumberOfLines;

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

    public TextField averageBuildTimeOutput;
    public TextField averageWaitTimeOutput;
    public TextField averageUsagePercentageOutput;

    public CheckBox erlangToggle;

    double progress;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Projecto Paradigmas Programação");
        primaryStage.setScene(new Scene(root, 733, 800));
        primaryStage.show();
    }

    public void startSimulation() {
        System.out.println("Simulacao iniciada");

        increaseProgress();
        increaseProgress();
        increaseProgress();

        //Initialize Controller
        Controller controller = new Controller();

        //Choose simulation time
        String tempString = simulationTime.getText();
        controller.setSimulationTime(tempString);


        //Choose min/max days-------------------------------------------------------------------------------------------------------------------
        //Model 1
        controller.setCarGeneratorSetMinDay("1", String.valueOf(model1MinDay.getText()));
        controller.setCarGeneratorSetMaxDay("1", String.valueOf(model1MaxDay.getText()));

        //Model 2
        controller.setCarGeneratorSetMinDay("2", String.valueOf(model2MinDay.getText()));
        controller.setCarGeneratorSetMaxDay("2", String.valueOf(model2MaxDay.getText()));

        //Model 3
        controller.setCarGeneratorSetMinDay("3", String.valueOf(model3MinDay.getText()));
        controller.setCarGeneratorSetMaxDay("3", String.valueOf(model3MaxDay.getText()));
        //--------------------------------------------------------------------------------------------------------------------------------------

        //Choose Model Order and Average time by Zone-------------------------------------------------------------------------------------------
        //Model 1
        controller.addSequenceInfo("1", String.valueOf(model1Zone1Order.getText()), "1", String.valueOf(model1Zone1AverageTime.getText()));
        controller.addSequenceInfo("1", String.valueOf(model1Zone2Order.getText()), "2", String.valueOf(model1Zone2AverageTime.getText()));
        controller.addSequenceInfo("1", String.valueOf(model1Zone3Order.getText()), "3", String.valueOf(model1Zone3AverageTime.getText()));
        controller.addSequenceInfo("1", String.valueOf(model1Zone4Order.getText()), "4", String.valueOf(model1Zone4AverageTime.getText()));
        controller.addSequenceInfo("1", String.valueOf(model1Zone5Order.getText()), "5", String.valueOf(model1Zone5AverageTime.getText()));

        //Model 2
        controller.addSequenceInfo("2", String.valueOf(model2Zone1Order.getText()), "1", String.valueOf(model2Zone1AverageTime.getText()));
        controller.addSequenceInfo("2", String.valueOf(model2Zone2Order.getText()), "2", String.valueOf(model2Zone2AverageTime.getText()));
        controller.addSequenceInfo("2", String.valueOf(model2Zone3Order.getText()), "3", String.valueOf(model2Zone3AverageTime.getText()));
        controller.addSequenceInfo("2", String.valueOf(model2Zone4Order.getText()), "4", String.valueOf(model2Zone4AverageTime.getText()));
        controller.addSequenceInfo("2", String.valueOf(model2Zone5Order.getText()), "5", String.valueOf(model2Zone5AverageTime.getText()));

        //Model 3
        controller.addSequenceInfo("3", String.valueOf(model3Zone1Order.getText()), "1", String.valueOf(model3Zone1AverageTime.getText()));
        controller.addSequenceInfo("3", String.valueOf(model3Zone2Order.getText()), "2", String.valueOf(model3Zone2AverageTime.getText()));
        controller.addSequenceInfo("3", String.valueOf(model3Zone3Order.getText()), "3", String.valueOf(model3Zone3AverageTime.getText()));
        controller.addSequenceInfo("3", String.valueOf(model3Zone4Order.getText()), "4", String.valueOf(model3Zone4AverageTime.getText()));
        controller.addSequenceInfo("3", String.valueOf(model3Zone5Order.getText()), "5", String.valueOf(model3Zone5AverageTime.getText()));
        //--------------------------------------------------------------------------------------------------------------------------------------

        //Choose number of lines per zone-------------------------------------------------------------------------------------------------------
        controller.setZoneNumberOfLines("1", String.valueOf(zone1NumberOfLines.getText()));
        controller.setZoneNumberOfLines("2", String.valueOf(zone2NumberOfLines.getText()));
        controller.setZoneNumberOfLines("3", String.valueOf(zone3NumberOfLines.getText()));
        controller.setZoneNumberOfLines("4", String.valueOf(zone4NumberOfLines.getText()));
        controller.setZoneNumberOfLines("5", String.valueOf(zone5NumberOfLines.getText()));
        //--------------------------------------------------------------------------------------------------------------------------------------


        //Start simulation
        controller.startSimulation();


        System.out.println("Simulação decorrida em " + tempString + " segundos.");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        progressBar.setStyle("-fx-accent: green;");

    }

    public void increaseProgress() {
        progress += 0.25;
        progressBar.setProgress(progress);
    }
}