package busResv;
import java.util.Scanner;

import BusResrv.BookingDAO;
import BusResrv.BusDAO;

import java.sql.SQLException;
import java.util.ArrayList;
public class BusDemo {

	public static void main(String[] args) throws SQLException {

		BusDAO busdao = new BusDAO();
		busdao.displayBusInfo();

		
		int userOpt = 1; /* userOption 1 to to enter the booking */ 
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in); /*Scanner used to scanner input and continue */
		
		
		 while(userOpt==1) { 
			 System.out.println("Enter 1 to book and 2 to exit"); 
			userOpt = scanner.nextInt();
			if(userOpt == 1) { 
				//System.out.println("Booking......");/*  booking */
				Booking booking = new Booking(); 
				if(booking.isAvailable()) { 
					BookingDAO bookingdao = new BookingDAO();
					bookingdao.addBooking(booking);
					System.out.println("Your booking is confirmed");
					
				}
				else
					System.out.println("Sorry. Bus is full. Try another bus or date.");
			}
             
		}
		 scanner.close();
	}

}
