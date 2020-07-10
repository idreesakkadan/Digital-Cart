package com.example.digital_cart;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.digital_cart.S_add_offers.savejson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class U_register extends Activity {
EditText e1,e2,e3,e4,e5,pass;
Button b1,b2;

private String TAG = U_register.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_register);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		e5=(EditText)findViewById(R.id.editText5);
		pass=(EditText)findViewById(R.id.editText6);
		b1=(Button)findViewById(R.id.button2);
		b2=(Button)findViewById(R.id.button1);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!e1.getText().toString().equals("")&&!e2.getText().toString().equals("")&&!e3.getText().toString().equals("")&&!e4.getText().toString().equals("")&&!e5.getText().toString().equals("")&&!pass.getText().toString().equals(""))
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
		            e1.setText("");
		            e2.setText("");
		            e3.setText("");
		            e4.setText("");
		            e5.setText("");
		           pass.setText("");
				
				
			}
		});
		
	}
	private class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://192.168.43.198:8000/a_register/vsh/";
			JSONObject jobj= new JSONObject();
	        try {
	        	
	        	
	            
				jobj.put("first_name",e1.getText().toString());
				jobj.put("second_name",e2.getText().toString());
				jobj.put("number",e3.getText().toString());
				jobj.put("address",e4.getText().toString());
				jobj.put("email",e5.getText().toString());
				jobj.put("password",pass.getText().toString());
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
	         
	         Intent i=new Intent(getApplicationContext(),Login.class);
				startActivity(i);
	      }
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.u_register, menu);
		return true;
	}
	
}
