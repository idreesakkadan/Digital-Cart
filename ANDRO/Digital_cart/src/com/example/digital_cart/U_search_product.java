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
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class U_search_product extends Activity {
	EditText e1;
	Button b1;
	ListView l1;
	 private String TAG = U_post_complaint_and_view_reply.class.getSimpleName();
		
		ArrayList<HashMap<String, String>> contactList;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_search_product);
		e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		l1=(ListView)findViewById(R.id.listView1);
		contactList = new ArrayList<HashMap<String,String>>();
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new getjson1().execute();
			}
		});
		
		
		
	}
	private class getjson1 extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			//http://192.168.43.198:8000/

			String url = "http://"+Login.ipads+"/a_product_details/search/";
//			JSONArray jdata=JsonHandler.GetJson(url);
			
			//########################
			
			   JSONObject jobj= new JSONObject();
	        
//				jobj.put("uid",Login.uid);
				try {
					jobj.put("productname", e1.getText().toString());
					
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				String s= JsonHandler.Postjson(url, jobj);
				JSONArray jdata =JsonHandler.Getjarray(s);
			
			//####################3333
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
					
					  String product_name = c.getString("productname");
	                    String price = c.getString("price");
	                    String mfd_date = c.getString("mfd_date");
	                    String exp_date = c.getString("exp_date");
	                   
	                    HashMap<String, String> contact =  new HashMap<String, String>();
	                  
	                    contact.put("product_name", product_name);
	                    contact.put("price", price);
	                    contact.put("mfd_date", mfd_date);
	                    contact.put("exp_date", exp_date);
	            
	                    contactList.add(contact);
                    
                    if(product_name.equals(e1.getText().toString()))
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
	         ListAdapter adapter = new SimpleAdapter(U_search_product.this, contactList,
	 	            R.layout.view_product, new String[]{ "product_name","price","mfd_date","mfd_date"}, 
	 	               new int[]{R.id.textView7,R.id.textView8,R.id.textView9,R.id.textView10});
	 	         l1.setAdapter(adapter);
	      }
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.u_search_product, menu);
		return true;
	}

}
