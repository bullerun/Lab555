package me.lab5.Utility;
import me.lab5.Manager.CommandManager;
import java.util.Scanner;
/**
 * console input processing class
 * @author Nikita and Vlad
 * @version 0.1
 */
public class Console {
    private CommandManager commandManager;
    private static String[] command;
    private Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public void consoleReader() {
        while (true) {
            command = (scanner.nextLine().trim() + " ").split(" ", 2);
            if (!command[0].equals("")) {
                commandManager.commandSelection(command);
            }
        }

    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }


}
