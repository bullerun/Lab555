package me.lab5.Command;

import me.lab5.Exception.MustBeEmptyException;

import java.util.ArrayList;
/**
 * command outputs the last 9 entered commands
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class HistoryCommand extends AbstractCommand {
    private ArrayList<String> lastCommands;
    public HistoryCommand(ArrayList<String> lastCommands) {
        super("history", "выводит последние 9 команд (без их аргументов)");
        this.lastCommands = lastCommands;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            for (int i = lastCommands.size() -1; i >= 0; i--) {
                System.out.println(lastCommands.get(i));
            }
            return true;
        }catch (MustBeEmptyException e) {
            System.out.println("Команда вводится без аргумента");
            return false;
        }
    }
}
