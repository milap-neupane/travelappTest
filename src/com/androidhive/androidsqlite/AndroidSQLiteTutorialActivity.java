package com.androidhive.androidsqlite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class AndroidSQLiteTutorialActivity extends Activity {
    /** Called when the activity is first created. */

	List<List<String>> table_cols = new ArrayList<List<String>>() ;
	ArrayList<List<String>> locAll = new ArrayList<List<String>>();
	
	StringBuilder builder = new StringBuilder();
	DatabaseHandler db = new DatabaseHandler(this);
	JsonInputs jin=new JsonInputs();
	JsonRouteNet jrn=new JsonRouteNet();
	List<String>cols=new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //---netwrok json for creating multiple tables
        update();
     //this  cols= jrn.pulljson();
       //String tb_name=cols.get(0).toString();
      // call db handler to create table for this
      //this db.tableCreate(cols);
        //--end jrn----------
        
        
        parse();
       // DatabaseHandler db = new DatabaseHandler(this);
        
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
       
   locAll= jin.getLocRoutAll(this); //pass this locAll to databasehandler to insert  data into tables via function call
   
   db.insertLocRout(locAll);
   
   Log.d("printing from main", locAll.get(0).get(0).toString());
       // db.custominsert();
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));
//        db.custominsert();
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        
        String kk= db.forme().toString();
        Log.d("printed forme: ", "phoneno."+kk);
      
        
        List<Contact> contacts = db.getAllContacts();       
 
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
        Log.d("Name: ", log);
        
        }
        
        // printing the values in the table
        
       int i=db.getContactsCount();
       Log.d("the number of contacts in table is: ", i+"");
        
    }
    
    
	//json parsing
	public void parse() {
		int row=0;
		int col=0;
		
		
		
		try {
			
			
			InputStream is=this.getAssets().open("datajson.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	        }
	        
	       String file=builder.toString();
	       
	        try {
	        	
	        	
	        	JSONObject json= new JSONObject(file);
	        	JSONArray rout=json.getJSONArray("contacts");
	        	
	        	
	        	
	        	JSONArray routearrI=rout.getJSONArray(0);
	        	
	        	String name1=routearrI.getString(0).toString();
	        	String phone1=routearrI.getString(1).toString();
	        	
	        	Log.e("kun value aayo?", "name: "+name1+"phone: "+phone1);
	        	// temporarily db.addContact(new Contact(name1, phone1));
	        	int scod=0;
	        	int dcod=0;
	        	
/*	        	
	        	
	        	JSONArray route=rout.getJSONArray(routeno);
	        	JSONArray routeI=route.getJSONArray(0);
	        	
	        	for(int k=0;k<routeI.length();k++)
	        	{
	        		if(routeI.getString(k).equalsIgnoreCase(source))
	        		{
	        			scod=k;
	        		}
	        		

	        		if(routeI.getString(k).equalsIgnoreCase(dest))
	        		{
	        			dcod=k;
	        		}
	        	}
	        	
	        	if(scod>=0 && dcod>=0){
	        		
	        		row=scod+1;
        			col=dcod;
	        		
	        		
	        		
	        	}
	        	else
	        	{
	        		//not found location though the location routeno is selected from db so program dont come here
	        	}
	        //now fare portion
	        	
	        	JSONArray farerow=route.getJSONArray(row);
	        	String fare=farerow.getString(col);
	        	
	        	TextView tv_t=(TextView) findViewById(R.id.tv_to);
	   		 tv_t.setText("fare fare  "+source+"  "+dest+" "+routeno+"fare: "+fare);
	        	
	        	
	        	*/
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		//ends here
	
	
	public Context getContext(){
		
		return this;
	}
	
	public void printme(){
		
		Log.d("from inside main", "this in in main this in in main printed here");
	}
	
	//call for update ; update for routes table
	
	public void update(){
		
		table_cols=jrn.get_table_cols();
		
		Log.e("the printed tab_cols is: ", table_cols.toString());
		
		
	}
		
		}


