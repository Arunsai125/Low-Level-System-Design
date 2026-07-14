class Student {
    private String name;
    private int age;
    private int rollNo;
    private int height;
    private int weight;
    private String address;

    public Student(String name, int age, int rollNo, int height, int weight, String address) {
        this.name = name;
        this.age = age;
        this.rollNo = rollNo;
        this.height = height;
        this.weight = weight;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", rollNo: " + rollNo + ", height: " + height + "cm, weight: "
                + weight + "kgs, address: " + address;
    }

}

class StudentBuilder {
    private String name;
    private int age;
    private int rollNo;
    private int height;
    private int weight;
    private String address;

    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public StudentBuilder setRollNo(int rollNo) {
        this.rollNo = rollNo;
        return this;
    }

    public StudentBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public StudentBuilder setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public StudentBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Student getStudent() {
        return new Student(name, age, rollNo, height, weight, address);
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        Student st1 = new StudentBuilder().setName("Arun").setAge(25).setHeight(180).setWeight(75).getStudent();
        System.out.println(st1);
    }
}