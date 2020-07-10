package com.example.digital_cart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	EditText e1,e2;
	Button b1,b2;
	public static String uid,ty;
public static String ipads="192.168.43.198:8000";
	public String perm="okk";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new savejson().execute();
			}
		});
		
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),U_register.class);
				startActivity(i);
				
			}
		});
	}
	 private class savejson extends AsyncTask<Void, Void, Void>{
			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub

				String url = "http://"+Login.ipads+"/login/vsh/";
				JSONObject jobj= new JSONObject();
		        try {
//					jobj.put("uid",Login.uid);
					jobj.put("username", e1.getText().toString());
					jobj.put("password", e2.getText().toString());
					String s= JsonHandler.Postjson(url, jobj);
					JSONArray jdata =JsonHandler.Getjarray(s);
					if(jdata!=null)
					{
						perm="error";
						for (int i = 0; i < jdata.length(); i++) {
							perm="ok";
							JSONObject ob;
							ob = jdata.getJSONObject(i);
							 uid=ob.getString("uid");
							 ty=ob.getString("type");
							Log.e("Tag",ty);
							if(ty.equals("deliveryboy"))
							{
								Intent ii=new Intent(getApplicationContext(), Delivery_boy_home.class);
								startActivity(ii);
							}
							else	if(ty.equals("user"))
								{
									Intent ii=new Intent(getApplicationContext(),User_home.class);
									startActivity(ii);
								}
							else	if(ty.equals("shop"))
							{
								Intent ii=new Intent(getApplicationContext(),Shop_home.class);
								startActivity(ii);
							}
								
						}
						
					}
					else
					{
						perm="error";
					}
					
					Log.d("out", perm);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null; 
			}
			
			@Override
		      protected void onPostExecute(Void result) {
		         super.onPostExecute(result);
		         
		         if(!perm.equals("error"))
		         {

		        	 
		        	 Log.d("äuth", "ok");
		        	 Toast.makeText(getApplicationContext(), uid,3).show();
		        	 
		         }
		         else
		         {
		        	 Log.d("äuth", "error");
		         }
		       
		      }
			
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
