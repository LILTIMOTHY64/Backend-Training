package exercises;

class TicketBooking {
	int seats = 1;

	void bookTicket(String name) {
		System.out.println(name + " is trying to book ticket");
		// Block level synchronization
		synchronized (this) {
			if (seats > 0) {
				System.out.println(name + " successfully booked the ticket");
				seats--;
			} else {
				System.out.println("Sorry ticket not available for " + name);
			}
		}
	}
}

class TicketCustomer extends Thread {
	TicketBooking tb;
	String name;

	TicketCustomer(TicketBooking tb, String name) {
		this.tb = tb;
		this.name = name;
	}

	public void run() {
		tb.bookTicket(name);
	}
}

public class BlockSynchronization {
	public static void main(String args[]) {
		TicketBooking obj = new TicketBooking();

		TicketCustomer c1 = new TicketCustomer(obj, "Customer 1");
		TicketCustomer c2 = new TicketCustomer(obj, "Customer 2");

		c1.start();
		c2.start();
	}
}
