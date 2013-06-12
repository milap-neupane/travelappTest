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
import android.util.Log;

public class JsonInputs {
	//AndroidSQLiteTutorialActivity main=new AndroidSQLiteTutorialActivity();
	StringBuilder builder = new StringBuilder();
	
	ArrayList<List<String>> locrout = new ArrayList<List<String>>() ;
	List<String> strList= new ArrayList<String>();
	String[] strarr=new String[2];
	
	public ArrayList<List<String>> getLocRoutAll(Context context){
		
		//main.printme();
	
		Log.d(" with ocntext here kun value aayo?", "location:mmmmmmmmmroutes:nnnnnnnn");	
		
		
try {
			
			
			InputStream is=context.getAssets().open("tb_loc.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	        }
	        
	       String file=builder.toString();
	       
	        try {
	        	
	        	
	        	JSONObject json= new JSONObject(file);
	        	JSONArray rout=json.getJSONArray("locroute");
	        	
	        	for(int i=0;i<rout.length();i++){
	        	
	        	JSONArray routearrI=rout.getJSONArray(i);
	        	
	        	//for(int i=0;i<routearrI.length();i++){
	        	strarr[0]=routearrI.getString(0).toString();
	        	strarr[1]=routearrI.getString(1).toString();
	        	//strList.set(0, routearrI.getString(0).toString());
	        	//strList.set(1, routearrI.getString(1).toString());
	        	//adding values to list
	        	
	        	strList.add(strarr[0]);
	        	strList.add(strarr[1]);
	        	Log.e("from list", i+"). "+"location: "+strList.get(0)+" routes: "+strList.get(1));
	        	Log.e("from array", i+"). "+"location: "+strarr[0]+" routes: "+strarr[1]);
	        	//locrout.add(strList);
	        	
	        	locrout.add(strList);
	        	
	        	 
	        	//}
	        	
	        	}//end for
	        	
	        	//db.addContact(new Contact(name1, phone1));
	        	//int scod=0;
	        	//int dcod=0;
	        	
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
		
	
		
		return locrout;
		
		
	}
	
	

}
