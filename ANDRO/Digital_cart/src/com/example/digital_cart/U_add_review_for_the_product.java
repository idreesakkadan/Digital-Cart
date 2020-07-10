package com.example.digital_cart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.example.digital_cart.S_add_offers.savejson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class U_add_review_for_the_product extends Activity {
	private String TAG = S_add_offers.class.getSimpleName();
	EditText e1,e2;
	Spinner sp1;
	Button b1;
	String prodid[],prodnm[];
	String prodctid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_add_review_for_the_product);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		b1=(Button)findViewById(R.id.button1);
		sp1=(Spinner)findViewById(R.id.spinner1);
		
		new getjson1().execute();
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!e1.getText().toString().equals("")&&!e2.getText().toString().equals(""))
				{
					new savejson().execute();
					Toast.makeText(getApplicationContext(), "Success", 3).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "error111", 3).show();
				}

			}
		});
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				prodctid=prodid[arg2];
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}
	public class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/a_review/vsh/";
			JSONObject jobj= new JSONObject();
	        try {
	        	jobj.put("pid",prodctid );
	        	jobj.put("date",e1.getText().toString() );
	        	jobj.put("review",e2.getText().toString() );
	        	
	        	
				JsonHandler.Postjson(url, jobj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null; 
		}
		
		@Override
	      protected void onPostExecute(Void result) {
	         super.onPostExecute(result);
	         
	         
	      }
			}
	private class getjson1 extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/a_product_details/vsh/";
			JSONArray jdata=JsonHandler.GetJson(url);
			prodid=new String[jdata.length()];
			prodnm=new String[jdata.length()];
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
                    String product_id = c.getString("productid");
                    String product_name = c.getString("productname");
                    
                    prodid[i]=product_id;
                    prodnm[i]=product_name;
                    		
                   
                   
                }
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
			}
			return null;
		}
		
		@Override
	      protected void onPostExecute(Void result) {
	         super.onPostExecute(result);
	        ArrayAdapter<String> ad=new ArrayAdapter<String>(U_add_review_for_the_product.this, android.R.layout.simple_spinner_item,prodnm);
	        sp1.setAdapter(ad);
	        
	        
	      }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.u_add_review_for_the_product, menu);
		return true;
	}

}
