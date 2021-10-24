package com.amazonaws.samples;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.model.ServiceException;

public class UtentiLambda implements UtentiDAO{
	
	 String functionName;
	 String region;
	 String resp="";
	 String resp2="";
	 String resp3="";
	 
	
	 public UtentiLambda(String region, String function) {
		 this.region=region;
		 this.functionName=function;
		 System.out.println("region="+region);
		 System.out.println("fun="+functionName);
		 
	 }
	 
	 
	 
	 public int countUtenti() {
		 	int ct=0;
		   	String req="{\r\n"
	        		+ "  \"operation\": \"queryCount\",\r\n"
	        		+ "  \"TableName\": \"UTENTI\",\r\n"
	        		+ "  \"payload\": {\r\n"
	        		+ " \"parameter\":\"mobile\",\n"
	        		+ " \"FilterExpression\":\"TIPOUTENTE=:topic\"\n"
		           	+ "  }\r\n"
	        		+ "}";
			    resp=invoke(req);
				JSONObject obj = new JSONObject(resp);
				ct=obj.getInt("Count");
		        return ct;
	 }
	 
	 public int countListe() {
		 	
		   	String req="{\r\n"
	        		+ "  \"operation\": \"list\",\r\n"
	        		+ "  \"payload\": {\r\n"
	        		+ "  \"TableName\": \"LISTAPREFERITI\",\r\n"
	        		+ " \"ProjectExpression\":\"USERID\"\n"
		           	+ "  }\r\n"
	        		+ "}";
		   	
		   	String req1="{\r\n"
	        		+ "  \"operation\": \"list\",\r\n"
	        		+ "  \"payload\": {\r\n"
	        		+ "  \"TableName\": \"LISTADAVEDERE\",\r\n"
	        		+ " \"ProjectExpression\":\"USERID\"\n"
		           	+ "  }\r\n"
	        		+ "}";
		   	
		   	String req2="{\r\n"
	        		+ "  \"operation\": \"list\",\r\n"
	        		+ "  \"payload\": {\r\n"
	        		+ "  \"TableName\": \"LISTAPERSONALE\",\r\n"
	        		+ " \"ProjectExpression\":\"USERID\"\n"
		           	+ "  }\r\n"
	        		+ "}";
		   	//System.out.println("req="+req);
			resp=invoke(req);
			resp2=invoke(req1);
			resp3=invoke(req2);
			//System.out.println("resp="+resp);
			//System.out.println("resp2="+resp2);
			//System.out.println("resp3="+resp3);

			int ct=conteggio_json(resp);
			ct=ct+conteggio_json(resp2);
			ct=ct+conteggio_json(resp3);
			return (ct);
		        
			
	 }
	 
	 private int conteggio_json(String resp) {
		 int ct=0;
		   	JSONObject obj = new JSONObject(resp);
			JSONArray arr= obj.getJSONArray("Items");
			ArrayList<String> a=new ArrayList<>();
			System.out.println("LISTAUTENTI");
			for (int i=0;i<arr.length();i++) {
				JSONObject object =new JSONObject(arr.get(i).toString());
				String user =object.getString("USERID");
				//System.out.println(user);
				a.add(user);
			}
			Collections.sort(a);
			//System.out.println(a);
			String olduser="";
				        
			for (int i=0;i<arr.length();i++) {
				if (!olduser.equals(a.get(i).toString())) {
					ct++;
				}
			}
			System.out.println("ct=");
			return ct;
	 }
	 
	 
	 public int countAccessi(String datainiz, String datafine) {
		 	int ct=0;
		 	
		 	
		   	String req="{\r\n"
	        		+ "  \"operation\": \"queryCountDate\",\n"
	        		+ "  \"payload\": {\r\n"
	        		+ "  \"TableName\": \"ACCESSI\",\n"
	        		+ "  \"parameter1\":\"LOGIN\",\n"
	        		+ "  \"parameter2\":\"" + datainiz + "\",\n"
	        		+ "  \"parameter3\":\"" + datafine + "\",\n"
	        		+ "  \"FilterExpression\":\"AZIONE =:par1 and DATATIME between :par2 and :par3\"\n"
		           	+ "  }\r\n"
	        		+ "}";
		   		//System.out.println("req="+req);
			    resp=invoke(req);
		        //System.out.println("resp2="+resp);
				JSONObject obj = new JSONObject(resp);
				ct=obj.getInt("Count");
		        return ct;
	 }
	 
	 public int countFeed(String datainiz, String datafine) {
 		 	int ct=0;

		   	String req="{\r\n"
	        		+ "  \"operation\": \"queryCountDate\",\n"
	        		+ "  \"payload\": {\r\n"
	        		+ "  \"TableName\": \"ATTIVITA\",\n"
	        		+ "  \"parameter1\":\"\",\n"
	        		+ "  \"parameter2\":\"" + datainiz + "\",\n"
	        		+ "  \"parameter3\":\"" + datafine + "\",\n"
	        		+ "  \"FilterExpression\":\"TIPOATTIVITA >:par1 and DATATIME between :par2 and :par3\"\n"
		           	+ "  }\r\n"
	        		+ "}";
	
	 	   	
		   	System.out.println("req="+req);
			    resp=invoke(req);
		        //System.out.println("resp2="+resp);
				
		        JSONObject obj = new JSONObject(resp);
				ct=obj.getInt("Count");
		        return ct;
	 }
	 
	 
	 public int countRicerche(String datainiz, String datafine) {
 		 	int ct=0;

		   	String req="{\r\n"
	        		+ "  \"operation\": \"queryCountDate\",\n"
	        		+ "  \"payload\": {\r\n"
	        		+ "  \"TableName\": \"ATTIVITA\",\n"
	        		+ "  \"parameter1\":\"" + "RICERCA" + "\",\n"
	        		+ "  \"parameter2\":\"" + datainiz + "\",\n"
	        		+ "  \"parameter3\":\"" + datafine + "\",\n"
	        		+ "  \"FilterExpression\":\"TIPOATTIVITA =:par1 and DATATIME between :par2 and :par3\"\n"
		           	+ "  }\r\n"
	        		+ "}";
	 	
		   	
		   	
		   	System.out.println("req="+req);
			    resp=invoke(req);
		        //System.out.println("resp2="+resp);
				
		        JSONObject obj = new JSONObject(resp);
				ct=obj.getInt("Count");
		        return ct;
	 }
	 
	 public int countConnessi(String datainiz, String datafine) {
		 	int ct=0;

		   	String req="{\r\n"
	        		+ "  \"operation\": \"list\",\n"
	        		+ "  \"payload\": {\r\n"
	        		+ "  \"TableName\": \"ACCESSI\"\n"
		           	+ "  }\r\n"
	        		+ "}";

	
	   	   	System.out.println("req="+req);
			    resp=invoke(req);
		        //System.out.println("resp2="+resp);
				
		        JSONObject obj = new JSONObject(resp);
				JSONArray a=obj.getJSONArray("Items");
				int sz=a.length();
				
				String OldUser="";
				JSONObject obj1 = new JSONObject(a.get(0).toString());
				System.out.println(Integer.toString(0)+"," + a.get(0).toString());

				String userid =obj1.getString("USERID");
				String dtime =obj1.getString("DATATIME");
				String action = obj1.getString("AZIONE");
				OldUser=userid;
				

			
				for (int i=1; i<sz; i++) {
					try {
						//System.out.println(Integer.toString(i)+"," + a.get(i).toString());
						obj1 = new JSONObject(a.get(i).toString());
						userid = obj1.getString("USERID");
						dtime =obj1.getString("DATATIME");
						action = obj1.getString("AZIONE");
						if (!userid.equals(OldUser)) {
							
							JSONObject obj2 = new JSONObject(a.get(i-1).toString());
					  
							String userid2 = obj2.getString("USERID");
							String dtime2 =obj2.getString("DATATIME");
							String action2 = obj2.getString("AZIONE");
							
							if(action2.equals("LOGIN")) {
								ct++;
							}
							OldUser=userid;

						}
					}
					catch (Exception e) {
						System.out.println("errore");
					}
					
				}
			
				
		       // ct=obj.getInt("Count");
		        return ct;
	 }

	 public String addUtente(UtentiModel u) {
		 
		 
		 String userid=u.getUserid();
		 String nome=u.getNome();
		 String cognome=u.getCognome();
		 String datanascita=u.getDataNascita();
		 String password=u.getPassword();
		 String tipoutente=u.getTipo();
		 
		   String req="{\r\n"
	        		+ "  \"operation\": \"create\",\r\n"
	        		+ "  \"TableName\": \"UTENTI\",\r\n"
	        		+ "  \"payload\": {\r\n"
	        		+ "    \"Item\" :{"
	        		+ " \"USERID\":\"" + userid +"\","
	        		+ " \"NOME\":\"" + nome + "\","
	        		+ " \"COGNOME\":\"" + cognome+"\","
	        		+ " \"DATANASCITA\":\"" + datanascita +"\","
	        		+ " \"PASSWORD\":\"" + password +"\","
	        		+ " \"TIPOUTENTE\":\"" + tipoutente +"\""
		        	+ "  }\r\n"
		           	+ "  }\r\n"
	        		+ "}";
			 	System.out.println("req="+req);
		   
		        resp=invoke(req);
	    	
		        return resp;
		}

		 
	 public String getUtente(String  userid) {
		 System.out.println("ELABORANDO getutente , region="+region);
		 System.out.println("fun="+functionName);

		 	String pwd="";
		 	String req="{\r\n"
	        		+ "  \"operation\": \"read\",\r\n"
	        		+ "  \"TableName\": \"UTENTI\",\r\n"
	        		+ "  \"payload\": {\r\n"
	        		+ "  \"Key\" :{"
	        		+ " \"USERID\":\""
	        		+ userid
	        		+"\""
	        		+ "  },\r\n"
	        		+"\"ProjectionExpression\": \"PASSWORD\""
	        		+ "  }\r\n"
	        		+ "}";
        
        
        System.out.println(req);
        resp=invoke(req);
        // INSERIRE PARSE del JSON response attraverso la libreria json
	    System.out.println("resp2="+resp);
	    String s="";
	    if (resp.equals("{}")) {
	        System.out.println("user non trovato sul DB");
	    }
	    else {
	        JSONObject obj= new JSONObject(resp);
	        JSONObject obj2 = obj.getJSONObject("Item");
	        System.out.println("elaborando json");
	        System.out.println(obj2.toString());
	        s = obj2.getString("PASSWORD");
	        System.out.println("S="+s);
	    }

        return s;
	}

	 
	private String invoke(String pl) {

		 String functionName = "LambdaFunctionOverHttps";
		 String resp="";
		 String region="eu-south-1";

		InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(functionName)
                .withPayload(pl);
        
        InvokeResult invokeResult = null;

        try {
            AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                 //   .withCredentials(new ProfileCredentialsProvider())
                    .withRegion(region).build();

            invokeResult = awsLambda.invoke(invokeRequest);
            resp = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);

            //write out the return value
            //System.out.println(resp);
	        System.out.println(invokeResult.getStatusCode());


        } catch (ServiceException e) {
            System.out.println(e);
        }
        //System.out.println("resp2="+resp);
        return resp;
	}
	 
	 
}

