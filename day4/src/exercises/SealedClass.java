package exercises;

sealed class Vehicles permits Cars, Bikes {
	void type() {
		System.out.println("This is a vehicle");
	}
}

final class Cars extends Vehicles {
}

final class Bikes extends Vehicles {
}

/*
 * class ToyVehicle extends Vehicle // Not allowed
 */
public class SealedClass {
	public static void main(String[] args) {
		Cars c = new Cars();
		c.type();

		Bikes jj = new Bikes();
		jj.type();
	}
}