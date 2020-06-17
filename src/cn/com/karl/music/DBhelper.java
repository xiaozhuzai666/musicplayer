package cn.com.karl.music;

import android.R.integer;
import android.content.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper{
	private final static String DATABASE_NAME="person.db";
	private final static int DATABASE_VERSION=1;
	
	public DBhelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="Create table record(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"name VARCHAR(10)," +
				"psd VARCHAR(16)," +
				"gender VARCHAR(4)," +
				"country VARCHAR(8))";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
}
