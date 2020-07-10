package com.example.digital_cart;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class U_view_product_details extends Activity {
	ListView l1;
    private String TAG = U_view_product_details.class.getSimpleName();
	
	ArrayList<HashMap<String, String>> contactList;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_view_product_details);
		l1=(ListView)findViewById(R.id.listView1);
		contactList = new ArrayList<HashMap<String,String>>();
		new getjson1().execute();
		
		l1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				
				HashMap<String, String>hmap=(HashMap<String, String>)arg0.getItemAtPosition(arg2);
				Intent i=new Intent(getApplicationContext(),U_book_product_and_view_response.class);
				i.putExtra("productid",hmap.get("productid"));
				i.putExtra("product_name",hmap.get("product_name"));
				i.putExtra("price",hmap.get("price"));
				startActivity(i);
				return false;
			}
		});
		
	}
	

	private class getjson1 extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/a_product_details/vsh/";
			JSONArray jdata=JsonHandler.GetJson(url);
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
					String productid = c.getString("productid");
					String secid = c.getString("secid");
                    String product_name = c.getString("productname");
                    String price = c.getString("price");
                    String mfd_date = c.getString("mfd_date");
                    String exp_date = c.getString("exp_date");
                   
                    HashMap<String, String> contact =  new HashMap<String, String>();
                    contact.put("productid", productid);
                    contact.put("secid", secid);
                    contact.put("product_name", product_name);
                    contact.put("price", price);
                    contact.put("mfd_date", mfd_date);
                    contact.put("exp_date", exp_date);
            
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
	         ListAdapter adapter = new SimpleAdapter(U_view_product_details.this, contactList,
	            R.layout.view_product, new String[]{ "productid","secid","product_name","price","mfd_date","mfd_date"}, 
	               new int[]{R.id.textView12,R.id.textView6,R.id.textView7,R.id.textView8,R.id.textView9,R.id.textView10});
	         l1.setAdapter(adapter);
	      }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.u_view_product_details, menu);
		return true;
	}

}
