package se.alphadev.reactive;

public class Person {
    enum Type {
        IDIOT,
        FRIEND,
        LOVER
    }

    private int id;
    private String name;
    private int salary;
    private Type type;


    public Person() {
    }

    public Person(int id, String name, int salary, Type type) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
