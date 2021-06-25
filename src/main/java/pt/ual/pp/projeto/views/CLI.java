package pt.ual.pp.projeto.views;

import pt.ual.pp.projeto.controllers.Controller;

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

        controller.setCarGeneratorSetMinDay("1", "3");
        controller.setCarGeneratorSetMaxDay("1", "7");

        controller.setCarGeneratorSetMinDay("2", "4");
        controller.setCarGeneratorSetMaxDay("2", "6");

        controller.setCarGeneratorSetMinDay("3", "2");
        controller.setCarGeneratorSetMaxDay("3", "5");

        controller.startSimulation();
        System.out.println("Simulação terminada.");
    }
}
