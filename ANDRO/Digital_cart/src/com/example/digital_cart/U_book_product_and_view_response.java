package com.example.digital_cart;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.digital_cart.U_add_review_for_the_product.savejson;

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
import android.widget.Spinner;
import android.widget.Toast;

public class U_book_product_and_view_response extends Activity {
	EditText e1,e2,e3,e4;
	ListView l1;
	Spinner sp1;
	Button b1,b2;
	String pdtid,pdtnm,pri;
	private String TAG = U_post_complaint_and_view_reply.class.getSimpleName();
	ArrayList<HashMap<String, String>> contactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_book_product_and_view_response);
		e1=(EditText)findViewById(R.id.editText1);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		sp1=(Spinner)findViewById(R.id.spinner1);
		l1=(ListView)findViewById(R.id.listView1);
		contactList = new ArrayList<HashMap<String,String>>();
		
		
		pdtid=getIntent().getStringExtra("productid");
		pdtnm=getIntent().getStringExtra("product_name");
		pri=getIntent().getStringExtra("price");
		
		e4.setText(pdtid);
		e1.setText(pdtnm);
		e3.setText(pri);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!e1.getText().toString().equals("")&&!e3.getText().toString().equals("")&&!e4.getText().toString().equals(""))
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
	
	private class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/a_booking/vsh/";
			JSONObject jobj= new JSONObject();
	        try {
	        	jobj.put("uid",Login.uid);
				jobj.put("pid",e4.getText().toString());
				
				
				
				
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
	         Toast.makeText(getApplicationContext(), "success", 3).show();
	         
	      }
		
	}
	private class getjson1 extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/a_booking/vsh/";
			JSONArray jdata=JsonHandler.GetJson(url);
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
					String uid = c.getString("uid");
                    String pid = c.getString("pid");
                    String date = c.getString("date");
                    String status = c.getString("status");
                   
                    HashMap<String, String> contact =  new HashMap<String, String>();
                    contact.put("uid", uid);
                    contact.put("pid", pid);
                    contact.put("date", date);
                    contact.put("status", status);
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
	         ListAdapter adapter = new SimpleAdapter(U_book_product_and_view_response.this, contactList,
	            R.layout.view_booking, new String[]{ "pid","date","status"}, 
	               new int[]{R.id.textView2,R.id.textView4,R.id.textView6});
	         l1.setAdapter(adapter);
	      }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.u_book_product_and_view_response, menu);
		return true;
	}

}
