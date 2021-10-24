package com.amazonaws.samples;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DAOFactory {
	//String resp="";
	 
	String db;
	String region;
	String LambdaFunction;
	
	private static DAOFactory theDAO;

	public static synchronized DAOFactory getDAOInstance() {
		if (theDAO==null)
			theDAO= new DAOFactory();
		return theDAO;
		
	}
	
	
	private DAOFactory() {
        String rootPath = this.getClass().getResource("").getPath();
        //String appConfigPath = rootPath + "app.properties";
        System.out.println("rootPth="+rootPath);
        //System.out.println("app.properiees="+appConfigPath);

		Properties prop = new Properties();
		FileInputStream input = null;
		File jarPath=new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		String propertiesPath=jarPath.getParentFile().getAbsolutePath();
		System.out.println("propertiesPath="+propertiesPath);		
		try {

		    input = new FileInputStream(propertiesPath+"/config.properties");
		    		//this.getClass().getClassLoader().getResourceAsStream("config.properties");
		    prop.load(input);
		    db=prop.getProperty("database");
		    region=prop.getProperty("region");
		    LambdaFunction=prop.getProperty("LambdaFunction");
		    
		    System.out.println("db="+db);
		    System.out.println("region="+region);
		    System.out.println("lambdafunc="+LambdaFunction);
		    
		    
		} catch (Exception ex) {
		    ex.printStackTrace();
		    db="AWS";
		    region="eu-south-1";
		    LambdaFunction="LambdaFunctionOverHttps";
		} 
		
		
	}

	public UtentiDAO getUtentiDAO() {

		if (db.equals("AWS"))
			return (UtentiDAO) new UtentiLambda(region, LambdaFunction);
		
		return null;
	}

	
	
}
