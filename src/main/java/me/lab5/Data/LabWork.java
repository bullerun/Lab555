package me.lab5.Data;

import java.time.LocalDate;

public class LabWork implements Comparable<LabWork> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long minimalPoint; //Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Discipline discipline; //Поле может быть null


    public LabWork(Long id, String name, Coordinates coordinates,LocalDate creationDate, long minimalPoint,
                   Difficulty difficulty, Discipline discipline) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }

    public long getId() {
        return id;
    }
    public String getName(){return name;}
    public Coordinates getCoordinates(){return coordinates;}
    public LocalDate getCreationDate(){return creationDate;}
    public long getMinimalPoint(){return minimalPoint;}
    public Difficulty getDifficulty(){return difficulty;}
    public Discipline getDiscipline(){return discipline;}
    public void setName(String name){this.name = name;}
    public void setCoordinates(Coordinates coordinates){this.coordinates = coordinates;}
    public void setMinimalPoint(long minimalPoint){this.minimalPoint = minimalPoint;}
    public void setDifficulty(Difficulty difficulty){this.difficulty = difficulty;}
    public void setDiscipline(Discipline discipline){this.discipline = discipline;}

    @Override
    public String toString() {
        return  "id=" + id +
                " имя=" + name +
                " координаты=" + coordinates +
                " дата создания=" + creationDate +
                " минимальный балл=" + minimalPoint +
                " трудность=" + difficulty +
                " дисциплина=" + discipline;
    }

    @Override
    public int compareTo(LabWork other) {
        return id.compareTo(other.getId());
    }
}
