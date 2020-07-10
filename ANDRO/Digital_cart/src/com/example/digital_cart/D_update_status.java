package com.example.digital_cart;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class D_update_status extends Activity {
	private String TAG = D_update_status.class.getSimpleName();
	
	ArrayList<HashMap<String, String>> contactList;
ListView l1;
RadioButton r1,r2,r3;
Button b1;
String asid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_d_update_status);
		contactList = new ArrayList<HashMap<String,String>>();
		
		l1=(ListView)findViewById(R.id.listView1);
		r1=(RadioButton)findViewById(R.id.radio0);
		r2=(RadioButton)findViewById(R.id.radio1);
		r3=(RadioButton)findViewById(R.id.radio2);
		b1=(Button)findViewById(R.id.button1);
		
		new getjson1().execute();
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				new savejson().execute();
				Toast.makeText(getApplicationContext(), "ghgh", 3).show();
			}
		});
		l1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String,String> hmap=(HashMap<String, String>)arg0.getItemAtPosition(arg2);
				asid=hmap.get("aid");
				Toast.makeText(getApplicationContext(), asid, 3).show();
			}
		});
		
	}
	private class getjson1 extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://192.168.43.198:8000/a_assigned_work/vsh/";
			JSONArray jdata=JsonHandler.GetJson(url);
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
					String aid = c.getString("aid");
                    String d_id = c.getString("d_id");
                    String work = c.getString("work");
                    String status = c.getString("status");
                    
                    
                    HashMap<String, String> contact =  new HashMap<String, String>();
                    contact.put("aid", aid);
                    contact.put("work", work);
                    contact.put("status", status);
                    if(d_id.equals(Login.uid)&&!status.equals("completed"))
                    {
                    contactList.add(contact);
                    }
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
	         ListAdapter adapter = new SimpleAdapter(D_update_status.this, contactList,
	            R.layout.work, new String[]{ "aid","work","status"}, 
	               new int[]{R.id.textView2,R.id.textView4,R.id.textView6});
	         l1.setAdapter(adapter);
		}
	      }
	
	
	private class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			
			String st;
			if(r1.isChecked()==true)
				st=r1.getText().toString();
			else if(r2.isChecked()==true)
				st=r2.getText().toString();
			else
				st=r3.getText().toString();
			
			String url = "http://192.168.43.238:8000/a_assigned_work/upvsh/";
			JSONObject jobj= new JSONObject();
	        try {
	        	jobj.put("aid",asid );
	        	jobj.put("status",st );
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.d_update_status, menu);
		return true;
	}
	
	
//	String st;
//	if(r1.isChecked()==true)
//		st=r1.getText().toString();
//	else if(r2.isChecked()==true)
//		st=r2.getText().toString();
//	else
//		st=r3.getText().toString();
	
//	jobj.put("aid",asid );
//	jobj.put("status",st );

}
