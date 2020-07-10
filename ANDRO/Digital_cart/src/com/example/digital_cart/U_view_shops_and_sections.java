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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class U_view_shops_and_sections extends Activity {
	ListView l1;
    private String TAG = U_post_complaint_and_view_reply.class.getSimpleName();
	
	ArrayList<HashMap<String, String>> contactList;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_view_shops_and_sections);
		l1=(ListView)findViewById(R.id.listView1);
		contactList = new ArrayList<HashMap<String,String>>();
		new getjson1().execute();
		
		l1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String, String>hmap=(HashMap<String, String>)arg0.getItemAtPosition(arg2);
				Intent i=new Intent(getApplicationContext(),U_view_sections.class);
				i.putExtra("sid",hmap.get("sid"));
				startActivity(i);		
				}
		});
	}
	private class getjson1 extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/reg_shop/vsh/";
			JSONArray jdata=JsonHandler.GetJson(url);
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
					String sid = c.getString("sid");
                    String name = c.getString("name");
                    String address = c.getString("address");
                    String ph = c.getString("ph");
                    String email = c.getString("email");
                    
                    HashMap<String, String> contact =  new HashMap<String, String>();
                    contact.put("sid", sid);
                    contact.put("name", name);
                    contact.put("address", address);
                    contact.put("ph", ph);
                    contact.put("email", email);
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
	         ListAdapter adapter = new SimpleAdapter(U_view_shops_and_sections.this, contactList,
	            R.layout.view_shop, new String[]{ "name","address","ph","email"}, 
	               new int[]{R.id.textView5, R.id.textView6,R.id.textView7,R.id.textView8});
	         l1.setAdapter(adapter);
	      }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.u_view_shops_and_sections, menu);
		return true;
	}

}
