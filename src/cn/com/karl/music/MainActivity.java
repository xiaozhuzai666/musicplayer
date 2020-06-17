package cn.com.karl.music;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;


public class MainActivity extends ActivityGroup implements OnTabChangeListener {
    /** Called when the activity is first created. */
	//TabHost tab;
	TabHost tabHost;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
      //  tab=(TabHost) findViewById(R.id.tabhost);
        //tab.setup();
        Intent intent;  
        intent = new Intent().setClass(this, ListActivity.class);
        Resources res = getResources();
        
       // tab.addTab(tab.newTabSpec("tabmusic").setIndicator("音乐",res.getDrawable(R.drawable.item)).setContent(intent));
        tabHost = (TabHost) findViewById(R.id.tabhost); 
        tabHost.setup();
        tabHost.setup(this.getLocalActivityManager());
        TabHost.TabSpec spec; 
      
        
        
        //音乐页面
        intent = new Intent().setClass(MainActivity.this, ListActivity.class);
        spec = tabHost.newTabSpec("music").setIndicator("音乐",
                          res.getDrawable(R.drawable.item))
                      .setContent(intent);
        tabHost.addTab(spec);
       
        //歌手页面
        intent = new Intent().setClass(MainActivity.this, ArtistsActivity.class);
        spec = tabHost.newTabSpec("artist").setIndicator("艺术家",
                          res.getDrawable(R.drawable.artist))
                      .setContent(intent);
        tabHost.addTab(spec);
        //专辑页面
        intent = new Intent().setClass(MainActivity.this, AlbumsActivity.class);
        spec = tabHost.newTabSpec("album").setIndicator("专辑",
                          res.getDrawable(R.drawable.album))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        //个人中心页面
        Intent get=getIntent();
		String id1=get.getStringExtra("name");
		String sex1=get.getStringExtra("gender");
		String cite1=get.getStringExtra("country");
        intent = new Intent();
        intent.setClass(MainActivity.this,PersonActivity.class);
        intent.putExtra("name", id1);
		intent.putExtra("gender", sex1);
		intent.putExtra("country", cite1);
        spec = tabHost.newTabSpec("login").setIndicator("个人中心",
                          res.getDrawable(R.drawable.juice))
                      .setContent(intent);
        tabHost.addTab(spec);
        
         
       // tabHost.setCurrentTab(0);

    }

	@Override
	public void onTabChanged(String arg0) {
		// TODO Auto-generated method stub
		
		
	}
}