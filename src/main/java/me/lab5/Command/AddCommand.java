package me.lab5.Command;

import me.lab5.Data.LabWork;
import me.lab5.Exception.IncorrectScript;
import me.lab5.Manager.CollectionManager;
import me.lab5.Run.RunMode;
import me.lab5.Utility.LabAsk;

import java.time.LocalDate;

public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private LabAsk labAsk;
    private RunMode runMode;


    public AddCommand(CollectionManager collectionManager, LabAsk labAsk, RunMode runMode) {
        super("add", "добавляет новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.labAsk = labAsk;
        this.runMode = runMode;
    }

    @Override
    public boolean execute(String argument) {
        try {
//            new LabWork(
//                    collectionManager.generateNextId(),
//                    labAsk.nameAsk(),
//                    labAsk.coordinatesAsk(),
//                    LocalDate.now(),
//                    labAsk.minimalPointAsk(),
//                    labAsk.difficultyAsk(),
//                    labAsk.disciplineAsk())
            collectionManager.addToCollection(labAsk.addLabWork(collectionManager.generateNextId()));
            return true;
        }catch (IncorrectScript e){
            return false;
        }
    }
}
