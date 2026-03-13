package exercises;

class StudentGS {
    private int id;
    private String name;
    private int marks;
    
    public StudentGS(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getMarks() {
        return marks;
    }
}

public class GetterSetter {

    public static void main(String[] args) {

        StudentGS s = new StudentGS(1, "Anand", 90);

        System.out.println(s.getId());
        System.out.println(s.getName());
        System.out.println(s.getMarks());

    }
}