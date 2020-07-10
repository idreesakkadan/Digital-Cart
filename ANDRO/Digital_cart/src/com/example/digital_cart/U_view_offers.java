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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class U_view_offers extends Activity {
	ListView l1;
	 private String TAG = U_post_complaint_and_view_reply.class.getSimpleName();
		
		ArrayList<HashMap<String, String>> contactList;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_view_offers);
		l1=(ListView)findViewById(R.id.listView1);
		contactList = new ArrayList<HashMap<String,String>>();
		new getjson1().execute();
	}
	private class getjson1 extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/a_offers/vsh/";
			JSONArray jdata=JsonHandler.GetJson(url);
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
					String section = c.getString("section");
                    String offer = c.getString("offer");
                    String pid = c.getString("pid");
                   
                    HashMap<String, String> contact =  new HashMap<String, String>();
                    contact.put("section", section);
                    contact.put("offer", offer);
                    contact.put("pid", pid);
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
	         ListAdapter adapter = new SimpleAdapter(U_view_offers.this, contactList,
	            R.layout.view_offers, new String[]{"section","offer","pid"}, 
	               new int[]{R.id.textView4,R.id.textView5,R.id.textView6});
	         l1.setAdapter(adapter);
	      }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.u_view_offers, menu);
		return true;
	}

}
