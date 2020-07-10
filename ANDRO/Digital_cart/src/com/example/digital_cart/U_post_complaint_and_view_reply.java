package com.example.digital_cart;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.digital_cart.S_add_sections.savejson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class U_post_complaint_and_view_reply extends Activity {
	ListView l1;
	EditText e1;
	Button b1,b2;
private String TAG = U_post_complaint_and_view_reply.class.getSimpleName();
	
	ArrayList<HashMap<String, String>> contactList;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_post_complaint_and_view_reply);
		l1=(ListView)findViewById(R.id.listView1);
		e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		contactList = new ArrayList<HashMap<String,String>>();
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!e1.getText().toString().equals(""))
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
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new getjson1().execute();
			}
		});
 
	}
	public class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/complaint_reply/vsh/";
			JSONObject jobj= new JSONObject();
	        try {
	        	jobj.put("uid","23");
				jobj.put("complaint",e1.getText().toString());
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
			String url = "http://"+Login.ipads+"/complaint_reply/vsh/";
			JSONArray jdata=JsonHandler.GetJson(url);
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
                    String uid = c.getString("uid");
                    String date = c.getString("date");
                    String complaint = c.getString("complaint");
                    String reply = c.getString("reply");
                    
                    HashMap<String, String> contact =  new HashMap<String, String>();
                    contact.put("uid", uid);
                    contact.put("date", date);
                    contact.put("complaint", complaint);
                    contact.put("reply", reply);
                    contactList.add(contact);
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
	         ListAdapter adapter = new SimpleAdapter(U_post_complaint_and_view_reply.this, contactList,
	            R.layout.reply, new String[]{ "complaint","date","reply"}, 
	               new int[]{R.id.textView4, R.id.textView5,R.id.textView6});
	         l1.setAdapter(adapter);
	      }
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.u_post_complaint_and_view_reply, menu);
		return true;
	}

}
