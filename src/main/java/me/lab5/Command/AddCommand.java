package me.lab5.Command;

import me.lab5.Manager.CollectionManager;
import me.lab5.Utility.LabAsk;

/**
 * add lab command
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private LabAsk labAsk;


    public AddCommand(CollectionManager collectionManager, LabAsk labAsk) {
        super("add", "добавляет новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.labAsk = labAsk;
    }

    @Override
    public boolean execute(String argument) {
        collectionManager.addToCollection(labAsk.addLabWork(collectionManager.generateNextId()));
        System.out.println("Лабораторная успешно добавлена");
        return true;
    }
}
