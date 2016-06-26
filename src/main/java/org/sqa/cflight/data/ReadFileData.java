/**
 *   File Name: ReadFileData.java<br>
 *
 *   Yutaka<br>
 *   Created: May 7, 2016
 *   
 */

package org.sqa.cflight.data;

/**
 * ReadFileData //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 * 
 * @author      Yutaka
 * @version     1.0.0
 * @since       1.0
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadFileData {
	public static String 	HOME_PAGE_URL,
							HOTELS_PAGE_URL,
							FLIGHTS_PAGE_URL,
							CARS_PAGE_URL;
	
	public void properties() {
		
		File file = new File("src/main/resources/datafile.properties");
		FileInputStream fileInput = null;
		
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HOME_PAGE_URL = prop.getProperty("homePageUrl");
		HOTELS_PAGE_URL = prop.getProperty("hotelsPageUrl");
		FLIGHTS_PAGE_URL = prop.getProperty("flightsPageUrl");
		CARS_PAGE_URL = prop.getProperty("carsPageUrl");
  }
	
}
