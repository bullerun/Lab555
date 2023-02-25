package me.lab5.Command;

import me.lab5.Exception.MustBeEmptyException;

public class HistoryCommand extends AbstractCommand {
    public HistoryCommand() {
        super("history", "выводит последние 9 команд (без их аргументов)");
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
