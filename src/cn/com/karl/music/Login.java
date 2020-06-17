package cn.com.karl.music;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	AutoCompleteTextView lname;
	EditText lpsd;
	Button login;
	TextView signup;
	ArrayAdapter<String> adapter;
	SharedPreferences.Editor editer;
	String TAG = "weimeng";
	
	 private SQLiteDatabase w;
	 private SQLiteDatabase r;
	 private DBhelper mys;
	 private List<St> mdata;
	 private String name;
	 private String pass;
	 private String gender;
	 private String country;
	 int index1,index2,index3,index4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		lname=(AutoCompleteTextView) findViewById(R.id.lname);
		lpsd=(EditText) findViewById(R.id.lpsd);
		login=(Button) findViewById(R.id.login);
		signup=(TextView) findViewById(R.id.signup);
        editer = getSharedPreferences("data",MODE_PRIVATE).edit();
        initAutoComplete("history1",lname);
        mys = new DBhelper(this, "person.db", null, 1);//使用halper创建数据库
        r=mys.getReadableDatabase();
        w=mys.getWritableDatabase();
        mdata=new ArrayList<St>();
        Cursor query = r.rawQuery("select * from record", null);
        while(query.moveToNext()){
          index1 = query.getColumnIndex("name");
          index2 = query.getColumnIndex("psd");
          index3 = query.getColumnIndex("gender");
          index4 = query.getColumnIndex("country");
         name = query.getString(index1);
         pass = query.getString(index2);
         gender= query.getString(index3);
         country= query.getString(index4);
         mdata.add(new St(0, name, pass,gender,country));
        }
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String name1 = lname.getText().toString().trim();
			    String pass1 = lpsd.getText().toString().trim();
			    String gender1=null;
			    String country1=null;
			    if (name1.equals(name)&&pass1.equals(pass)){
			        Cursor q = r.rawQuery("select * from record", null);
			        while(q.moveToNext()){
			         int dex1 = q.getColumnIndex("name");
			         int dex2 = q.getColumnIndex("psd");
			         int dex3 = q.getColumnIndex("gender");
			         int dex4 = q.getColumnIndex("country");
			        String n = q.getString(dex1);
			        String g= q.getString(dex3);
			        String  c= q.getString(dex4);
			        
			         if(n.equals(name1)){
			        	 gender1=g;
			        	 country1=c;
			        	 break;
			         }
			        	 
			        	
			        }
			   
			     Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_LONG).show();
			     Intent in=new Intent();
					
					in.setClass(Login.this, PersonActivity.class);
					in.putExtra("name", name1);
					in.putExtra("gender", gender1);
					in.putExtra("country", country1);
					startActivity(in);
					
			    }else{
			     Toast.makeText(Login.this,"账号与密码输入不正确",Toast.LENGTH_SHORT).show();
			    }
			}
		}
		);
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in2=new Intent();
				in2.setClass(Login.this, Register.class);
				startActivity(in2);
			}
		});
	}	
	private void initAutoComplete(String field, AutoCompleteTextView auto){
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        String longHistory = pref.getString(field, "nothing");
        Log.d(TAG, "initAutoComplete: +longHistory"+longHistory);
        String[] hisArrays = longHistory.split(",");
        Log.d(TAG, "hisArrays length="+hisArrays.length);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, hisArrays);
        //只保留最近的50条记录
        if (hisArrays.length > 50) {
            String[] newArray = new String[50];
            System.arraycopy(hisArrays, 0, newArray, 0, 50);
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,
                    newArray);
        }
        auto.setAdapter(adapter);
        Log.d(TAG, "setAdapter");
        auto.setDropDownHeight(1500);
        auto.setThreshold(1);
        auto.setCompletionHint("最近的5条记录");
        auto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(hasWindowFocus()){
                    ((AutoCompleteTextView) view).showDropDown();
                    Log.d(TAG, "onFocusChange: showdown");
                }
            }
        });
    }
    //放入 pref 的 field 字段中
    private void saveHistory(String field,AutoCompleteTextView auto) {
        String text = auto.getText().toString();
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String longHistory = sp.getString(field, "nothing");
        Log.d(TAG, "saveHistory: "+longHistory);
        //避免重复
        if (!longHistory.contains(text + ",")) {
            StringBuilder sb = new StringBuilder(longHistory);
            sb.insert(0, text + ",");
            editer.putString(field, sb.toString()).apply();
        }
    }
   


}
