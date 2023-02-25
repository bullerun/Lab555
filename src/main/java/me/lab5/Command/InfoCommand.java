package me.lab5.Command;

import me.lab5.Exception.MustBeEmptyException;
import me.lab5.Manager.CollectionManager;

public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            System.out.println("Тип " + collectionManager.getLabWork().getClass());
            System.out.println("Количество элементов: " + collectionManager.getLabWork().size());
            System.out.println("Дата инициализации: " + collectionManager.getCreatingCollection());
            return true;
        } catch (MustBeEmptyException e) {
            System.out.println("Команда вводится без аргумента");
            return false;
        }
    }
}
