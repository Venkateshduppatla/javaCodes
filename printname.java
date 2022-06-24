import java.lang.Math;

class TestMath
{
    public static void Main(String[] args) 
    {
        /*Math myMath1 = new Math();
        Math myMath2 = new Math();
        Math myMath3 = new Math();*/
        // Math1 myMath1 = new Math1(); 
        Math2 myMath2 = new Math2();
        System.out.println(Math1.square(100)); 
        System.out.println(myMath2.cube(81)); 
        System.out.println(myMath2.half(25));
        System.out.println(myMath2.half(25));
        System.out.println(myMath2.square(20));
    }
}

class Math1
{
	public static int square(int number)
	{
		return number * number;
	}
	public int cube(int number)
	{
		return number * number * number;
	}
	public float square(float number)
	{
		return number * number;
	}
	public double half(double number)
	{
		return Math.ceil(number/(double)2);
	}
}

class Math2 extends Math1
{
	public double half(double number)
	{
		return Math.floor(number/(double)2);
	}	
}