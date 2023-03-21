package me.lab5.Data;

public class Discipline {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int practiceHours;
    public Discipline(String name, int practiceHours){
        this.name = name;
        this.practiceHours = practiceHours;
    }
    public Discipline(){

    }
    public void setName(String name){
        if (name.equals("")) throw new IllegalArgumentException();
        this.name = name;
    }
    public void setPracticeHours(int practiceHours){
        this.practiceHours = practiceHours;
    }

    @Override
    public String toString() {
        return name + ", " + practiceHours;
    }
}
