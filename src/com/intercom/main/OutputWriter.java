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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The Class OutputWriter.
 * @author Yasith Lokuge
 */
public class OutputWriter {

	/**
	 * Write to file.
	 *
	 * @param customerList the output json
	 */
	public void writeToFile(ArrayList<Customer> customerList) {
		
		File file = new File("resources/output.txt");
		FileWriter fw;
		
		try {

			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile());

			for (Customer customer : customerList) {
				String writeLine = customer.getUserId() +" : "+ customer.getName() +"\r\n";
				fw.write(writeLine);
			}
			
			
			BufferedWriter bw = new BufferedWriter(fw);			
			bw.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
