package com.example.csci571homework;

import java.net.MalformedURLException;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class FBactivity extends ActionBarActivity{
	private static final String APP_ID = "337252859787269";
	Facebook fb=new Facebook(APP_ID);
	ImageView pic,button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab1);
		button=(ImageView)findViewById(R.id.login1);
		updateButtonImage();
//		pic=(ImageView)findViewByid(R.id.picture_pic)
			button.setOnClickListener((new OnClickListener() {
		    	  @Override
		     	    public void onClick(View arg0) {
		    	  if(fb.isSessionValid())
		    	  {
		    		  try {
		    		  fb.logout(getApplicationContext());
		    		  updateButtonImage();
		    		  //button close our session - log out of facebook;
		    		  }
		    		  catch (Exception e)
		    		  {
		    			 e.printStackTrace(); 
		    		  }
		    	  }
		    	  else
		    	  {
		    		  fb.authorize(FBactivity.this, new DialogListener() {
						
						@Override
						public void onFacebookError(FacebookError e) {
							// TODO Auto-generated method stub
							Toast.makeText(FBactivity.this, "fb error", Toast.LENGTH_SHORT).show();
							
						}
						
						@Override
						public void onError(DialogError e) {
							// TODO Auto-generated method stub
							Toast.makeText(FBactivity.this, "dialogError", Toast.LENGTH_SHORT).show();
						}
						
						@Override
						public void onComplete(Bundle values) {
							// TODO Auto-generated method stub
							updateButtonImage();
						}
						
						@Override
						public void onCancel() {
							// TODO Auto-generated method stub
							Toast.makeText(FBactivity.this, "onCancel", Toast.LENGTH_SHORT).show();
						}
					});
		    		  //login to facebook
		    	  }
		    	  } }));
		    	  }

	private void updateButtonImage() {
		// TODO Auto-generated method stub
		if(fb.isSessionValid())
		{
			button.setImageResource(R.drawable.logout_button);
		}
		else
			
		{
			button.setImageResource(R.drawable.login_button);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fbactivity, menu);
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
