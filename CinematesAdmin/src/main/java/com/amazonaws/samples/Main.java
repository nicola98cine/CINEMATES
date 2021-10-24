
package com.amazonaws.samples;

import java.awt.EventQueue;
import java.lang.Runnable;


public class Main {
	
	static FrameLogin f1;
	public static LoginController lc;
	public static AdminController ac;
        
    public static void main(String[] args) throws Exception {
       
        System.out.println("INIZIO Elaborazione Cinemates Console Amministrativa");
        lc=new LoginController();	
        ac=new AdminController();	
                       
         System.out.println("STEP02: eseguo elaborazione sui Frame Java");
         
        	EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    			        System.out.println("STEP03:invoco F1");

    					f1=new FrameLogin();
    					f1.setVisible(true);
    			     
 					
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    		    }
    		});
        	
        }
    
    }


