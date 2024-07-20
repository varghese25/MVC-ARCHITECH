import java.sql.*;
public class JDBCDem {

	public static void main(String[] args) throws Exception {
		
		
		readRecords(); // Methodds till rollback
	    //insertRecords();
	    //insertVar();
		//insertUsingPst();
		//delete();
		//update();
		//sp();
		//sp2();
		//sp3();
		//commitdemo();
		//batchdemo();
		//rollback();
		}
	
	public static void readRecords() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String password = "Tiju@25!";
		String query = "Select * from employee"; // Query

		Connection con = DriverManager.getConnection(url, userName, password); /* Connection  -> is interface not a class  con is object*/
		Statement st = con.createStatement(); //  Statement is class st object used to execute Query
		ResultSet rs = st.executeQuery(query); /* Result is class rs object execute query it give Result set --Pointer will pointing ->-first row we have to move next row--|Id | Name| Salary*/                             

		//rs.next(); /*pointer will move to next row*/ 
		
		while (rs.next()){ /* Display all resut in DB*/
/*Out put */
		System.out.println("Id is " + rs.getInt(1));
		System.out.println("firstName is " + rs.getString(2));
		System.out.println("Salary is " + rs.getInt(3));
		System.out.println("lastName is " + rs.getString(4));
		}
/*close the connection*/
		con.close();
	}
// insert Records
	public static void insertRecords() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String password = "Tiju@25!";
		String query = "insert into emplyoee values (4,'Poulose', 45000)";

		Connection con = DriverManager.getConnection(url,userName,password);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);
		System.out.println("Number of rows affected: " + rows);		
		con.close();
		}
	
	// insert Variables
	
	public static void insertVar() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String password = "Tiju@25!";
		
		int id =5;
		String name="Poulose";
		int salary= 45000;

		//"insert into employee values (5,'Poulose', 45000)";

		String query = "insert into emplyoee values ("+ id +",'"+ name +"'," + salary +");";

		Connection con = DriverManager.getConnection(url,userName,password);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);
		System.out.println("Number of rows affected: " + rows);		
		con.close();
		}
	
	
	
	//insert with prepared statement 
	
	public static void insertUsingPst() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String password = "Tiju@25!";
		
		int id =5;
		String name="Poulose";
		int salary= 45000;

	      //"insert into employee values (5,'Poulose', 45000)";

		String query = "insert into emplyoee values (?,?,?);";

		Connection con = DriverManager.getConnection(url,userName,password);
		PreparedStatement pst= con.prepareStatement(query);	
		pst.setInt(1,id);
		pst.setString(2,name);
		pst.setInt(3,salary);
		int rows = pst.executeUpdate();
		System.out.println("Number of rows affected: " + rows);	
		con.close();
		}

// delete only this method executed show me out put
	
	
	public static void delete() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcdemo";
		String userName = "root";
		String password = "Tiju@25!";
		
		int id =4;

		String query = "delete from employee where emp_id =" + id;

	    Connection con = DriverManager.getConnection(url,userName,password);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);
		System.out.println("Number of rows affected: " + rows);		
	
		con.close();
		}

	
		// update

		public static void update() throws Exception {
			String url = "jdbc:mysql://localhost:3306/jdbcdemo";
			String userName = "root";
			String password = "Tiju@25!";

			String query = "update employee set salary =66000 where emp_id =4";

			Connection con = DriverManager.getConnection(url, userName, password);
			Statement st = con.createStatement();
			int rows = st.executeUpdate(query);
			System.out.println("Number of rows affected: " + rows);

			con.close();
		}
// Three of statement 
		// 1. Normal Statement (Create/Insert Update)
		// 2. Prepared Statement (Question marks/ Create/Insert Update Both 1 & 2 used)
		// 3. CallableStatement (Store Procedure)
		
		// Calling Simple Store Procedure

 		   public static void sp() throws Exception {
			String url = "jdbc:mysql://localhost:3306/jdbcdemo";
			String userName = "root";
			String password = "Tiju@25!";
			
			Connection con = DriverManager.getConnection(url, userName, password);
			CallableStatement cst = con.prepareCall("{call GetEmp}");
			ResultSet rs= cst.executeQuery();
			while(rs.next()) {
			System.out.println("Id is " + rs.getInt(1)); 
			System.out.println("Name is " + rs.getString(2));
			System.out.println("Salary is " + rs.getInt(3));
			}
				
			con.close();
		}
 		   
 		// Calling Simple Store Procedure with Input Parameter
 		   
 		   
 		   public static void sp2() throws Exception {
			String url = "jdbc:mysql://localhost:3306/jdbcdemo";
			String userName = "root";
			String password = "Tiju@25!";
			int id = 4;
			Connection con = DriverManager.getConnection(url, userName, password);
			CallableStatement cst = con.prepareCall("{call GetEmpByID(?)}");
			cst.setInt(1, id);
			ResultSet rs= cst.executeQuery();
			while(rs.next()) {
			System.out.println("Id is " + rs.getInt(1)); 
			System.out.println("Name is " + rs.getString(2));
			System.out.println("Salary is " + rs.getInt(3));
			}
				
			con.close();
		}
 		   
 		   
 		   
// Calling Simple Store Procedure with In and Out Parameter
 		   
 		   
 		   public static void sp3() throws Exception {
			String url = "jdbc:mysql://localhost:3306/jdbcdemo";
			String userName = "root";
			String password = "Tiju@25!";
			int id = 4;
			Connection con = DriverManager.getConnection(url, userName, password);
			CallableStatement cst = con.prepareCall("{call GetNameByID(?,?)}"); // ? input parameter is id, ? out parameter
			cst.setInt(1, id);
			cst.registerOutParameter(2, Types.VARCHAR);
			cst.executeUpdate();
			System.out.println(cst.getString(2));
				
			con.close();
		}
 		   
 		  // commit vs auto commit
 		  public static void commitdemo() throws Exception {
 				String url = "jdbc:mysql://localhost:3306/jdbcdemo";
 				String userName = "root";
 				String password = "Tiju@25!";
 				
 				String query1 = "update employee set salary=21000 where emp_id=1";
 				String query2 = "update employee set salary=53000 where emp_id=2"; // even if make mistake in update salary value should not update disabled auto commit
 				
 				Connection con = DriverManager.getConnection(url, userName, password);
 				Statement st = con.createStatement();
 				
 				con.setAutoCommit(false);  // disabled auto commit
 				int rows1 = st.executeUpdate(query1);
 				System.out.println("Row affected" + rows1);
 				
 				int rows2 = st.executeUpdate(query2);
 				System.out.println("Row affected" + rows2);
 				
 				if(rows1>0 && rows2>0) // condition should satifiy correct value willupdate in the db or it wont
 					con.commit();
 					
 				con.close();
 		  }
 		  
 		   // Batch processing 
 		  public static void batchdemo() throws Exception {
				String url = "jdbc:mysql://localhost:3306/jdbcdemo";
				String userName = "root";
				String password = "Tiju@25!";
				
				String query1 = "update employee set salary=21000 where emp_id=1"; // after 21000 -> before 21000
				String query2 = "update employee set salary=21000 where emp_id=2"; // after 21000 -> before 53000
				String query3 = "update employee set salary=21000 where emp_id=3"; // after 21000 -> before 20000
				String query4 = "update employee set salary=21000 where emp_id=4"; // after 21000 -> before 66000
				
				Connection con = DriverManager.getConnection(url, userName, password);
				Statement st = con.createStatement();
				st.addBatch(query1);
				st.addBatch(query2);
				st.addBatch(query3);
				st.addBatch(query4);
				int[] res = st.executeBatch();
				for(int i: res) {
					System.out.println("Rows Affected: " + i);	
				}
				con.close();
		
		  }
 		   
 		// Batch processing same code used RollBack in case any error in update statement Query update in db. it will roll back to acutal value
 		 public static void rollback() throws Exception {
				String url = "jdbc:mysql://localhost:3306/jdbcdemo";
				String userName = "root";
				String password = "Tiju@25!";
				
				String query1 = "update employee set salary=45000 where emp_id=1"; 
				String query2 = "update employee set salary=34000 where emp_id=2"; 
				String query3 = "update employee set salary=23000 where emp_id=3"; 
				String query4 = "updat employee set salary=21000 where emp_id=4"; 
				
				Connection con = DriverManager.getConnection(url, userName, password);
				con.setAutoCommit(false);
				Statement st = con.createStatement();
				st.addBatch(query1);
				st.addBatch(query2);
				st.addBatch(query3);
				st.addBatch(query4);
				int[] res = st.executeBatch();
				for(int i: res) {
					if (i>0)
						continue;
					else
						con.rollback();
				}
				con.commit();
				con.close();
		
		  }

}
