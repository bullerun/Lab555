package me.lab5.Run;

import me.lab5.Manager.*;
import me.lab5.Utility.Console;
import me.lab5.Utility.FileHanding;
import me.lab5.Utility.LabAsk;
import me.lab5.Utility.RunMode;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String envVariable = System.getenv("LAB");
        Scanner scanner = new Scanner(System.in);
        CollectionManager collectionManager = new CollectionManager();
        RunMode runMode = new RunMode();
        LabAsk labAsk = new LabAsk(scanner);
        Console console = new Console(scanner);
        FileHanding fileHanding = new FileHanding(collectionManager, labAsk, envVariable, runMode, console);
        CommandManager commandManager = new CommandManager(collectionManager, fileHanding, labAsk);
        console.setCommandManager(commandManager);
        fileHanding.setCommandManager(commandManager);
        fileHanding.xmlFileReader();
        console.consoleReader();
    }
}