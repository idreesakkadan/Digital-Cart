package com.example.digital_cart;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class S_add_product_details extends Activity {
	private String TAG = S_add_product_details.class.getSimpleName();
	Spinner sp;
	EditText e1,e2,e3,e4;
	Button b1;
	ImageButton img1;
	String secid[],secnm[];
	String prodid[],prodnm[];
	String sectionid;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_s_add_product_details);
		
		sp=(Spinner)findViewById(R.id.spinner1);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		img1=(ImageButton)findViewById(R.id.imageButton1);
		b1=(Button)findViewById(R.id.button1);
		new getjson1().execute();
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!sp.getSelectedItem().toString().equals("")&&!e1.getText().toString().equals("")&&!e2.getText().toString().equals("")&&!e3.getText().toString().equals("")&&!e4.getText().toString().equals(""))
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
		
		
	}

	private class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/a_product_details/vsh/";
			JSONObject jobj= new JSONObject();
	        try {
				jobj.put("secid",sectionid);
				jobj.put("productname",e1.getText().toString());
				jobj.put("price",e2.getText().toString());
				jobj.put("mfd_date",e3.getText()).toString();
				jobj.put("exp_date",e4.getText().toString());
				jobj.put("exp_date",e4.getText().toString());
				
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
			//http://192.168.43.198:8000/

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
	         ArrayAdapter<String> ad=new ArrayAdapter<String>(S_add_product_details.this, android.R.layout.simple_spinner_item,secnm);
		        sp.setAdapter(ad);
	      }
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.s_add_product_details, menu);
		return true;
	}

}
