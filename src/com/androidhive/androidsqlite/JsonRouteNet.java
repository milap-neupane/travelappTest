package com.androidhive.androidsqlite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


//this is my test class 
public class JsonRouteNet {
	
	//variable declarations
	JSONArray json_route_upd=new JSONArray();
	//JSONArray update_param=new JSONArray();
	
	
StringBuilder builder = new StringBuilder();
	//DatabaseHandler dbh= new DatabaseHandler(context);
	//ArrayList<List<String>> locrout = new ArrayList<List<String>>() ;
	
	String[] strarr=new String[4];
	
	
	//theory for updates: if update_param is empty no update is available this time
public JSONArray pulljson(){  //this method will return the whole json file ie Jsonarray json to the calling methods
	try {
		
		Log.e("Tryblock start","inside try block");
	
		
		URL url = new URL
		        ("http://192.168.97.1:8081/android/ktm_fare_server/update.php?last_stamp=2012-06-06%2016:52:44");
		        HttpURLConnection urlConnection =
		            (HttpURLConnection) url.openConnection();
		        urlConnection.setRequestMethod("GET");
		        urlConnection.connect();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		  
		//-------------------------------------------
        String line;
        while ((line = reader.readLine()) != null) {
          builder.append(line);
        }
        
       String file=builder.toString();
       Log.e("the network json from table is: ", file);
       
       //--temporary commented
       
        try {
        	
        	
        	//--JSONObject json= new JSONObject(file);
        	 json_route_upd=new JSONArray(file);
        	 
        	/* 
        	JSONObject obj1= json_route_upd.getJSONObject(0);
        	 update_param=obj1.getJSONArray("update_param");//inside update_param is two arrays
        	JSONArray arr_1_1=update_param.getJSONArray(0);//array containing table names and column names
        	String tb_name=arr_1_1.get(0).toString();
        	//put all the table names in table_list
        	
        	for(int m=0;m<update_param.length();m++){
        		JSONArray update_param_arr=update_param.getJSONArray(m);
        		table_list.add(update_param_arr.getString(0));
        		
        	}
        	
        	//add table name to list
        	colList.add(tb_name);
        	JSONArray col_arr=arr_1_1.getJSONArray(1);
        	for(int i=0;i<col_arr.length();i++){
        		colList.add(col_arr.getString(i));
        		Log.e("column names "+i, colList.get(i));
        	}
        	
        	
        	Log.e("first table name obtained is : ", tb_name);
        	
        	//now the table name and col names needs to be passed to a function to carry out table creation in database
        	
        	//tableCreate(tb_name, colList);
        	
        	//for array containing the table datas
        	JSONObject obj2= json_route_upd.getJSONObject(1);
        	
        	JSONArray arr2_1=obj2.getJSONArray(tb_name);
        	JSONArray row_1=arr2_1.getJSONArray(0);//array containing table contents
        	Log.e("the first insert row is : ", row_1.toString());
        	
      
        	//Log.e("first column name obtained is : ", col_arr.toString());  
        	
        	
        	//--JSONArray rout=json.getJSONArray("locroute");
        	
        	*//*
        	for(int i=0;i<rout.length();i++){
        	
        	JSONArray routearrI=rout.getJSONArray(i);
        	
        	//for(int i=0;i<routearrI.length();i++){
        	strarr[0]=routearrI.getString(0).toString();
        	strarr[1]=routearrI.getString(1).toString();
        	strarr[2]=routearrI.getString(2).toString();
        	strarr[3]=routearrI.getString(3).toString();
        	//strList.set(0, routearrI.getString(0).toString());
        	//strList.set(1, routearrI.getString(1).toString());
        	//adding values to list
        	List<String> strList= new ArrayList<String>();
        	strList.add(strarr[0]);
        	strList.add(strarr[1]);
        	strList.add(strarr[2]);
        	strList.add(strarr[3]);
        	Log.e("from list", i+"). "+"location_name: "+strList.get(0)+" loc_id: "+strList.get(1)+" routes: "+strList.get(2)+" timestamp: "+strList.get(3));
        	//Log.e("from array", i+"). "+"location: "+strarr[0]+" routes: "+strarr[1]);
        	//locrout.add(strList);
        	
        	locrout.add(strList);
        	
        	 
        	//}
        	
        	}//end for
        	
        	//db.addContact(new Contact(name1, phone1));
        	//int scod=0;
        	//int dcod=0;
        	 * 
        	 */
        	
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
	
	return json_route_upd; //the first item here is table name
	
}


//method to returns json file
	
	public JSONArray get_json_route_upd(){
		
		
		return pulljson();
	}
	
//returns the update parameter containing json array
	
public JSONArray get_update_param(){
	int counter=0;
	JSONObject upd_obj= new JSONObject();
	JSONArray upd_param=new JSONArray();
		JSONArray json=pulljson();
		try {
			upd_obj=json.getJSONObject(0);
			upd_param=upd_obj.getJSONArray("update_param");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.e("from inside update param", upd_param.toString());
		return upd_param;
	}

//returns the list of tables

public List<String> get_table_list(){
	List<String> table_list=new ArrayList<String>();
	
	JSONArray upd_param=get_update_param();
	
	for(int m=0;m<upd_param.length();m++){
		JSONArray upd_param_arr;
		try {
			upd_param_arr = upd_param.getJSONArray(m);
			table_list.add(upd_param_arr.getString(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	return table_list;
}

//returns col list containing table name at first

public List<List<String>> get_table_cols (){
	int counter=0;
	List<List<String>> table_cols = new ArrayList<List<String>>() ;
	List<String> colList= new ArrayList<String>();
	JSONArray col_arr=new JSONArray();
	JSONArray upd_param=get_update_param();
	table_cols.clear();
	
	for(int m=0;m<upd_param.length();m++){
		
		
		try {
			JSONArray	upd_param_arr = upd_param.getJSONArray(m);
			colList.clear();
			colList.add(upd_param_arr.get(0).toString());
			//Log.e("for loop table name", upd_param_arr.getString(0));
			col_arr=upd_param_arr.getJSONArray(1);
			counter=0;
			for(int n=0;n<col_arr.length();n++){
				colList.add(col_arr.get(n).toString());
				Log.e("counter", ""+counter++);
				
			}
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			
			//Log.e("loop col_list",colList.toString());
			table_cols.add(colList);
			Log.e("loop table_cols", table_cols.toString());
			
		}
		
		
		
	
	
	
	return table_cols;
	
}

}
