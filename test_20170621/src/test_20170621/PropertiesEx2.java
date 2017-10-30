package test_20170621;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesEx2 {
	public static void main(String[]args){
		
		if(args.length !=1){
			System.out.println("USAGE : java PropertiesEx2 INPUTFILENAME");
			System.exit(0);
		}
		
		Properties prop = new Properties();
		
		String inputFile = args[0];
		
		try{
			prop.load(new FileInputStream(inputFile));
			
		}catch(IOException e){
			
		}
		
		
	}
}
