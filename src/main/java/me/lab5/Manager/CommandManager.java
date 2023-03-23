package me.lab5.Manager;

import me.lab5.Command.*;
import me.lab5.Run.RunMode;
import me.lab5.Utility.FileHanding;
import me.lab5.Utility.LabAsk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    ArrayList<Command> commandsForHelpCommand = new ArrayList<>();
    ArrayList<String> lastCommands = new ArrayList<>();
    Map<String, Command> commands = new HashMap<>();

    public CommandManager(CollectionManager collectionManager, FileHanding fileHanding, RunMode runMode, LabAsk labAsk) {
        commandsForHelpCommand.add(new InfoCommand(collectionManager));
        commandsForHelpCommand.add(new HistoryCommand(lastCommands));
        commandsForHelpCommand.add(new ShowCommand(collectionManager));
        commandsForHelpCommand.add(new AddCommand(collectionManager, labAsk));
        commandsForHelpCommand.add(new RemoveByIdCommand(collectionManager));
        commandsForHelpCommand.add(new UpdateByIdCommand(collectionManager, labAsk));
        commandsForHelpCommand.add(new SumOfMinimalPointCommand(collectionManager));
        commandsForHelpCommand.add(new AverageOfMinimalPointCommand(collectionManager));
        commandsForHelpCommand.add(new ClearCommand(collectionManager));
        commandsForHelpCommand.add(new ExitCommand());
        commandsForHelpCommand.add(new ExecuteScriptCommand(fileHanding));
        commandsForHelpCommand.add(new PrintFieldDescendingDisciplineCommand(collectionManager));
        commandsForHelpCommand.add(new RemoveGreaterCommand(collectionManager));
        commandsForHelpCommand.add(new RemoveLowerCommand(collectionManager));
        commandsForHelpCommand.add(new SaveCommand(fileHanding, collectionManager));
        commandsForHelpCommand.add(new HelpCommand(commandsForHelpCommand));

        commands.put("help", new HelpCommand(commandsForHelpCommand));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("history", new HistoryCommand(lastCommands));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager, labAsk));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("update", new UpdateByIdCommand(collectionManager, labAsk));
        commands.put("sum_of_minimal_point", new SumOfMinimalPointCommand(collectionManager));
        commands.put("average_of_minimal_point", new AverageOfMinimalPointCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(fileHanding));
        commands.put("print_field_descending_discipline", new PrintFieldDescendingDisciplineCommand(collectionManager));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commands.put("save", new SaveCommand(fileHanding, collectionManager));
        commands.put("exit", new ExitCommand());

    }

    public void commandSelection(String[] command) {
        Command commandSelect = commands.get(command[0]);
        if (commandSelect != null) {
            if (commandSelect.execute(command[1])) {
                addCommandHistory(command[0]);
            }
        } else {
            commandNotFound();
        }
    }


    public void commandNotFound() {
        System.out.println("команда не найдена, введите команду help, чтобы получить инструкции");
    }

    public void addCommandHistory(String command) {
        lastCommands.add(command);
        if (lastCommands.size() > 9) {
            lastCommands.remove(0);
        }
    }
}
