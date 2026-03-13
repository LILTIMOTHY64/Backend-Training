package exercises;

class VehiclesUnsealed {
	void type() {
		System.out.println("This is a vehicle");
	}
}

class CarsUnsealed extends VehiclesUnsealed {
}

class BikesUnsealed extends VehiclesUnsealed {
}

class ToyVehiclesUnsealed extends VehiclesUnsealed // Not expected but still allowed
{
}

public class UnSealedClass {
	public static void main(String[] args) {
		CarsUnsealed c = new CarsUnsealed();
		c.type();
		ToyVehiclesUnsealed t = new ToyVehiclesUnsealed();
		t.type();
	}
}