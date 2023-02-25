package me.lab5.Command;

import me.lab5.Data.LabWork;
import me.lab5.Exception.MustBeNotEmptyException;
import me.lab5.Manager.CollectionManager;

public class RemoveByIdCommand extends AbstractCommand {
    CollectionManager collectionManager;
    public RemoveByIdCommand(CollectionManager collectionManager) {
            super("remove_by_id id", "удаляет элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new MustBeNotEmptyException();
            long removeLabWorkId = Long.parseLong(argument.trim());
            LabWork removeLabWork = collectionManager.getElementById(removeLabWorkId);
            if(removeLabWork == null) throw new NullPointerException();
            collectionManager.removeLabWork(removeLabWork);
            System.out.println("Лабораторная работа успешно удалена");
            return true;
        } catch (MustBeNotEmptyException e){
            System.out.println("Id не введен");
            return false;
        } catch (NullPointerException e){
            System.out.println("Лабораторной работы с таким Id отсутствует");
            return false;
        }
    }
}
