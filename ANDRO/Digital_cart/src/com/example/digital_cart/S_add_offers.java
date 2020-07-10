package com.example.digital_cart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class S_add_offers extends Activity {
	private String TAG = S_add_offers.class.getSimpleName();
	Spinner sp,sp1;
	EditText e1;
	Button b1;
	String secid[],secnm[];
	String prodid[],prodnm[];
	String sectionid,productid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_s_add_offers);
		sp=(Spinner)findViewById(R.id.spinner1);
		sp1=(Spinner)findViewById(R.id.spinner2);
		e1=(EditText)findViewById(R.id.editText2);
		b1=(Button)findViewById(R.id.button1);
		new getjson1().execute();
		new getjson2().execute();
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!sp.getSelectedItem().toString().equals("")&&!sp1.getSelectedItem().toString().equals("")&&!e1.getText().toString().equals(""))
				{
					new savejson().execute();
					Toast.makeText(getApplicationContext(), "Success", 3).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "error", 3).show();
				}
				

			}
		});
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				sectionid=secid[arg2];
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				productid=prodid[arg2];
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
			String url = "http://"+Login.ipads+"/a_offers/vsh/";
			JSONObject jobj= new JSONObject();
	        try {
				
	        	jobj.put("section",sectionid );
	        	jobj.put("product",productid );
				jobj.put("offer",e1.getText().toString());
				jobj.put("pid",productid);
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
			String url = "http://"+Login.ipads+"/a_section/vsh/";
			JSONArray jdata=JsonHandler.GetJson(url);
			secid=new String[jdata.length()];
			secnm=new String[jdata.length()];
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
                    String sectid = c.getString("secid");
                    String sections = c.getString("sections");
                    
                    secid[i]=sectid;
                    secnm[i]=sections;
                    		
                   
                   
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
	        ArrayAdapter<String> ad=new ArrayAdapter<String>(S_add_offers.this, android.R.layout.simple_spinner_item,secnm);
	        sp.setAdapter(ad);
	        
	        new getjson2().execute();
	      }
	}
	private class getjson2 extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://192.168.43.198:8000/a_product_details/vsh/";
			JSONArray jdata=JsonHandler.GetJson(url);
			prodid=new String[jdata.length()];
			prodnm=new String[jdata.length()];
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
                    String productid = c.getString("productid");
                    String product_name = c.getString("productname");
                    
                    prodid[i]=productid;
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
	        ArrayAdapter<String> ad=new ArrayAdapter<String>(S_add_offers.this, android.R.layout.simple_spinner_item,prodnm);
	        sp1.setAdapter(ad);
	      }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.s_add_offers, menu);
		return true;
	}

}

