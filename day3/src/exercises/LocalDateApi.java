package exercises;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class LocalDateApi {
	public static void main(String[] args) {
		// Today date
		LocalDate today = LocalDate.now();

		// Employee Joining data
		LocalDate joiningDate = LocalDate.of(2025, 5, 10);
		System.out.println("Today: " + today);
		System.out.println("Joining date:  " + joiningDate);
		Period period = Period.between(today, joiningDate);
		int diff_months = Math.abs(period.getMonths());
		int diff_years = Math.abs(period.getYears());
		
		System.out.println("Experience: "+diff_years+ " years and "+diff_months+" months");
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		System.out.println("Local Date & Time: "+dateTime);

	}

}
