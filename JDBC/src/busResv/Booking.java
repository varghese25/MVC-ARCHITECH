package busResv;
import java.util.*;

import com.mysql.cj.jdbc.exceptions.SQLError;

import BusResrv.BookingDAO;
import BusResrv.BusDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
public class Booking {
	String passengerName;
	int busNo;
	Date date; // created in object it will be easy to compare
	
  Booking(){ // Constructor
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in); //
	System.out.println("Enter name of passenger: ");
	passengerName = scanner.next();
    System.out.println("Enter bus no:");
    busNo = scanner.nextInt();
    System.out.println("Enter date dd-mm-yyyy");
    String dateInput = scanner.next(); // Convert String to date in java 
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // simpleDateFormat class & Object created dateFormat
    
	try {
		date = dateFormat.parse(dateInput);  // It show error in parse so i have selected try catch 
		  
	} catch (ParseException e) {
		e.printStackTrace();
	}
  }
  public boolean isAvailable() throws SQLException {
	  BusDAO busdao = new BusDAO();
	  BookingDAO bookingdao = new BookingDAO();
	  
	  int capacity = busdao.getCapacity(busNo);
	  
	  int booked = bookingdao.getBookedCount(busNo,date); // Counter Old booking and New Booking Compare match same date
	  
	  return booked<capacity;// turner operater
  }
}
