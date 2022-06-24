// Program of Mytools like print and input methods.
import java.util.*;

class Mytools
{
	public static void print(String... aString) 
	{
		if (aString.length == 0) 
		{
			System.out.println();
		}
		for (String value: aString) 
			System.out.println(value);
	}

	public static String input(String... aString)
	{
		Scanner sc = new Scanner(System.in);
		for (String value: aString) 
			System.out.print(value);
		String returnValue = sc.next();
		return returnValue;
	}
}

