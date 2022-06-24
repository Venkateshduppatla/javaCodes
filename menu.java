import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class GFG
{
	private static void readUsingFileReader(String fileName)
        throws IOException
    {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        System.out.println("Reading text file using FileReader");
        while ((line = br.readLine()) != null) 
        {
            System.out.println(line);
        }
        br.close();
        fr.close();
    }
	public static void main(String[] args)
		throws IOException
	    {
	        String fileName = "menu.cfg";
	 	    readUsingFileReader(fileName);
	    }
}
