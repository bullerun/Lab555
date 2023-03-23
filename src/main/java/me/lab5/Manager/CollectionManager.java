package me.lab5.Manager;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import me.lab5.Data.LabWork;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;
public class CollectionManager {
    private NavigableSet<LabWork> labWorks = new TreeSet<>();
    private LocalDate creatingCollection;

    public CollectionManager() {
        creatingCollection = LocalDate.now();
    }

    public void addToCollection(LabWork newLab) {
        labWorks.add(newLab);
        System.out.println("Лабораторная успешно добавлена");
    }

    public long generateNextId() {
        if (labWorks.isEmpty()) return 1L;
        return labWorks.last().getId() + 1;
    }

    public NavigableSet<LabWork> getLabWork() {
        return labWorks;
    }
    public void clearCollection(){
        this.labWorks = new TreeSet<>();
        System.out.println("Коллекция очищена");
    }

    public void removeLabWork(LabWork removeLabWork) {
        labWorks.remove(removeLabWork);
    }
    public void removeGreater(LabWork removeLabWork) {
        Iterator<LabWork> labs = labWorks.tailSet(removeLabWork).iterator();
        labs.next();
        while (labs.hasNext()) {
            LabWork lab = labs.next();
            labs.remove();
        }
    }
    public void removeLower(LabWork removeLabWork) {
        Iterator<LabWork> labs = labWorks.headSet(removeLabWork).iterator();
        while (labs.hasNext()) {
            LabWork lab = labs.next();
            labs.remove();
        }
    }

    public LabWork getElementById(long id){
        for (LabWork i : labWorks) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public LocalDate getCreatingCollection() {
        return creatingCollection;
    }

    @Override
    public String toString() {
        return "labWorks=" + labWorks;
    }
}
