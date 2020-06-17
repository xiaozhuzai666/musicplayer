package cn.com.karl.music;

import android.content.*;
import android.database.sqlite.SQLiteDatabase;

public class DBService {
	private DBhelper helper;
	//构造方法，建立数据库
	public  DBService(Context context){
		this.helper=new DBhelper(context, "person.db", null, 1);
	}
	public void insert(String name,String psd,String gender,String country){
		String sql = "INSERT INTO record(name,psd,gender,country)" +
				"VALUES('" +name+"','"+psd+"','"+gender+"','"+country+"')";
		SQLiteDatabase db=helper.getWritableDatabase();
		db.execSQL(sql);
	}
	public void delete(String name){
		String sql="delete from record where name='"+name+"'";
		SQLiteDatabase db=helper.getWritableDatabase();
		db.execSQL(sql);
	}
	public void update(String type,String value,String name){
		String sql=null;
		if(type.equals("psd")){
			sql="update record set psd='"+value+"'where name='"+name+"'";
		}else if(type.equals("gender")){
			sql="update record set gender='"+value+"'where name='"+name+"'";
		}
		else if(type.equals("country")){
			sql="update record set country='"+value+"'where name='"+name+"'";
		}
		SQLiteDatabase db=helper.getWritableDatabase();
		db.execSQL(sql);
	}

}
