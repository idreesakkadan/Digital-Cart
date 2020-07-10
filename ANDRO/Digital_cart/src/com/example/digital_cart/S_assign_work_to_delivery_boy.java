package com.example.digital_cart;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class S_assign_work_to_delivery_boy extends Activity {
	EditText wrk;
	Spinner sp;
Button b;
String []dnm;
String[] dbid;
String dbdid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_s_assign_work_to_delivery_boy);
		
		wrk=(EditText)findViewById(R.id.editText1);
		sp=(Spinner)findViewById(R.id.spinner1);
		b=(Button)findViewById(R.id.button1);
		new getjson1().execute();
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				dbdid=dbid[arg2];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!wrk.getText().toString().equals("")&&!sp.getSelectedItem().toString().equals(""))
				{
				new savejson().execute();
				Toast.makeText(getApplicationContext(), "success", 3).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "error", 3).show();
				}
			}
		});
	}
	
	
	public class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://192.168.43.198:8000/a_assigned_work/vsh/";
			JSONObject jobj= new JSONObject();
	        try {
				
	        	jobj.put("dbid",dbdid);
	        	jobj.put("work",wrk.getText().toString());
	        	
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
			String url = "http://"+Login.ipads+"/reg_manage_d_boy/vsh/";
			JSONArray jdata=JsonHandler.GetJson(url);
			
			 dbid=new String[jdata.length()];
			 dnm=new String[jdata.length()];
			 
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
					String id=c.getString("id");
					String name =c.getString("name");
					
					 dbid[i]=id;
					 dnm[i]=name;
					 
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
	         ArrayAdapter<String> ad=new ArrayAdapter<String>(S_assign_work_to_delivery_boy.this, android.R.layout.simple_spinner_item,dnm);
			 sp.setAdapter(ad);
	      }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.s_assign_work_to_delivery_boy, menu);
		return true;
	}

}
