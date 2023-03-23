package me.lab5.Run;
import me.lab5.Command.*;
import me.lab5.Manager.*;
import me.lab5.Utility.Console;
import me.lab5.Utility.FileHanding;
import me.lab5.Utility.LabAsk;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        String envVariable = System.getenv("LAB");
        Scanner scanner = new Scanner(System.in);
        CollectionManager collectionManager = new CollectionManager();
        LabAsk labAsk = new LabAsk(scanner);
        Console console = new Console(scanner);
        FileHanding fileHanding = new FileHanding(collectionManager, labAsk, envVariable);
        RunMode runMode = new RunMode(console, fileHanding);
        labAsk.setRunMode(runMode);
        CommandManager commandManager = new CommandManager(collectionManager, fileHanding, runMode, labAsk);
        console.setCommandManager(commandManager);
        fileHanding.setCommandManager(commandManager);
        fileHanding.setFileType(FileHanding.FileType.XML_FILE);
        runMode.setMode(RunModeEnum.FILE_MODE);//установка режима работы
        runMode.operatingModeSetting(); // нужно понять когда устанавливать
        console.consoleReader();


    }
}