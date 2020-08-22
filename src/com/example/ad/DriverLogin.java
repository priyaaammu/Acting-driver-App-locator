package com.example.ad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DriverLogin extends Activity 
{
	EditText uname,mno;
	Button b1;
	
	TextView t1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver_login);
		
		
		uname=(EditText)findViewById(R.id.driver_login_vno);
		
		mno=(EditText)findViewById(R.id.driver_login_mno);
		
		b1=(Button)findViewById(R.id.driver_login_b1);
		
		t1=(TextView)findViewById(R.id.driver_login_signup);
		
		boolean connected = false;
		ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || 
		            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
		        //we are connected to a network
		        connected = true;
		    }
		    else
		        connected = false;
		    
		    
		
		if(connected==false)
		{
			Toast.makeText(getApplicationContext(), "Check the internet connection", Toast.LENGTH_LONG).show();

			finish();
			
		}
		
		t1.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v) 
			{
				 finish();
                 Intent intent = new Intent(DriverLogin.this, DriverRegister.class);
               
                 startActivity(intent);
			}
		});
		
		b1.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v)
			{
				
				boolean connected = false;
				ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
				    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || 
				            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
				        //we are connected to a network
				        connected = true;
				    }
				    else
				        connected = false;
				    
				    
				
				if(connected==false)
				{
					Toast.makeText(getApplicationContext(), "Check the internet connection", Toast.LENGTH_LONG).show();

					finish();
					
				}
			String n=uname.getText().toString().trim();
			String p=mno.getText().toString().trim();
			
			if(n.equals("") || p.equals(""))
			{
				Toast.makeText(getApplicationContext(), "Please, Fill All Mandatory Fields", Toast.LENGTH_LONG).show();

				
				
			
			}
			else
			{
				//GlobalVariables.MNO=n;
			 login(n,p);
			 
			
				//Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
			}
				
			}
		});
		
		
	}
	private void login(final String username,final String aa) {
		 
        class LoginAsync extends AsyncTask<String, Void, String>{
 
            private Dialog loadingDialog;
 
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(DriverLogin.this, "Please wait", "Loading...");
            }
 
            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];
                String pass = params[1];
 
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("vno", username));
              nameValuePairs.add(new BasicNameValuePair("mno", aa));
                String result = null;
 
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://greensofttech.in/SVMS/dlogin.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 
                    HttpResponse response = httpClient.execute(httpPost);
 
                    HttpEntity entity = response.getEntity();
 
                    is = entity.getContent();
 
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
 
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } 
                catch (ClientProtocolException e) 
                {
                    e.printStackTrace();
                } 
                catch (UnsupportedEncodingException e) 
                {
                    e.printStackTrace();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
                return result;
            }
 
            @Override
            protected void onPostExecute(String result)
            {
                String s = result.trim();
                loadingDialog.dismiss();
                if(s.equalsIgnoreCase("success"))
                {
                	
                	 GlobalVariables.VNO=username;
                	 GlobalVariables.MNO=aa;
                	 
                	// Toast.makeText(getApplicationContext(), "BNO"+StudentGlobalVariables.BUSNO, Toast.LENGTH_LONG).show();
                	
                	 Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                	 finish();
                    Intent intent = new Intent(DriverLogin.this, DriverLocation.class);
                  
                    startActivity(intent);
                }
                else 
                {
                    Toast.makeText(getApplicationContext(), "Invalid Details", Toast.LENGTH_LONG).show();
                    finish();
                    Intent in=new Intent(DriverLogin.this,DriverLogin.class);
                    startActivity(in);
                }
            }
        }
 
        LoginAsync la = new LoginAsync();
        la.execute(username, aa);
 
    }
 


	public void onBackPressed()
	{
		 final Intent mainIntent = new Intent(DriverLogin.this, OverallProcess.class);
		 DriverLogin.this.startActivity(mainIntent);
		 DriverLogin.this.finish();
	}
}