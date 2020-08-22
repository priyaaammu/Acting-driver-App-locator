package com.example.ad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends Activity 
{

	EditText name,pass;
	Button b1,b2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(   savedInstanceState);
		setContentView(R.layout.activity_admin_login);
		//convertView.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
		name=(EditText) findViewById(R.id.admin_login_name);
		pass=(EditText) findViewById(R.id.admin_login_pass);
		
		b1=(Button)findViewById(R.id.admin_login);
	//	b2=(Button)findViewById(R.id.admin_pass);
		
		b1.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v) 
			{
				String n=name.getText().toString().trim();
				String p=pass.getText().toString().trim();
				
				if(n.equals("admin") && p.equals("admin"))
				{
					
					finish();
					Intent in=new Intent(AdminLogin.this,AdminProcess.class);
					startActivity(in);
				}
				else
				{
					//finish();
					Toast.makeText(getApplicationContext(), "name/Password is incorrect", Toast.LENGTH_LONG).show();
					//Intent in=new Intent(AdminLogin.this,AdminLogin.class);
					//startActivity(in);
				}
				
			}
		});
		/*b2.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
			
				finish();
				Intent in=new Intent(AdminLogin.this,OverallProcess.class);
				startActivity(in);
			}
		});*/
		
	}


}