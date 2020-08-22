package com.example.ad;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class AdminHome extends Activity 
{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_home);

	}


	
	public void onBackPressed()
	{
		 final Intent mainIntent = new Intent(AdminHome.this, AdminProcess.class);
		 AdminHome.this.startActivity(mainIntent);
		 AdminHome.this.finish();
	}
}
