package com.example.ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.ad.DriverRegister.ImageProcessClass;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegister extends Activity 
{
	
	EditText name,mno,address,ano;
	
	String device_name="";
	
	//String R_STMT="";
	
	TextView view_image_text;
	
	String n="",m="",ad="",ano1="";
	Button b1;
	 boolean check = true;
	// Bitmap bitmap;
	  ProgressDialog progressDialog ;
	 // String ImageName = "image_name" ;

	  //  String ImagePath = "image_path" ;
	 String ServerUploadPath = "http://greensofttech.in/SVMS/uinsert.php";
	 
	// String FILENAME="";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_register);
		
		name=(EditText)findViewById(R.id.user_name);
		mno=(EditText)findViewById(R.id.user_mno);
		address=(EditText)findViewById(R.id.user_address);
		ano=(EditText)findViewById(R.id.user_email);
	//	lno=(EditText)findViewById(R.id.user_lno);
		
		//view_image_text=(TextView)findViewById(R.id.view_image);
		
		b1=(Button)findViewById(R.id.user_register);
	//	upload=(Button)findViewById(R.id.upload_image);
		
		device_name=Build.DEVICE;
		//device_name="";
		
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
				
				 n=name.getText().toString().trim();
				 m=mno.getText().toString().trim();
				 ad=address.getText().toString().trim();
				 ano1=ano.getText().toString().trim();
				// lno1=lno.getText().toString().trim();
				 // GetImageNameEditText = imageName.getText().toString();

	               
				
				if(n.equals("") || m.equals("") || ad.equals("")|| ano1.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please, Fill All Mandatory Fields", Toast.LENGTH_LONG).show();
				}
				else
				{
					
					if(m.length()==10)
					{
						
						 ImageUploadToServerFunction();				
									
					//	insert(n,m,ad,ano1,device_name);
									
									// Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
													//	 finish();
													//		Intent i=new Intent(UserRegister.this,UserLogin.class);
													//		startActivity(i);
							
						
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Mobile Number should be 10 digit", Toast.LENGTH_LONG).show();
					}
					
				}
				
			}
		});
		
	}
		
	 public void ImageUploadToServerFunction(){

	       // ByteArrayOutputStream byteArrayOutputStreamObject ;

	       // byteArrayOutputStreamObject = new ByteArrayOutputStream();

	       // bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
//
	      //  byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

	      //  final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

	        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

	            @Override
	            protected void onPreExecute() {

	                super.onPreExecute ();

	                progressDialog = ProgressDialog.show(UserRegister.this," Uploading","Please Wait",false,false);
	            }

	            @Override
	            protected void onPostExecute(String string1) {

	                super.onPostExecute(string1);

	                // Dismiss the progress dialog after done uploading.
	                progressDialog.dismiss();
	                
	              //  Toast.makeText(DriverRegister.this,""+string1,Toast.LENGTH_LONG).show();
	                
	                if(string1.equals("inserted"))
	                {
	                	// Toast.makeText(DriverRegister.this,"success",Toast.LENGTH_LONG).show();
	                	 Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
						 finish();
							Intent i=new Intent(UserRegister.this,UserLogin.class);
							startActivity(i);
	                }
	                else 
	                {
	                	 Toast.makeText(UserRegister.this,"Your Email / Mobile No already registered",Toast.LENGTH_LONG).show();
	                }
	         


	            }

	            @Override
	            protected String doInBackground(Void... params) {

	                ImageProcessClass imageProcessClass = new ImageProcessClass();

	                HashMap<String,String> HashMapParams = new HashMap<String,String>();

	              //  HashMapParams.put(ImageName, GetImageNameEditText);

	              //  HashMapParams.put(ImagePath, ConvertImage);
	                HashMapParams.put("name", n);
	                HashMapParams.put("mno", m);
	                HashMapParams.put("address", ad);
	                HashMapParams.put("email", ano1);
	               // HashMapParams.put("lno", lno1);
	               // HashMapParams.put("did", D_ID);
	                HashMapParams.put("dname", device_name);

	                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);
	                
	                //Toast.makeText(DriverRegister.this,"Final "+FinalData,Toast.LENGTH_LONG).show();

	                return FinalData;
	            }
	        }
	        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

	      AsyncTaskUploadClassOBJ.execute();
	    }

	    public class ImageProcessClass{

	        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

	            StringBuilder stringBuilder = new StringBuilder();

	            try 
	            {

	                URL url;
	                HttpURLConnection httpURLConnectionObject ;
	                OutputStream OutPutStream;
	                BufferedWriter bufferedWriterObject ;
	                BufferedReader bufferedReaderObject ;
	                int RC ;

	                url = new URL(requestURL);

	                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

	                httpURLConnectionObject.setReadTimeout(19000);

	                httpURLConnectionObject.setConnectTimeout(19000);

	                httpURLConnectionObject.setRequestMethod("POST");

	                httpURLConnectionObject.setDoInput(true);

	                httpURLConnectionObject.setDoOutput(true);

	                OutPutStream = httpURLConnectionObject.getOutputStream();

	                bufferedWriterObject = new BufferedWriter(

	                        new OutputStreamWriter(OutPutStream, "UTF-8"));

	                bufferedWriterObject.write(bufferedWriterDataFN(PData));

	                bufferedWriterObject.flush();

	                bufferedWriterObject.close();

	                OutPutStream.close();

	                RC = httpURLConnectionObject.getResponseCode();

	                if (RC == HttpsURLConnection.HTTP_OK) {

	                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

	                    stringBuilder = new StringBuilder();

	                    String RC2;

	                    while ((RC2 = bufferedReaderObject.readLine()) != null){

	                        stringBuilder.append(RC2);
	                    }
	                }

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return stringBuilder.toString();
	        }

	        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

	            StringBuilder stringBuilderObject;

	            stringBuilderObject = new StringBuilder();

	            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

	                if (check)

	                    check = false;
	                else                     
	                    stringBuilderObject.append("&");

	                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

	                stringBuilderObject.append("=");

	                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
	            }

	            return stringBuilderObject.toString();
	        }

	    }

	public void onBackPressed()
	{
		 final Intent mainIntent = new Intent(UserRegister.this, OverallProcess.class);
		 UserRegister.this.startActivity(mainIntent);
		 UserRegister.this.finish();
	}
	

}
