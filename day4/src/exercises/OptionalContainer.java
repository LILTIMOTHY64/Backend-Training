package exercises;

import java.util.Optional;

class StudentClass {
	String email;

	public StudentClass(String email) {
		this.email = email;
	}

	Optional<String> getEmail() {
		return Optional.ofNullable(email);
	}
}

public class OptionalContainer {
	public static void main(String[] args) {
		StudentClass s = new StudentClass(null);
		Optional<String> email = s.getEmail();
		if (email.isPresent()) {
			System.out.println(email.get().length());
		} else {
			System.out.println("Email is not available");

		}
	}
}
