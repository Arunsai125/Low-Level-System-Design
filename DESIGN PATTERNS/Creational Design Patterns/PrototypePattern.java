interface Prototype {
    Prototype clone();
}

class Student implements Prototype {
    private String name;
    private int age;
    private int SSN;

    public Student(String name, int age, int SSN) {
        this.name = name;
        this.age = age;
        this.SSN = SSN;
    }

    @Override
    public Prototype clone() {
        return new Student(name, age, SSN);
    }

    @Override
    public String toString() {
        return "Student Name: " + name + ", Student age: " + age + ", Student SSN: " + SSN;
    }
}

public class PrototypePattern {
    public static void main(String[] args) {
        Prototype st1 = new Student("Arun", 25, 777777777);
        Student st2 = (Student) st1.clone();
        System.out.println(st2.toString());
    }
}