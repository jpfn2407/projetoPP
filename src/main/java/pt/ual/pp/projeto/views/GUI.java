package pt.ual.pp.projeto.views;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import pt.ual.pp.projeto.controllers.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


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

    public TextArea averageBuildTimeOutput;
    public TextArea averageWaitTimeOutput;
    public TextArea averageUsagePercentageOutput;

    public CheckBox erlangToggle;

    double progress;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Projecto Paradigmas Programação");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    public void startSimulation() {

        ExecutorService executorService = Executors.newCachedThreadPool();

        //Initialize Controller
        Controller controller = new Controller();

        //Choose simulation time
        String tempString = simulationTime.getText();
        controller.setSimulationTime(tempString);

        /*executorService.submit(() -> {
            double counter = 0.0;
            while(counter <= 1){
                progressBar.progressProperty().setValue(counter);
                try {
                    TimeUnit.MICROSECONDS.sleep(Math.round(0.001 * (Double.valueOf(tempString)) * 1_000_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter += 0.001;
            }
        });
*/
        //Choose min/max days-----------------------------------------------------------------------------------------------------------------------------------------
        //Model 1
        controller.setCarGeneratorSetMinDay("1", String.valueOf(model1MinDay.getText()));
        controller.setCarGeneratorSetMaxDay("1", String.valueOf(model1MaxDay.getText()));

        //Model 2
        controller.setCarGeneratorSetMinDay("2", String.valueOf(model2MinDay.getText()));
        controller.setCarGeneratorSetMaxDay("2", String.valueOf(model2MaxDay.getText()));

        //Model 3
        controller.setCarGeneratorSetMinDay("3", String.valueOf(model3MinDay.getText()));
        controller.setCarGeneratorSetMaxDay("3", String.valueOf(model3MaxDay.getText()));
        //-----------------------------------------------------------------------------------------------------------------------------------------------------------

        //Choose Model Order and Average time by Zone----------------------------------------------------------------------------------------------------------------
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
        //-----------------------------------------------------------------------------------------------------------------------------------------------------------

        //Choose number of lines per zone----------------------------------------------------------------------------------------------------------------------------
        controller.setZoneNumberOfLines("1", String.valueOf(zone1NumberOfLines.getText()));
        controller.setZoneNumberOfLines("2", String.valueOf(zone2NumberOfLines.getText()));
        controller.setZoneNumberOfLines("3", String.valueOf(zone3NumberOfLines.getText()));
        controller.setZoneNumberOfLines("4", String.valueOf(zone4NumberOfLines.getText()));
        controller.setZoneNumberOfLines("5", String.valueOf(zone5NumberOfLines.getText()));
        //-----------------------------------------------------------------------------------------------------------------------------------------------------------

        //Enable Erlang----------------------------------------------------------------------------------------------------------------------------------------------
        controller.setUseErlang(erlangToggle.isSelected());


        //Start simulation
        executorService.submit(() -> {
            controller.startSimulation();
            double counter = 0.0;
            while(counter <= 1) {
                progressBar.progressProperty().setValue(counter);
                try {
                    TimeUnit.MICROSECONDS.sleep(Math.round(0.001 * (Double.valueOf(tempString)) * 1_000_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter += 0.001;
            }

            //Output results
            //Average Build Time-----------------------------------------------------------------------------------------------------------------------------------------
            String output1FinalText = "Tempo médio que cada tipo de modelo demora a ser construido: \n\n";

            HashMap<String, Double> averageBuildTime = controller.getModelAverageBuildTime();
            for(String key : averageBuildTime.keySet()){
                output1FinalText += ("M"+ key + ": " + averageBuildTime.get(key) + " horas.\n");
            }

            averageBuildTimeOutput.setText(output1FinalText);
            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            //Average Wait Time------------------------------------------------------------------------------------------------------------------------------------------
            String outputAverageWaitTimeOutput = "Tempo médio que cada tipo de modelo fica em espera: \n\n";

            HashMap<String, Double> averageWaitTime = controller.getAverageWaitTime();
            for(String key : averageWaitTime.keySet()){
                outputAverageWaitTimeOutput += ("M" + key + ": " + averageWaitTime.get(key) + " horas.\n");
            }

            averageWaitTimeOutput.setText(outputAverageWaitTimeOutput);
            //-----------------------------------------------------------------------------------------------------------------------------------------------------------

            //Average Usage Percentage-----------------------------------------------------------------------------------------------------------------------------------
            String outputAverageUsagePercentageOutput = "Percentagem de tempo de utilização de cada linha de trabalho: \n\n";

            HashMap<String, ArrayList<Double>> linesAverages = controller.getLinesAverages();
            for(String key : linesAverages.keySet()) {
                int line = 1;
                for (Double value : linesAverages.get(key)) {
                    outputAverageUsagePercentageOutput += ("Z" + key + " L" + line + ": " + value + "%\n");
                    line++;
                }
                outputAverageUsagePercentageOutput += "\n";
            }

            averageUsagePercentageOutput.setText(outputAverageUsagePercentageOutput);
            //-----------------------------------------------------------------------------------------------------------------------------------------------------------
        });


    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        progressBar.setStyle("-fx-accent: green;");

    }

}