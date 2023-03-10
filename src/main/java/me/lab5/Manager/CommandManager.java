package me.lab5.Manager;

import me.lab5.Command.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    private final int numberOfRecentCommands = 9;
    private List<Command> commandsForHelpCommand = new ArrayList<>();
    private String[] lastCommands = new String[numberOfRecentCommands];
    private final Command helpCommand;
    private final Command infoCommand;
    private final Command historyCommand;
    private final Command showCommand;
    private final Command addCommand;
    private final Command removeCommand;
    private final Command updateCommand;
    private final Command sumOfMinimalPoint;
    private final Command averageOfMinimalPoint;
    private final Command clearCommand;
    private final Command exitCommand;
    private final Command executeScriptCommand;
    private final Command printFieldDescendingDiscipline;
    private final Command removeGreater;
    Map<String, Command> commands = new HashMap<>();
    private RemoveLowerCommand removeLowerCommand;
    private SaveCommand saveCommand;

    public CommandManager(Command helpCommand, Command infoCommand, Command historyCommand,
                          Command showCommand, Command addCommand, Command removeCommand, Command updateCommand,
                          Command sumOfMinimalPoint, Command averageOfMinimalPoint, Command clearCommand, Command exitCommand,
                          Command executeScriptCommand,
                          PrintFieldDescendingDisciplineCommand printFieldDescendingDisciplineCommand,
                          RemoveGreaterCommand removeGreaterCommand, RemoveLowerCommand removeLowerCommand, SaveCommand saveCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.historyCommand = historyCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.removeCommand = removeCommand;
        this.updateCommand = updateCommand;
        this.sumOfMinimalPoint = sumOfMinimalPoint;
        this.averageOfMinimalPoint = averageOfMinimalPoint;
        this.clearCommand = clearCommand;
        this.exitCommand = exitCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.printFieldDescendingDiscipline = printFieldDescendingDisciplineCommand;
        this.removeGreater = removeGreaterCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.saveCommand = saveCommand;

        commandsForHelpCommand.add(helpCommand);
        commandsForHelpCommand.add(infoCommand);
        commandsForHelpCommand.add(historyCommand);
        commandsForHelpCommand.add(showCommand);
        commandsForHelpCommand.add(addCommand);
        commandsForHelpCommand.add(removeCommand);
        commandsForHelpCommand.add(updateCommand);
        commandsForHelpCommand.add(sumOfMinimalPoint);
        commandsForHelpCommand.add(averageOfMinimalPoint);
        commandsForHelpCommand.add(clearCommand);
        commandsForHelpCommand.add(exitCommand);
        commandsForHelpCommand.add(executeScriptCommand);
        commandsForHelpCommand.add(printFieldDescendingDisciplineCommand);
        commandsForHelpCommand.add(removeGreaterCommand);
        commandsForHelpCommand.add(removeLowerCommand);
        commandsForHelpCommand.add(saveCommand);


        commands.put("help", helpCommand);
        commands.put("info", infoCommand);
        commands.put("history", historyCommand);
        commands.put("show", showCommand);
        commands.put("add", addCommand);
        commands.put("remove_by_id", removeCommand);
        commands.put("update", updateCommand);
        commands.put("sum_of_minimal_point", sumOfMinimalPoint);
        commands.put("average_of_minimal_point", averageOfMinimalPoint);
        commands.put("clear", clearCommand);
        commands.put("execute_script", executeScriptCommand);
        commands.put("print_field_descending_discipline", printFieldDescendingDisciplineCommand);
        commands.put("remove_greater", removeGreaterCommand);
        commands.put("remove_lower", removeLowerCommand);
        commands.put("save", saveCommand);
        commands.put("exit", exitCommand);
    }

    public void commandSelection(String[] command) {
        Command commandSelect = commands.get(command[0]);
        if (commandSelect != null) {
            if (commandSelect.getName().equals("help")) if (help(command[1])) {
                addCommandHistory(command[0]);
            } else if (commandSelect.getName().equals("history")) if (history(command[1])) {
                addCommandHistory(command[0]);
            } else if (commandSelect.execute(command[1])) {
                addCommandHistory(command[0]);
            }
        }
        commandNotFound();
    }

    public boolean help(String arg) {
        if (helpCommand.execute(arg)) {
            for (Command i : commandsForHelpCommand) {
                System.out.println("Команда " + i.getName() + " " + i.getDescription());
            }
            return true;
        }
        return false;
    }

    public boolean history(String arg) {
        if (historyCommand.execute(arg)) {
            for (int i = 0; i < numberOfRecentCommands; i++) {
                if (lastCommands[i] != null) System.out.println(lastCommands[i]);
            }
            return true;
        }
        return false;
    }

    public void commandNotFound() {
        System.out.println("команда не найдена, введите команду help, чтобы получить инструкции");
    }

    public void addCommandHistory(String command) {
        for (int i = numberOfRecentCommands - 1; i > 0; i--) {
            lastCommands[i] = lastCommands[i - 1];
        }
        lastCommands[0] = command;
    }
}
