package com.androidhive.androidsqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables

	// Database Version
	private static final int DATABASE_VERSION = 8;

	// Database Name
	private static final String DATABASE_NAME = "contactsManager";

	// Contacts table name
	private static final String TABLE_CONTACTS = "contacts";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PH_NO = "phone_number";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_PH_NO + " TEXT" + ")";
		
		Log.e("from inside oncreate", "from inside oncreate");
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
		Log.e("from inside onupgrade", "from inside onupgrade");
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName()); // Contact Name
		values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		
		db.close(); // Closing database connection
		//inserting my own customized values
		
		
	}
//my customized insertion	
	void custominsert(){
		
	
		SQLiteDatabase db = this.getWritableDatabase();


		ContentValues values = new ContentValues();
			String sql1= "insert into "+TABLE_CONTACTS+" ("+KEY_NAME+","+KEY_PH_NO+") VALUES (?,?)";
			db.beginTransaction();
			SQLiteStatement stmt=db.compileStatement(sql1);
			
			values.put(KEY_NAME, "hoooo"); // Contact Name
			values.put(KEY_PH_NO, "98888"); // Contact Phone
			for(int i=0;i<3;i++){
				
				stmt.bindString(1, values.get(KEY_NAME).toString());
				stmt.bindString(2, values.get(KEY_PH_NO).toString());
			
			stmt.execute();
			stmt.clearBindings();
			}
			db.setTransactionSuccessful();
			db.endTransaction();
			db.close(); // Closing database connection
	}

	// Getting single contact
	Contact getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
				KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		// return contact
		return contact;
	}
	
	//Get contact using where clause customized for me
	
	public String forme(){
		String ss="";
		String selectQuery = "SELECT phone_number  FROM " + TABLE_CONTACTS + " where name='Tommy'";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		
		if (cursor.moveToFirst()) {
			do {
				
				ss=cursor.getString(0);
				//contact.setName(cursor.getString(1));
				//contact.setPhoneNumber(cursor.getString(2));
				
			} while (cursor.moveToNext());
		}

		return ss;
	}
	
	// Getting All Contacts
	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + "TABLE_CONTACTS2";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setPhoneNumber(cursor.getString(2));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PH_NO, contact.getPhoneNumber());

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}

	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}


	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int a= cursor.getCount();
		cursor.close();

		// return count
		return a;
	}
	//-----------------------my section
	
	public void insertLocRout(ArrayList<List<String>> locrout){
		
		
		
	}
	
	public int getTableRowsCoutn(String tablename){
		int a=0;
		
		return a;
		
		
	}
	
	public void tableCreate(List<String> cols){
		Log.e("table call happened ","hurrah" );
		String tb_name=cols.get(0).toString();
		SQLiteDatabase db = this.getWritableDatabase();
		
		for(int i=1;i<cols.size();i++){//since first element is table name so bypassed
    		
    		Log.e("inside db column names "+i, cols.get(i));
    	}
    	//create table statement goes here
		db.execSQL("DROP TABLE IF EXISTS " + tb_name);
		String CREATE_ROUTE_TABLE = "CREATE TABLE " + tb_name + "("
				+ cols.get(1).toString() + " INTEGER PRIMARY KEY,";
		for(int i=2;i<cols.size()-1;i++){
			CREATE_ROUTE_TABLE+= cols.get(i).toString() + " TEXT, ";
				
		}
		CREATE_ROUTE_TABLE+=cols.get((cols.size()-1)).toString() + " TEXT )";
		
		
		
		
		/*
		
		String CREATE_ROUTE_TABLE = "CREATE TABLE " + "TABLE_CONTACTS2" + " ("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_PH_NO + " TEXT" + ")";
		
		*/
		db.execSQL(CREATE_ROUTE_TABLE);
			Log.e("table created", "table created");
		
		
		db.close();
	}

}
