package me.lab5.Utility;

import me.lab5.Manager.CommandManager;

import java.util.Scanner;

public class Console {
    private CommandManager commandManager;
    private static String[] command;
    private Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }
    public void consoleReader() {
        int commandStatus;
        do {
            command = (scanner.nextLine().trim() + " ").split(" ", 2);
            commandStatus = commandSelection(command);
            if (commandStatus == 1) {
                commandManager.addCommandHistory(command[0]);
            }
        } while (commandStatus != 2);
        System.exit(0);
    }

    protected int commandSelection(String[] command) {
        switch (command[0]) {
            case "":
                break;
            case "help":
                if (commandManager.help(command[1])) return 1;
                break;
            case "info":
                if (commandManager.info(command[1])) return 1;
                break;
            case "history":
                if (commandManager.history(command[1])) return 1;
                break;
            case "show":
                if (commandManager.show(command[1])) return 1;
                break;
            case "add":
                if (commandManager.add(command[1])) return 1;
                break;
            case "remove_by_id":
                if (commandManager.removeById(command[1])) return 1;
                break;
            case "update":
                if (commandManager.updateById(command[1])) return 1;
                break;
            case "sum_of_minimal_point":
                if (commandManager.sumOfMinimalPoint(command[1])) return 1;
                break;
            case "average_of_minimal_point":
                if (commandManager.averageOfMinimalPoint(command[1])) return 1;
                break;
            case "clear":
                if (commandManager.clear(command[1])) return 1;
                break;
            case "execute_script":
                if (commandManager.executeScript(command[1])) return 1;
                break;
            case "print_field_descending_discipline":
                if (commandManager.printFieldDescendingDiscipline(command[1])) return 1;
                break;
            case "remove_greater":
                if (commandManager.removeGreater(command[1])) return 1;
                break;
            case "remove_lower":
                if (commandManager.removeLower(command[1])) return 1;
                break;
            case "save":
                if (commandManager.save(command[1])) return 1;
                break;
            case "exit":
                if (commandManager.exit(command[1])) return 2;
                break;
            default:
                if (commandManager.commandNotFound()) return 0;
        }
        return 0;
    }
    public void setCommandManager(CommandManager commandManager){
        this.commandManager = commandManager;
    }


}
