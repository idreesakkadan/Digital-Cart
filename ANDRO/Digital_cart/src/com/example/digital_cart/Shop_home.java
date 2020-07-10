package com.example.digital_cart;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Shop_home extends Activity {
	Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_home);
		//b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
	//	b5=(Button)findViewById(R.id.button5);
		b6=(Button)findViewById(R.id.button6);
		//b7=(Button)findViewById(R.id.button7);
		//b8=(Button)findViewById(R.id.button8);
		//b9=(Button)findViewById(R.id.button9);
		//b10=(Button)findViewById(R.id.button10);
//		b1.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent i=new Intent(getApplicationContext(),S_update_profile.class);
//				startActivity(i);
//				
//			}
//		});
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),S_add_sections.class);
				startActivity(i);
				
			}
		});
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),S_add_product_details.class);
				startActivity(i);
				
			}
		});
		b4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),S_add_offers.class);
				startActivity(i);
				
			}
		});
	
		b6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),S_assign_work_to_delivery_boy.class);
				startActivity(i);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop_home, menu);
		return true;
	}

}
