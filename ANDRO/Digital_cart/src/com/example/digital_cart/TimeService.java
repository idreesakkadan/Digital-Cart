package com.example.digital_cart;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class TimeService extends Service {
	// constant
	public static final long NOTIFY_INTERVAL = 30 * 1000; // 10 seconds
	SQLiteDatabase db;
	String uid;;
	// run on another Thread to avoid crash
	private Handler mHandler = new Handler();
	// timer handling
	private Timer mTimer = null;
	//AudioManager audioManager;
	
	Gpstracker g=new Gpstracker(getApplicationContext());

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		// cancel if already existed
		if (mTimer != null) {
			mTimer.cancel();
		} else {
			// recreate new
			mTimer = new Timer();
		}
		// schedule task
		mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0,
				NOTIFY_INTERVAL);
	}

	class TimeDisplayTimerTask extends TimerTask {

		@Override
		public void run() {
			// run on another thread
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					
					
				}
			});
		}

	}

	private double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}




class getjson1 extends AsyncTask<Void, Void, Void>{
	int cnt=0;
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		String url = "http://192.168.43.198:8000/a_assigned_work/vsh/";
		JSONArray jdata=JsonHandler.GetJson(url);
		if(jdata!=null)
		{
			try {
			for (int i = 0; i < jdata.length(); i++) {
                JSONObject c;
				c = jdata.getJSONObject(i);
				String dbid = c.getString("dbid");
               
                if(dbid.equals(Login.uid))
                {
               cnt=cnt++;
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
        if(cnt==1)
        {
        	
        }
        if(cnt==0)
        {
        	
        }
	}
      }
class savejson1 extends AsyncTask<Void, Void, Void>{
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		
		
		String url = "http://192.168.43.238:8000/a_assigned_work/upvsh/";
		JSONObject jobj= new JSONObject();
        try {
        	jobj.put("dbid",Login.uid );
        	//jobj.put("lat",lat );
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


//In manifest
// <service android:name="com.example.bus.TimeService" >
       // </service>	

//In main page
//startService(new //Intent(getApplicationContext(),TimeService.class));