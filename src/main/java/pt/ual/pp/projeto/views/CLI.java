package pt.ual.pp.projeto.views;

import pt.ual.pp.projeto.controllers.Controller;

import java.util.Scanner;

public class CLI {
    public CLI() {
        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tempo de simulação:");
        String line = scanner.nextLine();
        if (line == "") return;

        String[] commands = line.split(" ");
        controller.setSimulationTime(commands[0]);
        controller.startSimulation();
    }
}
