package com.example.csci571homework;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	private static final String TAG = "MainActivity.java";
	private Button btn;
	private EditText add,ci;
	private TextView t1,t2,t3,t4;
	private Spinner spinner3;
	private int Checkifvalid=0;
	 public final static String EXTRA_MESSAGE = "com.example.csci571homework";
	public int firstinstance=0;

	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		additemsonSpinner();
		btn=(Button)findViewById(R.id.button1);
		add=(EditText)findViewById(R.id.editText1);
		ci=(EditText)findViewById(R.id.editText2);
		t1=(TextView)findViewById(R.id.Text1);
		t2=(TextView)findViewById(R.id.Text2);
		t3=(TextView)findViewById(R.id.Text3);
		t4=(TextView)findViewById(R.id.Text4);
		spinner3=(Spinner) findViewById(R.id.spinner2);
		dynamicinitialization();
		ImageView img = (ImageView)findViewById(R.id.imageViewtr);
		img.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v){
		        Intent intent = new Intent();
		        intent.setAction(Intent.ACTION_VIEW);
		        intent.addCategory(Intent.CATEGORY_BROWSABLE);
		        intent.setData(Uri.parse("http://zillow.com"));
		        startActivity(intent);
		    }
		});
		btn.setOnClickListener(new View.OnClickListener() {
		    @Override
			public void onClick(View v) {
		    	
		    	new PostDataAsyncTask().execute();
		    	if(Checkifvalid==1)
		    	{
		    		t4.setVisibility(View.VISIBLE);
		    		
		    	}
		    	else if(Checkifvalid==0)
		    	{
		    		t4.setVisibility(View.INVISIBLE);
		    	}
		    	//String json = "{'phonetype':'N95','cat':'WP'}";
		    	//String json2="{'homedetails':'http:\/\/www.zillow.com\/homedetails\/720-W-27th-St-1-San-Pedro-CA-90731\/21313115_zpid\/','street':'720 W 27th St # 1','city':'San Pedro','state':'CA','zipcode':'90731','useCode':'Townhouse','lastSoldPrice':'$482,000.00','yearBuilt':'1991','lastSoldDate':' 16- May -2014','lotSizeSqFt':'5009sq.ft.','estimateLastUpdate':' 20- Nov -2014','estimateAmount':'$502,630.00','finishedSqFt':'1690sq.ft.','estimateValueChangeSign':'+','Year1':'http:\/\/www.zillow.com\/app?chartDuration=1year&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent%20=true&width=600&zpid=21313115','Year5':'http:\/\/www.zillow.com\/app?chartDuration=5years&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent%20=true&width=600&zpid=21313115','Year10':'http:\/\/www.zillow.com\/app?chartDuration=10years&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent%20=true&width=600&zpid=21313115','estimateValueChange':'$919.00','bathrooms':'3.0','estimateValuationRangeLow':'$472,472.00','estimateValuationRangeHigh':'$547,867.00','bedrooms':'3','restimateLastUpdate':' 3- Nov -2014','restimateAmount':'$2,336.00','taxAssessmentYear':'2013','restimateValueChangeSign':'+','restimateValueChange':'$47.00','taxAssessment':'$349,000.00','restimateValuationRangeLow':'$1,659.00','restimateValuationRangeHigh':'$2,710.00','errcode':'1'}";
				//String json2="{'homedetails':'http:\\/\\/www.zillow.com\\/homedetails\\/720-W-27th-St-1-San-Pedro-CA-90731\\/21313115_zpid\\/','street':'720 W 27th St # 1','city':'San Pedro','state':'CA','zipcode':'90731','useCode':'Townhouse','lastSoldPrice':'$482,000.00','yearBuilt':'1991','lastSoldDate':' 16- May -2014','lotSizeSqFt':'5009sq.ft.','estimateLastUpdate':' 20- Nov -2014','estimateAmount':'$502,630.00','finishedSqFt':'1690sq.ft.','estimateValueChangeSign':'+','Year1':'http:\\/\\/www.zillow.com\\/app?chartDuration=1year&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent%20=true&width=600&zpid=21313115','Year5':'http:\\/\\/www.zillow.com\\/app?chartDuration=5years&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent%20=true&width=600&zpid=21313115','Year10':'http:\\/\\/www.zillow.com\\/app?chartDuration=10years&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent%20=true&width=600&zpid=21313115','estimateValueChange':'$919.00','bathrooms':'3.0','estimateValuationRangeLow':'$472,472.00','estimateValuationRangeHigh':'$547,867.00','bedrooms':'3','restimateLastUpdate':' 3- Nov -2014','restimateAmount':'$2,336.00','taxAssessmentYear':'2013','restimateValueChangeSign':'+','restimateValueChange':'$47.00','taxAssessment':'$349,000.00','restimateValuationRangeLow':'$1,659.00','restimateValuationRangeHigh':'$2,710.00','errcode':'1'}";
//String json2="{'valid':'1','homedetails':'http:\\/\\/www.zillow.com\\/homedetails\\/2430-Raymond-Ave-Los-Angeles-CA-90007\\/20592587_zpid\\/','street':'2430 Raymond Ave','city':'Los Angeles','state':'CA','zipcode':'90007','latitude':'34.033592','longitude':'-118.297213','useCode':'MultiFamily2To4','lastSoldPrice':'203,000.00','yearBuilt':'1890','lastSoldDate':'01-May-2001','lotSizeSqFt':'6,752.00','estimateLastUpdate':'20-Nov-2014','estimateAmount':'588,518.00','finishedSqFt':'2,667.00','estimateValueChange':'11,706.00','imgn':'<img src=\\'http:\\/\\/cs-server.usc.edu:45678\\/hw\\/hw6\\/down_r.gif\\'>','imgp':'<img src=\\'http:\\/\\/cs-server.usc.edu:45678\\/hw\\/hw6\\/down_r.gif\\'>','bathrooms':'3.0','estimateValuationRangeLow':'535,551.00','estimateValuationRangeHigh':'659,140.00','bedrooms':'5','restimateLastUpdate':'03-Nov-2014','taxAssessmentYear':'2010','restimateValueChange':'9.00','taxAssessment':'286,427.00','restimateAmount':'588,518.00','restimateValuationRangeLow':'1,430.00','restimateValuationRangeHigh':'2,717.00','year1':'http:\\/\\/www.zillow.com\\/app?chartDuration=1year&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent=true&width=600&zpid=20592587','years5':'http:\\/\\/www.zillow.com\\/app?chartDuration=5years&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent=true&width=600&zpid=20592587','years10':'http:\\/\\/www.zillow.com\\/app?chartDuration=10years&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent=true&width=600&zpid=20592587','valuesign':'-'}";
		    	
		    	//String json2="{'valid':'1','homedetails':'http:\\/\\/www.zillow.com\\/homedetails\\/2430-Raymond-Ave-Los-Angeles-CA-90007\\/20592587_zpid\\/','street':'2430 Raymond Ave','city':'Los Angeles','state':'CA','zipcode':'90007','latitude':'34.033592','longitude':'-118.297213','useCode':'MultiFamily2To4','lastSoldPrice':'203,000.00','yearBuilt':'1890','lastSoldDate':'01-May-2001','lotSizeSqFt':'6,752.00','estimateLastUpdate':'20-Nov-2014','estimateAmount':'588,518.00','finishedSqFt':'2,667.00','estimateValueChange':'11,706.00','imgn':'http:\\/\\/cs-server.usc.edu:45678\\/hw\\/hw6\\/down_r.gif\','imgp':'http:\\/\\/cs-server.usc.edu:45678\\/hw\\/hw6\\/down_r.gif\','bathrooms':'3.0','estimateValuationRangeLow':'535,551.00','estimateValuationRangeHigh':'659,140.00','bedrooms':'5','restimateLastUpdate':'03-Nov-2014','taxAssessmentYear':'2010','restimateValueChange':'9.00','taxAssessment':'286,427.00','restimateAmount':'588,518.00','restimateValuationRangeLow':'1,430.00','restimateValuationRangeHigh':'2,717.00','year1':'http:\\/\\/www.zillow.com\\/app?chartDuration=1year&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent=true&width=600&zpid=20592587','years5':'http:\\/\\/www.zillow.com\\/app?chartDuration=5years&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent=true&width=600&zpid=20592587','years10':'http:\\/\\/www.zillow.com\\/app?chartDuration=10years&chartType=partner&height=300&page=webservice%2FGetChart&service=chart&showPercent=true&width=600&zpid=20592587','valuesign':'-'}";
		    	//Intent intent = new Intent(MainActivity.this,ResultsFromZillow.class);
		    	//intent.putExtra("JSONSCRIPT", json2);
		    	//startActivity(intent);
				
		    }
		});
		
	}
	 public void dynamicinitialization()
	 {
			add.addTextChangedListener(new TextWatcher() {
		       	 
		     	   public void afterTextChanged(Editable s) {
		     		   if(!(add.getText().toString().matches("")))
		 	        	{
		 	        		t1.setVisibility(View.INVISIBLE);
		 	        		
		 	        	}
		     	   }
		     	 
		     	   public void beforeTextChanged(CharSequence s, int start, 
		     	     int count, int after) {
		     		   t1.setVisibility(View.INVISIBLE);
		     	   }
		     	 
		     	   public void onTextChanged(CharSequence s, int start, 
		     	     int before, int count) {
		     			if(add.getText().toString().matches(""))
		 	        	{
		 	        		t1.setVisibility(View.VISIBLE);
		 	        		
		 	        	}
		     	   }
		     	  });
				ci.addTextChangedListener(new TextWatcher() {
			       	 
			     	   public void afterTextChanged(Editable s) {
			     		   if(!(ci.getText().toString().matches("")))
			 	        	{
			 	        		t2.setVisibility(View.INVISIBLE);
			 	        		
			 	        	}
			     	   }
			     	 
			     	   public void beforeTextChanged(CharSequence s, int start, 
			     	     int count, int after) {
			     		   t2.setVisibility(View.INVISIBLE);
			     	   }
			     	 
			     	   public void onTextChanged(CharSequence s, int start, 
			     	     int before, int count) {
			     			if(ci.getText().toString().matches(""))
			 	        	{
			 	        		t2.setVisibility(View.VISIBLE);
			 	        		
			 	        	}
			     	   }
			     	  });
				spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {
				    @Override
				    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				       if(firstinstance==1)
				       {
				    	if(spinner3.getSelectedItemPosition()==0)
					    {
					 // t3.setText("Please choose state");
				        	firstinstance=1;
					  t3.setVisibility(View.VISIBLE);
					    }
				      
				        else  if(!(spinner3.getSelectedItemPosition()==0))
					    {
					 // t3.setText("Please choose state");
					 t3.setVisibility(View.INVISIBLE);
					    }
				       }
				       else if (firstinstance==0)
				       {
				    	   t3.setVisibility(View.INVISIBLE);
				    	   firstinstance=1;
				       }
		  // your code here
				    }

				    @Override
				    public void onNothingSelected(AdapterView<?> parentView) {
				    	if(spinner3.getSelectedItemPosition()==0)
					    {
					 // t3.setText("Please choose state");
					  t3.setVisibility(View.VISIBLE);
					    }
				        // your code here
				    }

				});
	 }

	public class PostDataAsyncTask extends AsyncTask<String, String, String> {
	     


	        protected void onPreExecute() {
	            super.onPreExecute();
	            
	        
	        	if(add.getText().toString().matches(""))
	        	{
	        		t1.setVisibility(View.VISIBLE);
	        		
	        	}
	        	if(ci.getText().toString().matches(""))
	        	{
	        		t2.setVisibility(View.VISIBLE);
	        		
	        	}
	            if(spinner3.getSelectedItemPosition()==0)
	            {
	         // view3.setText("Please choose state");
	          t3.setVisibility(View.VISIBLE);
	            }
	  
	        }

	@Override
	protected String doInBackground(String... params) {
	try {
	                    postText();
	            } catch (NullPointerException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return null;
	    }
	}
	private void postText() throws JSONException{
	      try{
	         
	        String result="";
	      //  final EditText street1=(EditText) findViewById(R.id.street);
	    //final EditText city1=(EditText) findViewById(R.id.city);
	            String streetInput=add.getText().toString();
	    String cityInput=ci.getText().toString();
	   //     final Spinner spinner2=(Spinner) findViewById(R.id.spinner1);
	        String text1 = spinner3.getSelectedItem().toString();
	            String postReceiverUrl = "http://cs-server.usc.edu:56845/index.php";
	          Log.v(TAG, "postURL: " + postReceiverUrl);

	       
	          HttpClient httpClient = new DefaultHttpClient();

	        
	          HttpPost httpPost = new HttpPost(postReceiverUrl);

	          
	          List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	          nameValuePairs.add(new BasicNameValuePair("street", streetInput));
	          nameValuePairs.add(new BasicNameValuePair("city", cityInput));
	          nameValuePairs.add(new BasicNameValuePair("state", text1));
	          final TextView view1=(TextView) findViewById(R.id.textView1);
	          httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	          // execute HTTP post request
	          HttpResponse response = httpClient.execute(httpPost);
	          //get message from response
	          HttpEntity resEntity = response.getEntity();
	          //get content of message
	            InputStream webs=resEntity.getContent();
	          if (resEntity != null) {


	              try
	              {
	                BufferedReader reader=new BufferedReader(new
	InputStreamReader(webs,"iso-8859-1"),8);
	                StringBuilder sb=new StringBuilder();
	                String line=null;
	                while((line=reader.readLine())!=null)
	                {
	                sb.append(line+"\n");
	                }
	                webs.close();
	                result=sb.toString();
	                System.out.println(result);
	                Log.i("json",result);
	                JSONObject outer = new JSONObject(result);
	                String k=outer.getString("valid");
	                // String hi=outer.getString("homedetails");
	              //    System.out.println(hi);
	               // JSONObject json_data=outer.getJSONObject(0);
	               if(k.matches("0"))
	                {
	            	   Checkifvalid=1;
	            	 //  t4.setText("Please Verify your input");
	            	  // t4.setVisibility(View.VISIBLE);
	                }
	               if(k.matches("1"))
	               {
	            	   Checkifvalid=0;
	                Intent intent = new Intent(MainActivity.this,ResultsFromZillow.class);
	                intent.putExtra("JSONSCRIPT", result);
	                  startActivity(intent);
	               }

	              }
	                catch(Exception e)
	                {
	                Log.e("log_tag","Error converting result"+e.toString());
	                }
                    
	              //uncomment later
	            /*  try{


	              // for(int i=0;i<outer.length();i++)
	              //{
	                //JSONObject json_data=outer.getJSONObject(0);
	                Iterator<String> iter = outer.keys();
	                  while (iter.hasNext()) {
	                      String key = iter.next();
	                      try {
	                          Object value = outer.get(key);
	                          view1.setText((CharSequence) value);
	                      } catch (JSONException e) {
	                          // Something went wrong!
	                      }
	                  }
	            //  view1.setText(json_data.getString("homedetails"));
	            //  }
	              String responseStr = EntityUtils.toString(resEntity).trim();
	              Log.v(TAG, "Response: " +  responseStr);
	              }*/
	              // catch(JSONException e)

	              //{
	                //Log.e("log_tag","Error parsing data"+e.toString());
	              // }
	          }

	      } catch (ClientProtocolException e) {
	          e.printStackTrace();
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	  }


	    
     public void additemsonSpinner()
     {
    	 Spinner spinner = (Spinner) findViewById(R.id.spinner2);
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
    	        R.array.State, android.R.layout.simple_spinner_item);
    	// Specify the layout to use when the list of choices appears
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	spinner.setAdapter(adapter);
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


