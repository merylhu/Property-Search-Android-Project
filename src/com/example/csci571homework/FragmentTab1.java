package com.example.csci571homework;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.widget.LoginButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//import android.support.v4.app.Fragment;
//import android.view.Gravity;
//import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;


public class FragmentTab1 extends FragmentActivity {
    // Store instance variables
	private MainFragment mainFragment;
    private String title;
    private int page;
    ImageView m1,m2;
    Bitmap bitmap;
    Intent intent;
    String JSONSTRING;
   	JSONObject obj;
    Button authButton;
    Bundle params =new Bundle();
    int checkifpressed=0;
   int checkifvalid=0;
	private static final String APP_ID = "337252859787269";
	 String t[]=new String [35];
	Facebook fb=new Facebook(APP_ID);
	ImageView pic,button;
   	//private static final String TAG = "MainFragment";
   //	JSONArray ,
 TextView t1;
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);

  setContentView(R.layout.tab1);
// authButton = (Button) findViewById(R.id.FacebookShareButton);
  //authButton.setFragment(FragmentTab1);
  m1=(ImageView)findViewById(R.id.imageView1);
  m2=(ImageView)findViewById(R.id.imageView2);
  button=(ImageView)findViewById(R.id.login1);
	//updateButtonImage();
	parsetheJSON();
	button.setOnClickListener((new OnClickListener() {
			
	    	  @Override
	     	    public void onClick(View arg0) {
	    		  
	    	
	     someshit();
	    	  }
	}));
	
  
}
 	    
    	  @Override
    	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    		// TODO Auto-generated method stub
    		super.onActivityResult(requestCode, resultCode, data);
    		fb.authorizeCallback(requestCode,resultCode,data);
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
    public Bitmap getBitmapFromURL(String src)
    {
    	try
    	{
    		URL url =new URL(src);
    		HttpURLConnection connection =(HttpURLConnection)url.openConnection();
    	    connection.setDoInput(true);
    	    InputStream input=connection.getInputStream();
    	    Bitmap myBitmap= BitmapFactory.decodeStream(input);
    	    return myBitmap;
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    }
  //  public void postThisShit(String t1,String t2,String t3,String t4,String t5,String t7,String t27,String t31, String t14)
    public void postThisShit()
    {
    	if(checkifpressed==1&&checkifvalid==1)
    	{
    	//Bundle Params;
    	//params.putString("name","Zillow App");
		params.putString("link",t[1]);
		params.putString("picture",t[27]);
		params.putString("name",t[2]+","+t[3]+","+t[4]+" "+t[5]);
		params.putString("description","Last Sold Price:$"+t[7]+", 30 Days Overall Change:"+t[31]+"$"+t[14]);
    	
    	fb.dialog(FragmentTab1.this, "feed",params,new DialogListener() {
				
				@Override
				public void onFacebookError(FacebookError e) {
					// TODO Auto-generated method stub
					Toast.makeText(FragmentTab1.this, "Facebook Error", Toast.LENGTH_SHORT).show();
					
				}
				
				@Override
				public void onError(DialogError e) {
					// TODO Auto-generated method stub
					Toast.makeText(FragmentTab1.this, "dialogError", Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onComplete(Bundle values) {
					// TODO Auto-generated method stub
					Toast.makeText(FragmentTab1.this, "Posted Story ID:"+APP_ID, Toast.LENGTH_SHORT).show();
					
				}
				
				@Override
				public void onCancel() {
					Toast.makeText(FragmentTab1.this, "Cancelled", Toast.LENGTH_SHORT).show();
					// TODO Auto-generated method stub
					
				}});
    	}
    }
    public void someshit()
    {
    	new AlertDialog.Builder(this)
			.setMessage("Post to Facebook")
			.setCancelable(false)
			.setPositiveButton("Post Property Details",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							login();
						}
					}).setNegativeButton("Cancel", null).show();
    }
    public void login()
    {
    	checkifpressed=1;
	    	  if(fb.isSessionValid())
	    	  {
	    		  try {
	    		  fb.logout(getApplicationContext());
	    		 // updateButtonImage();
	    		
	    		  //login to facebook
	    	  
	    		  //button close our session - log out of facebook;
	    		  }
	    		  catch (Exception e)
	    		  {
	    			 e.printStackTrace(); 
	    		  }
	    	  }
	    	  else
	    	  {
	    		  
	    		  fb.authorize(FragmentTab1.this, new DialogListener() {
					
					@Override
					public void onFacebookError(FacebookError e) {
						// TODO Auto-generated method stub
						Toast.makeText(FragmentTab1.this, "FB Error", Toast.LENGTH_SHORT).show();
						
					}
					
					@Override
					public void onError(DialogError e) {
						// TODO Auto-generated method stub
						Toast.makeText(FragmentTab1.this, "dialogError", Toast.LENGTH_SHORT).show();
					}
					
					@Override
					public void onComplete(Bundle values) {
						// TODO Auto-generated method stub
						
						/*try {
						    Thread.sleep(1000);                 //1000 milliseconds is one second.
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}*/
						checkifvalid=1;
						Toast.makeText(FragmentTab1.this, "Success", Toast.LENGTH_SHORT).show();
						//updateButtonImage();
					    postThisShit();
						
					}
					
					@Override
					public void onCancel() {
						// TODO Auto-generated method stub
						Toast.makeText(FragmentTab1.this, "Post Cancelled", Toast.LENGTH_SHORT).show();
					}
				});
	    		  //login to facebook
	    	  }	
    }
public void parsetheJSON()
{

	 intent=getIntent();
		
	   JSONSTRING = intent.getStringExtra("JSONSCRIPT");
	   //Log.INFO(JSONSTRING);
	    try {

	         obj = new JSONObject(JSONSTRING);

	        
	         t[0]= obj.getString("valid");
	         t[1]= obj.getString("homedetails");
	         t[2]= obj.getString("street");
	         t[3]= obj.getString("city");
	         t[4]= obj.getString("state");
	         t[5]= obj.getString("zipcode");
	         t[6]= obj.getString("useCode");
	         t[7]= obj.getString("lastSoldPrice");
	         t[8]= obj.getString("yearBuilt");
	         t[9]= obj.getString("lastSoldDate");
	         t[10]= obj.getString("lotSizeSqFt");
	         t[11]= obj.getString("estimateLastUpdate");
	         t[12]= obj.getString("estimateAmount");
	         t[13]= obj.getString("finishedSqFt");
	         t[14]= obj.getString("estimateValueChange");
	         t[15]= obj.getString("imgn");
	         t[16]= obj.getString("imgp");
	         t[17]= obj.getString("bathrooms");
	         t[18]= obj.getString("estimateValuationRangeLow");
	         t[19]= obj.getString("estimateValuationRangeHigh");
	         t[20]= obj.getString("restimateLastUpdate");
	         t[21]= obj.getString("taxAssessmentYear");
	         t[22]= obj.getString("restimateValueChange");
	         t[23]= obj.getString("taxAssessment");
	         t[24]= obj.getString("restimateAmount");
	         t[25]= obj.getString("restimateValuationRangeLow");
	         t[26]= obj.getString("restimateValuationRangeHigh");
	         t[27]= obj.getString("year1");
	         t[28]= obj.getString("years5");
	         t[29]= obj.getString("years10");
	         t[30]=obj.getString("bedrooms");
	         t[31]=obj.getString("valuesign");
	     	t1=(TextView)findViewById(R.id.textView23);
	     	String n;
	     	n=t1.getText().toString();
	     	n=n+" "+t[11];
	     	t1.setText(n);
	     	t1=(TextView)findViewById(R.id.textView29);
	     	String n1;
	     	n1=t1.getText().toString();
	     	n1=n1+" "+t[20];
	     	t1.setText(n1);
	     	t1=(TextView)findViewById(R.id.textView6);
	     	t1.setText(t[6]);
	     	t1=(TextView)findViewById(R.id.yearBuilt);
	     	t1.setText(t[8]);
	     	t1=(TextView)findViewById(R.id.textView8);
	     	t1.setText(t[10]+" Sq. Ft.");
	     	t1=(TextView)findViewById(R.id.textView10);
	     	t1.setText(t[13]+" Sq. Ft.");
	     	t1=(TextView)findViewById(R.id.textView12);
	     	t1.setText(t[17]);
	     	t1=(TextView)findViewById(R.id.textView14);
	     	t1.setText(t[30]);
	     	t1=(TextView)findViewById(R.id.textView16);
	     	t1.setText(t[21]);
	     	t1=(TextView)findViewById(R.id.textView18);
	     	t1.setText("$"+t[23]);
	     	t1=(TextView)findViewById(R.id.textView20);
	     	t1.setText("$"+t[7]);
	     	t1=(TextView)findViewById(R.id.textView22);
	     	t1.setText(t[9]);
	     	t1=(TextView)findViewById(R.id.textView24);
	     	t1.setText("$"+t[12]);
	     	t1=(TextView)findViewById(R.id.textView26);
	     	t1.setText("$"+t[14]);
	     	t1=(TextView)findViewById(R.id.textView28);
	     	t1.setText("$"+t[18]+" - $"+t[19]);
	     	t1=(TextView)findViewById(R.id.textView32);
	     	t1.setText("$"+t[22]);
	     	t1=(TextView)findViewById(R.id.textView34);
	     	t1.setText("$"+t[25]+" - $"+t[26]);
	     	t1=(TextView)findViewById(R.id.textView30);
	     	t1.setText("$"+t[24]);
	     	
	     	t1=(TextView)findViewById(R.id.ZillowDisclaimer);
	    	t1.setClickable(true);
	     	t1.setMovementMethod(LinkMovementMethod.getInstance());
	     	//\u00AE Zillow, Inc., 2006-2014. Use is subject to 
	     	String link="http://www.zillow.com/corp/Terms.htm";
	     	String text="<a href='"+link+"'>Terms of Use</a>";
	     			//"<a href=\'http://www.zillow.com/zestimate/\'></a>";
	     			//"What\'s a Zestimate?";
	     			t1.setText(Html.fromHtml(text));
	     			t1=(TextView)findViewById(R.id.what);
	    	    	t1.setClickable(true);
	    	     	t1.setMovementMethod(LinkMovementMethod.getInstance());
	    	     	
	    	     	 link="http://www.zillow.com/zestimate/";
	    	     text="<a href='"+link+"'>What\'s a Zestimate?</a>";
	    	     			//"<a href=\'http://www.zillow.com/zestimate/\'></a>";
	    	     			//"What\'s a Zestimate?";
	    	     			t1.setText(Html.fromHtml(text));
	     			t1=(TextView)findViewById(R.id.textView3);
	     	t1.setClickable(true);
	     	t1.setMovementMethod(LinkMovementMethod.getInstance());
	     	text="<a href='"+t[1]+"'>"+t[2]+","+t[3]+","+t[4]+" "+t[5]+"</a>";
	     	t1.setText(Html.fromHtml(text));
	    
	        int loader = R.drawable.abc_item_background_holo_light;  
	      	        ImageView image = (ImageView) findViewById(R.id.imageView1);
	        String image_url = t[15];
	        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
     imgLoader.DisplayImage(image_url, loader, image);
     image = (ImageView) findViewById(R.id.imageView2);
     image_url=t[16];
     imgLoader = new ImageLoader(getApplicationContext());
     imgLoader.DisplayImage(image_url, loader, image);

   			
   		    		  
   		    	
   		    	     
       Log.d("My vtrApp", obj.toString());
	       

	    } catch (Throwable t) {
	        Log.e("My 5usApp", "Could not parse malformed JSON: \"" + JSONSTRING + "\"");
	    }
}
  
}