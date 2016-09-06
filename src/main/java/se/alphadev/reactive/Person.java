package se.alphadev.reactive;

public class Person {
    enum Type {
        IDIOT,
        FRIEND,
        LOVER
    }

    private int sequenceNo;
    private String name;
    private Type type;


    public Person() {
    }

    public Person(int sequenceNo, String name, Type type) {
        this.sequenceNo = sequenceNo;
        this.name = name;
        this.type = type;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
