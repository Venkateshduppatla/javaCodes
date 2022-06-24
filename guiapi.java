// Program to display the entered city in GUI. 

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.event.*;

class SwingApp 
{  
	// String temperature = "";
	String cityName = "";
	// public static JTextField cityNameTF;
	SwingApp()
	{  
		JFrame frame = new JFrame("Temperature of any city");  
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
			}
		});

		  
		JLabel cityNameLabel = new JLabel(" City Name");  
		cityNameLabel.setBounds(20, 50, 80, 20);    
		  
		JTextField cityNameTF = new JTextField();  
		cityNameTF.setBounds(120, 50, 100, 20);  
		
		


		JButton submit = new JButton("Get Temperature");  
		submit.setBounds(20, 160, 130, 30);

		JLabel statusLabel = new JLabel("");  
		statusLabel.setBounds(20, 200, 200, 20);

		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cityName = cityNameTF.getText();
				// String temperature1 = temperatureFromUrl(cityName);
				// System.out.println(temperature1);
				String temperature= "";
				try
				{			
					URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=3bd3cf90bdd0e58f6460e8280d878c9a");
					HttpURLConnection connection = (HttpURLConnection)url.openConnection();
					connection.setRequestMethod("GET");
					connection.connect();
					Scanner sc  = new Scanner(url.openStream());
					JSONObject object = new JSONObject(sc.nextLine());
					JSONObject content = (JSONObject)object.get("main"); 
					temperature = content.getString("temp");
					// System.out.println(temperature);
				}
				catch (Exception error)
				{
					System.out.println(error);
				}
				statusLabel.setText("Temperature at "+ cityName + " is " + temperature + ".");
			}
		});
		frame.add(cityNameLabel);  
		frame.add(cityNameTF);  
		frame.add(submit);  
		frame.add(statusLabel);  
		frame.setSize(300,300);  
		frame.setLayout(null);  
		frame.setVisible(true);  
	}

	public static void main(String[] args) 
	{  
		SwingApp s = new SwingApp();  
	}  
}

// public String temperatureFromUrl(String cityName)
// {
	// try
	// 	{			
	// 		URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=3bd3cf90bdd0e58f6460e8280d878c9a");
	// 		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	// 		connection.setRequestMethod("GET");
	// 		connection.connect();
	// 		Scanner sc  = new Scanner(url.openStream());
	// 		JSONObject object = new JSONObject(sc.nextLine());
	// 		JSONObject content = (JSONObject)object.get("main"); 
	// 		String temperature = content.getString("temp");
	// 		// System.out.println(temperature);
	// 	}
	// 	catch (Exception error)
	// 	{
	// 		System.out.println(error);
	// 	}
		// return temperature;
// }


// class WeatherReport
// {
// 	public static void main(String[] args) {
// 		try
// 		{
// 			Mytools tools = new Mytools();
// 			String cityName = tools.input("Enter the City Name do which you need temperature: ");
// 			URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=3bd3cf90bdd0e58f6460e8280d878c9a");
// 			System.out.println(url);
// 			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
// 			connection.setRequestMethod("GET");
// 			connection.connect();
// 			Scanner sc  = new Scanner(url.openStream());

// 			JSONObject object = new JSONObject(sc.nextLine());
// 			JSONObject content = (JSONObject)object.get("main"); 
// 			tools.print("The Temperature at " + cityName + " is " + content.getString("temp"));
// 		}
// 		catch (Exception error)
// 		{
// 			System.out.println(error);
// 			// tools.println(error);
// 		}
// 	}
// }