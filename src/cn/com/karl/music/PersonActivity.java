package cn.com.karl.music;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PersonActivity extends Activity implements OnClickListener{
	Button logout,resignup,mworld;
	String id1,sex1,cite1;
	TextView id,sex,cite;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person);
	
		resignup=(Button) findViewById(R.id.resignup);
		logout=(Button) findViewById(R.id.logout);
		mworld=(Button) findViewById(R.id.mworld);
		resignup.setOnClickListener(this);
		logout.setOnClickListener(this);
		mworld.setOnClickListener(this);
		Intent get=getIntent();
		id1=get.getStringExtra("name");
		sex1=get.getStringExtra("gender");
		cite1=get.getStringExtra("country");
		id=(TextView) findViewById(R.id.account);
		sex=(TextView) findViewById(R.id.sex);
		cite=(TextView) findViewById(R.id.cite);
		id.setText(id1);
		sex.setText(sex1);
		cite.setText(cite1);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent pinIntent=new Intent();
		switch (v.getId()) {
		case R.id.mworld:	
			pinIntent.setClass(PersonActivity.this,MainActivity.class );
			pinIntent.putExtra("name", id1);
			pinIntent.putExtra("gender", sex1);
			pinIntent.putExtra("country", cite1);
			startActivity(pinIntent);
			break;
		case R.id.resignup:
			
			pinIntent.setClass(PersonActivity.this, Register.class);
			startActivity(pinIntent);
			break;
		case R.id.logout:
			
			pinIntent.setClass(PersonActivity.this, Login.class);
			startActivity(pinIntent);
			break;
		default:
			break;
		}
		
	}

	

}
