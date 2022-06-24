// Program 

import java.sql.*;
import java.util.*;

class BankRecords    
{
  public static void main( String args[] )
  {
   try 
   {
      Loadconnection connector = new Loadconnection();
   } 
   catch ( Exception e )
   {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);   
   }
  }
}

class Loadconnection
{
   Loadconnection()
   {
      Connection c = null;
      Statement stmt = null;
   try 
   {
      Class.forName("com.mysql.cj.jdbc.Driver");
      c = DriverManager.getConnection("jdbc:mysql://165.22.14.77/dbVenkatesh","venkatesh", "pwdvenkatesh");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");
      stmt = c.createStatement();
      Scanner read = new Scanner(System.in);
      while (true)
         {  
            System.out.print("\nMenu:\n1. Create a Record.\n2. Display all Records.\n3. Exit.\nEnter Your Choice: ");
            int choice = read.nextInt();
            Cruds create = new Cruds();
            switch(choice)
            {
               case 1:
                  create.createRecord(stmt, read);
                  break;
               case 2:
                  create.displayRecords(stmt);
               // Display.display(c, "SELECT * from bankcustomers;");
                  break;
               case 3:
                 finalize(stmt, c);
            }
         }
      }
      catch ( Exception e )
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);   
      }
   }
   protected void finalize(Statement stmt, Connection c)
   {
      try
      {
         stmt.close();
         c.close();
         System.exit(0);
      }
      catch ( Exception e )
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);   
      }
   }
}

class Cruds
{
   public void createRecord(Statement stmt, Scanner read)
   {
      try
      {
         System.out.print("Enter Account Number: ");
         String accountNumber = read.next();
         System.out.print("Enter Customer Name: ");
         String name = read.next();
         System.out.print("Enter The Customer Bank Balance: ");
         String balance = read.next();
         String sql = "INSERT INTO bankcustomers (accountNumber,name,Balance,status) " + "VALUES ('" + accountNumber + "', '" + name + "', '" + balance + "', 'C');";
         stmt.executeUpdate(sql);
      }
      catch ( Exception e )
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
   }

   
   public void displayRecords(Statement stmt)
   {
      try
      {
         ResultSet rs = stmt.executeQuery( "SELECT * FROM bankcustomers;" );
         while ( rs.next() )
         {
            String accountNumber = rs.getString("accountNumber");
            String name = rs.getString("name");
            int balance  = rs.getInt("Balance");
            // String status = rs.getString("status");
            
            System.out.println( "Customer Account Number: " + accountNumber );
            System.out.println( "Name of the customer: " + name );
            System.out.println( "Customer Bank Balance = " + balance );
            System.out.println();
         }
         rs.close();
      }

      catch ( Exception e )
      {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
      }
   }
   
}

// class Display
// {
//    public static void display(Connection c, String query)
//    {
//       try
//       {
//          PreparedStatement ps=c.prepareStatement(query);  
//          ResultSet rs=ps.executeQuery();  
//          ResultSetMetaData rsmd=rs.getMetaData();  
//          List<String> fieldNames = new ArrayList<String>();
//          for (int fieldNameCounter = 1; fieldNameCounter <= rsmd.getColumnCount(); fieldNameCounter++)
//          {  
//             String fieldName = (rsmd.getColumnName(fieldNameCounter));
//             fieldNames.add(fieldName);
//          }  
//          int fieldsCount = rsmd.getColumnCount();
//          while ( rs.next() )
//          {
//             for (int fieldNameCounter = 0; fieldNameCounter < fieldsCount; fieldNameCounter++) 
//             {     
//                System.out.println(fieldNames.get(fieldNameCounter) + ": " + rs.getString(fieldNames.get(fieldNameCounter)));
//                System.out.println( "Customer Account Number: " + accountNumber );
//             }
//          }
//       }
//       catch(Exception e){ System.out.println(e);}
//    }  
// }  




// class SQLiteJDBC {

//    public static void main( String args[] ) {
//       Connection c = null;
//       Statement stmt = null;
      
//       try {
//          Class.forName("org.sqlite.JDBC");
//          c = DriverManager.getConnection("jdbc:sqlite:bankCustomers.db");
//          c.setAutoCommit(false);
//          System.out.println("Opened database successfully");

//          stmt = c.createStatement();
//          String sql = "INSERT INTO bankcustomers () " +
//                         "VALUES (1, 'Paul', 32, 'California', 20000.00 );"; 
//          stmt.executeUpdate(sql);

//          stmt.close();
//          c.commit();
//          c.close();
//       } catch ( Exception e ) {
//          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//          System.exit(0);
//       }
//       System.out.println("Records created successfully");
//    }
// }

         // InputStreamReader isr = new InputStreamReader(System.in);
         // BufferedReader reader = new BufferedReader(isr);
         // System.out.println("Enter Account Number : ");
         // int accountNumber = Integer.parseInt(reader.readLine());
         // System.out.println("Enter Name : ");
         // String name = Integer.parseString(reader.readLine());
         // System.out.println("Enter Balance : ");
         // int balance = Integer.parseInt(reader.readLine());





