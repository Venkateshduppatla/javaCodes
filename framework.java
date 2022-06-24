// Cruds Framework Program.

import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.IOException;


class Records    
{
  public static void main( String args[] )
  {
   try 
   {
   		String menuFile = "menu.cfg";
   		String recordsTable = "bankCustomers";
    	Sqlconnection connector = new Sqlconnection(menuFile, recordsTable);
   } 
   catch ( Exception e )
   {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);   
   }
  }
}


class Sqlconnection
{

	private static void readMenuLines(String menuFile)
        throws IOException
    {
    	try
    	{
	        File file = new File(menuFile);
	        Scanner br = new Scanner(file);
	        String line;
	        while (br.hasNextLine()) 
	        {
	            System.out.println(br.nextLine());
	        }
	        br.close();
    	}
    	catch ( Exception e )
	      {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
    }

   Sqlconnection(String menuFile, String recordsTable)
   {
      Connection c = null;
      Statement stmt = null;
      try 
      {

      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:bankCustomers.db");
      c.setAutoCommit(false);
      stmt = c.createStatement();
      Scanner read = new Scanner(System.in);
    	String query = ("SELECT * FROM " + recordsTable + ";");
      List<String> fieldNames = Cruds.loadFields(c, query);
      while (true)
      {
        readMenuLines(menuFile);
        int choice = read.nextInt();
        switch(choice)
        {
          case 1:
            Cruds.createRecord(stmt, read, recordsTable, fieldNames);
            break;
          case 2:
            Cruds.displayRecords(c, query, fieldNames);
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
	public static List<String> loadFields(Connection c, String query)
	{
		List<String> fieldNames = new ArrayList<String>();
		try
		{
			PreparedStatement ps=c.prepareStatement(query);  
			ResultSet rs=ps.executeQuery();  
			ResultSetMetaData rsmd=rs.getMetaData();  
			for (int fieldNameCounter = 1; fieldNameCounter <= rsmd.getColumnCount(); fieldNameCounter++)
			{  
			String fieldName = (rsmd.getColumnName(fieldNameCounter));
			fieldNames.add(fieldName);
			}
		}
		catch ( Exception e )
	      {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
    return fieldNames; 
	}
	public static void createRecord(Statement stmt, Scanner read, String recordsTable, List<String> fieldNames)
    {
      try
      {
      	int fieldsCount = fieldNames.size();
      	List<String> fieldValues = new ArrayList<String>();
        for (int fieldNameCounter = 0; fieldNameCounter < (fieldsCount-1); fieldNameCounter++) 
      	{
	        System.out.print("Enter " + fieldNames.get(fieldNameCounter) + ": ");
	        fieldValues.add(read.next());
      	}
        String result = String.join("', '", fieldValues);
        String sql = "INSERT INTO " + recordsTable +" VALUES('" + result + "', 'O')";
        stmt.executeUpdate(sql);
      }
      catch ( Exception e )
      {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
    }
    public static void displayRecords(Connection c, String query, List<String> fieldNames)
   {
      try
      {
         PreparedStatement ps=c.prepareStatement(query);  
         ResultSet rs=ps.executeQuery();  
         ResultSetMetaData rsmd=rs.getMetaData();  
         int fieldsCount = rsmd.getColumnCount();
         while ( rs.next() )
         {
            for (int fieldNameCounter = 0; fieldNameCounter < fieldsCount; fieldNameCounter++) 
            {
            	String fieldName = fieldNames.get(fieldNameCounter);
				System.out.println(fieldName + ": " + rs.getString(fieldName));
            }
         }
      }
      catch(Exception e){ System.out.println(e);}
   }  
}