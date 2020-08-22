package com.example.ad;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		new Handler().postDelayed(new Runnable() {
	         public void run() {
	             final Intent mainIntent = new Intent(SplashScreen.this, OverallProcess.class);
	             SplashScreen.this.startActivity(mainIntent);
	             SplashScreen.this.finish();
	         }
	     }, 5000);
	}

	
}
