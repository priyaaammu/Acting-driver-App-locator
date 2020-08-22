package com.example.ad;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OverallProcess extends Activity 
{
	
	Button b1,b2,b3;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overall_process);
		
		b1=(Button)findViewById(R.id.admin);
		b2=(Button)findViewById(R.id.user);
		b3=(Button)findViewById(R.id.driver);
		
		
		
		b1.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				
				finish();
				Intent in=new Intent(OverallProcess.this,AdminLogin.class);
				startActivity(in);
				
			}
		});
		b2.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				
				finish();
				Intent in=new Intent(OverallProcess.this,UserLogin.class);
				startActivity(in);
			}
		});
		
		b3.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				
				finish();
				Intent in=new Intent(OverallProcess.this,DriverLogin.class);
				startActivity(in);
			}
		});
	}

	public void onBackPressed()
	{
		// final Intent mainIntent = new Intent(OverallProcess.this, OverallProcess.class);
		// DriverLogin.this.startActivity(mainIntent);
		 finish();
	}
}
