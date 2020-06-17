package cn.com.karl.music;

import java.text.BreakIterator;
import java.util.ArrayList;

import android.os.Bundle;
import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
public class Register extends Activity implements OnClickListener{
	private SQLiteDatabase sdb;
	private DBhelper mys;
	DBService dbService;
	Spinner sp;
	EditText rname,rpsd;
	RadioButton female,male;
	Button sign;
	String name,psd,gender,country;
	String []str={"�й�","����","�ձ�","����","�¼���","Ӣ��"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		sp=(Spinner) findViewById(R.id.country);
		rname=(EditText) findViewById(R.id.rname);
		rpsd=(EditText) findViewById(R.id.rpsd);
		female=(RadioButton) findViewById(R.id.female);
		male=(RadioButton) findViewById(R.id.male);
		sign=(Button) findViewById(R.id.sign);
		mys = new DBhelper(this, "person.db", null, 1);//ʹ��halper�������ݿ�
		sdb=mys.getWritableDatabase();
		//�����б�
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(Register.this,
				android.R.layout.select_dialog_item,str);
		sp.setAdapter(adapter);
		sign.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sign:
			  String name = rname.getText().toString().trim();
			  String pass = rpsd.getText().toString().trim();
			  String gender = null;
					  sp.setOnItemSelectedListener(new OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
									country=parent.getItemAtPosition(position).toString();
									Toast.makeText(Register.this, "���ڵ�ѡ��ɹ�", Toast.LENGTH_LONG).show();
								
							}
		
							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub
								
							}
						});
			  if (name == null || "".equals(name) || pass == null || "".equals(pass)||country==null) {
			   Toast.makeText(this, "�˺š����������ڵز���Ϊ��", Toast.LENGTH_SHORT).show();
			  } else {
			   String number = rname.getText().toString();
			   boolean judgef = female.isChecked();
			   boolean judgem = male.isChecked();
			   String pa = rpsd.getText().toString();
			   if (judgef == true|| judgem == true) {
			    Toast.makeText(this, "ע��ɹ�", Toast.LENGTH_SHORT).show();
			    if(judgef==true){
			    	gender="Ů";
			    }else if(judgem==true){
					 gender="��";
				}
			    sdb.execSQL("insert into record(name,psd,gender,country)values('"+name+"','"+pass+"','"+gender+"','"+country+"')");
			    Intent intent = new Intent();
			    intent.setClass(Register.this, Login.class);
			    startActivity(intent);//������ת
			   } else {
			    Toast.makeText(this, "�û������������벻�����������", Toast.LENGTH_SHORT).show();
			   }
			  }
			 default:
				 break;
			  
			 }
			
			
		}


		
	}


