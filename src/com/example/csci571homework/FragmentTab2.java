package com.example.csci571homework;

//import android.app.Fragment;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class FragmentTab2 extends Activity {
    // Store instance variables
	  Intent intent;
	    String JSONSTRING;
	   	JSONObject obj;
	   	String title[]=new String[3];
	   	String image[]=new String [3];
	   	ImageView m1;
	   	TextView t1;
	    int loader = R.drawable.abc_item_background_holo_light;  
	    //ImageSwitcher imageSwitcher;
	    
	    ImageLoader imgLoader;
	    Animation slide_in_left, slide_out_right;
	   	int current;
    //private String title;
    private int page;
    Button p,n;
    int curIndex;
    //int imageResources[]=new int[3];

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
     setContentView(R.layout.tab2);
     letsparsethis();

}
    public void letsparsethis()
    {
        intent=getIntent();
		
 	   JSONSTRING = intent.getStringExtra("JSONSCRIPT");
 	    try {

 	         obj = new JSONObject(JSONSTRING);
 	         //p=(Button).findViewById(R.id.button1)

  
 	        
 	    n = (Button) findViewById(R.id.next);
 	    p=(Button) findViewById(R.id.prev);
 	      //imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);

 	      slide_in_left = AnimationUtils.loadAnimation(this,
 	        android.R.anim.slide_in_left);
 	      slide_out_right = AnimationUtils.loadAnimation(this,
 	        android.R.anim.slide_out_right);
 	      
 	     /*imageSwitcher.setFactory(new ViewFactory() {

 	    	   @Override
 	    	   public View makeView() {

 	    	    ImageView imageView = new ImageView(FragmentTab2.this);
 	    	    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

 	    	    LayoutParams params = new ImageSwitcher.LayoutParams(
 	    	      LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

 	    	    imageView.setLayoutParams(params);
 	    	    return imageView;

 	    	   }
 	    	  });
*/
 	      title[0]="Historical Zestimate for the past 1 year";
 	      title[1]="Historical Zestimate for the past 5 years";
 	      title[2]="Historical Zestimate for the past 10 years";
 	      image[0]=obj.getString("year1");
 	     image[1]=obj.getString("years5");
 	    image[2]=obj.getString("years10");
 	   t1=(TextView)findViewById(R.id.ZillowDisclaimer2);
   	t1.setClickable(true);
    	t1.setMovementMethod(LinkMovementMethod.getInstance());
    	//\u00AE Zillow, Inc., 2006-2014. Use is subject to 
    	String link="http://www.zillow.com/corp/Terms.htm";
    	String text="<a href='"+link+"'>Terms of Use</a>";
    			//"<a href=\'http://www.zillow.com/zestimate/\'></a>";
    			//"What\'s a Zestimate?";
    			t1.setText(Html.fromHtml(text));
    			t1=(TextView)findViewById(R.id.what2);
   	    	t1.setClickable(true);
   	     	t1.setMovementMethod(LinkMovementMethod.getInstance());
   	     	
   	     	 link="http://www.zillow.com/zestimate/";
   	     text="<a href='"+link+"'>What\'s a Zestimate?</a>";
   	     			//"<a href=\'http://www.zillow.com/zestimate/\'></a>";
   	     			//"What\'s a Zestimate?";
   	     			t1.setText(Html.fromHtml(text));
 	    curIndex = 0;
 	    m1=(ImageView)findViewById(R.id.chart);
 	  
 	    t1=(TextView)findViewById(R.id.heading);
 	   t1.setText(title[curIndex]);
	    imgLoader = new ImageLoader(getApplicationContext());
	     imgLoader.DisplayImage(image[curIndex], loader, m1);
	  
 	 //  imageSwitcher.setImageResource(imageResources[curIndex]);

 	   n.setOnClickListener(new OnClickListener() {

 	    @Override
 	    public void onClick(View arg0) {
 	     if (curIndex == image.length-1 ) {
 	      curIndex = 0;
 	      t1.setText(title[curIndex]);
 	    imgLoader = new ImageLoader(getApplicationContext());
 	     imgLoader.DisplayImage(image[curIndex], loader, m1);
 	     // imageSwitcher.setImageBitmap();
 	      //imageSwitcher.setImageResource(imageResources[curIndex]);
 	     } else {
 	    	t1.setText(title[curIndex+1]);
 	    	imgLoader = new ImageLoader(getApplicationContext());
 	 	     imgLoader.DisplayImage(image[curIndex++], loader, m1);
 	      //imageSwitcher.setImageResource(imageResources[++curIndex]);
 	     }
 	    }
 	   });
 	  p.setOnClickListener(new OnClickListener() {

 	 	    @Override
 	 	    public void onClick(View arg0) {
 	 	     if (curIndex ==0 ) {
 	 	      curIndex = image.length-1;
 	 	    t1.setText(title[curIndex]);
 	 	    imgLoader = new ImageLoader(getApplicationContext());
 	 	     imgLoader.DisplayImage(image[curIndex], loader, m1);
 	 	     } else {
 	 	    	t1.setText(title[curIndex-1]);
 	 	       imgLoader = new ImageLoader(getApplicationContext());
 	  	     imgLoader.DisplayImage(image[curIndex--], loader, m1);
 	 	     }
 	 	    }
 	 	   });
 	  
 	        Log.d("My App3", obj.toString());

 	    } catch (Throwable t) {
 	        Log.e("My App3", "Could not parse malformed JSON: \"" + JSONSTRING + "\"");
 	    }
     }
    
    }






 
