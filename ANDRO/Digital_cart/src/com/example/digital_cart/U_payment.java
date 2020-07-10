package com.example.digital_cart;

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
import android.widget.Toast;

public class U_payment extends Activity {
	EditText e1,e2,e3;
	Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_payment);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!e1.getText().toString().equals("")&&!e2.getText().toString().equals("")&&!e3.getText().toString().equals(""))
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
		
	}
	public class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/a_payment/android/";
			JSONObject jobj= new JSONObject();
	        try {
	        	jobj.put("pid",e1.getText().toString());
				jobj.put("amount",e2.getText().toString());
				jobj.put("mode",e3.getText().toString());
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
		getMenuInflater().inflate(R.menu.u_payment, menu);
		return true;
	}

}
