package pt.ual.pp.projeto.views;

import pt.ual.pp.projeto.controllers.Controller;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Scanner;

public class CLI {
    public CLI() {
        Controller controller = new Controller();

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Tempo de simulação:");
        String line = scanner.nextLine();
        if (line == "") return;

        String[] commands = line.split(" ");
        controller.setSimulationTime(commands[0]);
        controller.startSimulation();*/

        System.out.println("Começando a simulação.");
        controller.setSimulationTime("3");

        //Car generator days
        //Modelo 1
        controller.setCarGeneratorSetMinDay("1", "3");
        controller.setCarGeneratorSetMaxDay("1", "7");
        //Modelo 2
        /*controller.setCarGeneratorSetMinDay("2", "4");
        controller.setCarGeneratorSetMaxDay("2", "6");
        //Modelo 3
        controller.setCarGeneratorSetMinDay("3", "2");
        controller.setCarGeneratorSetMaxDay("3", "5");*/

        //Car generator sequences / Tabela 4 do enunciado
        //Modelo 1
        controller.addSequenceInfo("1", "2", "1", "0.8");
        controller.addSequenceInfo("1", "", "2", "");
        controller.addSequenceInfo("1", "3", "3", "0.75");
        controller.addSequenceInfo("1", "1", "4", "1.1");
        controller.addSequenceInfo("1", "", "5", "");
        //Modelo 2
        controller.addSequenceInfo("2", "2", "1", "0.6");
        controller.addSequenceInfo("2", "3", "2", "0.85");
        controller.addSequenceInfo("2", "1", "3", "0.5");
        controller.addSequenceInfo("2", "", "4", "");
        controller.addSequenceInfo("2", "4", "5", "0.5");
        //Modelo 3
        controller.addSequenceInfo("3", "3", "1", "0.7");
        controller.addSequenceInfo("3", "1", "2", "1.2");
        controller.addSequenceInfo("3", "5", "3", "1.0");
        controller.addSequenceInfo("3", "4", "4", "0.9");
        controller.addSequenceInfo("3", "2", "5", "0.25");

        //Define o numero de linhas por zona
        controller.setZoneNumberOfLines("1", "3");
        controller.setZoneNumberOfLines("2", "2");
        controller.setZoneNumberOfLines("3", "4");
        controller.setZoneNumberOfLines("4", "3");
        controller.setZoneNumberOfLines("5", "1");

        controller.startSimulation();

        HashMap<String, Double> averageBuildTime = controller.getModelAverageBuildTime();
        for(String key : averageBuildTime.keySet()){
            System.out.println("M"+ key + ": " + averageBuildTime.get(key));
        }

        System.out.println("Simulação terminada.");
    }
}
