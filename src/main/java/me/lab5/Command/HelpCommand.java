package me.lab5.Command;

import me.lab5.Exception.MustBeEmptyException;

import java.util.ArrayList;
import java.util.List;
/**
 * command outputs help on
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class HelpCommand extends AbstractCommand {
    private ArrayList<Command> commandsForHelpCommand;

    public HelpCommand(ArrayList<Command> commandsForHelpCommand) {
        super("help", "выводит справку по доступным командам");
        this.commandsForHelpCommand = commandsForHelpCommand;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            for (Command i : commandsForHelpCommand) {
                System.out.println("Команда " + i.getName() + " " + i.getDescription());
            }
            return true;
        } catch (MustBeEmptyException e) {
            System.out.println("Команда вводится без аргумента");
            return false;
        }
    }
}
