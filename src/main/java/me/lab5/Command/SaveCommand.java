package me.lab5.Command;

import me.lab5.Exception.MustBeEmptyException;
import me.lab5.Manager.CollectionManager;
import me.lab5.Utility.FileHanding;

/**
 * command of saving laboratory work to a file
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class SaveCommand extends AbstractCommand {
    private FileHanding fileHanding;
    private CollectionManager collectionManager;

    public SaveCommand(FileHanding fileHanding, CollectionManager collectionManager) {
        super("save", "сохраняет коллекцию в файл");
        this.fileHanding = fileHanding;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new MustBeEmptyException();
            fileHanding.xmlFileWrite(collectionManager);
            System.out.println("Коллекция сохранена");
            return true;
        } catch (MustBeEmptyException e) {
            System.out.println("Команда вводится без аргумента");
            return false;
        }
    }
}