package me.lab5.Command;

import me.lab5.Exception.MustBeEmptyException;

public class HelpCommand extends AbstractCommand {
    public HelpCommand() {
        super("help", "выводит справку по доступным командам");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            return true;
        }catch (MustBeEmptyException e) {
            System.out.println("Команда вводится без аргумента");
            return false;
        }
    }
}
