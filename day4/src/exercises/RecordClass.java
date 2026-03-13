package exercises;

record Students(int id, String name, int marks) {
}

public class RecordClass {
	public static void main(String[] args) {
		Students s = new Students(1,"Keerthi", 90);
		System.out.println(s.id());
		System.out.println(s.name());
		System.out.println(s.marks());
	}
}
