package com.example.csci571homework;

import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ResultsFromZillow extends TabActivity{
 Intent intent;
 String JSONSTRING;
	JSONObject obj;
	ImageView image1,image2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results_from_zillow);
		 intent=getIntent();
			
		   JSONSTRING = intent.getStringExtra("JSONSCRIPT");
		    try {

		         obj = new JSONObject(JSONSTRING);

		        Log.d("My App", obj.toString());

		    } catch (Throwable t) {
		        Log.e("My App", "Could not parse malformed JSON: \"" + JSONSTRING + "\"");
		    }
		TabHost tabhost=getTabHost();
		
		TabSpec tab1=tabhost.newTabSpec("Tab1");
		tab1.setIndicator("Basic Info");
		Intent i1=new Intent(ResultsFromZillow.this,FragmentTab1.class);
		i1.putExtra("JSONSCRIPT", JSONSTRING);
		//startActivity(i1);
		tab1.setContent(i1);
		
		TabSpec tab2=tabhost.newTabSpec("Tab2");
		tab2.setIndicator("Historical Zestimates");
		Intent i2=new Intent(ResultsFromZillow.this,FragmentTab2.class);
		i2.putExtra("JSONSCRIPT", JSONSTRING);
		//startActivity(i2);
		tab2.setContent(i2);
		
	   tabhost.addTab(tab1);
	   tabhost.addTab(tab2);
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
