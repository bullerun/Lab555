package me.lab5.Manager;

import me.lab5.Command.*;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final int numberOfRecentCommands = 9;
    private List<Command> commands = new ArrayList<>();
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
    private RemoveLowerCommand removeLowerCommand;
    private SaveCommand saveCommand;

    public CommandManager(Command helpCommand, Command infoCommand, Command historyCommand,
                          Command showCommand, Command addCommand, Command removeCommand, Command updateCommand,
                          Command sumOfMinimalPoint, Command averageOfMinimalPoint, Command clearCommand, Command exitCommand,
                          Command executeScriptCommand,
                          PrintFieldDescendingDisciplineCommand printFieldDescendingDisciplineCommand,
                          RemoveGreaterCommand removeGreaterCommand, RemoveLowerCommand removeLowerCommand, SaveCommand saveCommand){
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

        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(historyCommand);
        commands.add(showCommand);
        commands.add(addCommand);
        commands.add(removeCommand);
        commands.add(updateCommand);
        commands.add(sumOfMinimalPoint);
        commands.add(averageOfMinimalPoint);
        commands.add(clearCommand);
        commands.add(exitCommand);
        commands.add(executeScriptCommand);
        commands.add(printFieldDescendingDisciplineCommand);
        commands.add(removeGreaterCommand);
        commands.add(removeLowerCommand);
        commands.add(saveCommand);
    }

    public List<Command> getCommands() {
        return commands;
    }

    public boolean help(String arg) {
        if (helpCommand.execute(arg)) {
            for (Command i : commands) {
                System.out.println("Команда " + i.getName() + " " + i.getDescription());
            }
            return true;
        }
        return false;
    }

    public boolean info(String arg) {return infoCommand.execute(arg);}

    public boolean history(String arg) {
        if (historyCommand.execute(arg)) {
            for (int i = 0; i < numberOfRecentCommands; i++) {
                if (lastCommands[i] != null) System.out.println(lastCommands[i]);
            }
            return true;
        }
        return false;
    }

    public boolean show(String arg) {return showCommand.execute(arg);}
    public boolean add(String arg) {return addCommand.execute(arg);}
    public boolean removeById(String arg) {return removeCommand.execute(arg);}
    public boolean updateById(String arg) {return updateCommand.execute(arg);}
    public boolean sumOfMinimalPoint(String arg){return sumOfMinimalPoint.execute(arg);}
    public boolean averageOfMinimalPoint(String arg){return averageOfMinimalPoint.execute(arg);}
    public boolean clear(String arg){return clearCommand.execute(arg);}
    public boolean exit(String arg) {return exitCommand.execute(arg);}
    public boolean executeScript(String arg){return executeScriptCommand.execute(arg);}
    public boolean printFieldDescendingDiscipline(String arg){return printFieldDescendingDiscipline.execute(arg);}
    public boolean removeGreater(String arg){return removeGreater.execute(arg);}
    public boolean removeLower(String arg){return removeLowerCommand.execute(arg);}
    public boolean save(String arg){return saveCommand.execute(arg);}
    public boolean commandNotFound() {
        System.out.println("команда не найдена, введите команду help, чтобы получить инструкции");
        return true;
    }

    public void addCommandHistory(String command) {
        for (int i = numberOfRecentCommands - 1; i > 0; i--) {
            lastCommands[i] = lastCommands[i - 1];
        }
        lastCommands[0] = command;
    }
}
