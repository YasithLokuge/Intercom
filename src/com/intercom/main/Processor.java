/*******************************************************************************
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.intercom.main;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.log4j.Logger;
import com.google.gson.JsonObject;



/**
 * The Class Processor.
 * @author Yasith Lokuge
 */
public class Processor {
	
	/** The Constant DUBLIN_LATITUDE. */
	private static final float DUBLIN_LATITUDE = (float) 53.3381985;
	
	/** The Constant DUBLIN_LONGITUDE. */
	private static final float DUBLIN_LONGITUDE =  (float) -6.2592576;
	
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(Processor.class);
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String [] args){
		
		ListingsReader listingsReader = new ListingsReader();
		OutputWriter outputWriter = new OutputWriter();
		
		ArrayList<JsonObject> records = listingsReader.getListings();
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		logger.info("Started processing");
		
		for (JsonObject jsonObject : records) {
			
			float latitude = jsonObject.get("latitude").getAsFloat();
			float longitude = jsonObject.get("longitude").getAsFloat();
			Customer customer = new Customer();
			
			float distance = GPSDistanceCalculator.distFrom(latitude, longitude, DUBLIN_LATITUDE, DUBLIN_LONGITUDE)/1000;
			
			if(distance <= 100){
				customer.setName(jsonObject.get("name").getAsString());
				customer.setUserId(jsonObject.get("user_id").getAsInt());				
				customerList.add(customer);
			}
		}	
		
		Collections.sort(customerList);
		logger.info("Array Sorted");		
		outputWriter.writeToFile(customerList);
		logger.info("FInished processing. See output.txt");
		
	}

}
