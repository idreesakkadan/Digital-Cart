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
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

public class U_add_rating_for_delivery_boy extends Activity {
	EditText e1,e2;
	RatingBar rb1;
	Button b1;
	Float rating;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_u_add_rating_for_delivery_boy);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		rb1=(RatingBar)findViewById(R.id.ratingBar1);
		b1=(Button)findViewById(R.id.button1);
		
		
		
		rb1.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
				// TODO Auto-generated method stub
			   rating=rb1.getRating();
			}
		});
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				new savejson().execute();
				Toast.makeText(getApplicationContext(), "Success", 3).show();
				
			}
		});
	}
	public class savejson extends AsyncTask<Void, Void, Void>{
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = "http://"+Login.ipads+"/a_rating/vsh/";
			JSONObject jobj= new JSONObject();
	        try {
	        	jobj.put("uid","23");
	        	jobj.put("d_id",e1.getText().toString());
	        	jobj.put("comments",e2.getText().toString());
				jobj.put("deliveryboy_rating",rating+"");
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
		getMenuInflater().inflate(R.menu.u_add_rating_for_delivery_boy, menu);
		return true;
	}

}
