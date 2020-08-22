package com.example.ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.provider.Settings.Secure;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DriverRegister extends Activity 

{
	EditText name,mno,address,ano,lno;
	
	TextView view_image_text;
	
	String D_ID="";
	
	String n="",m="",ad="",ano1="",lno1="";
	
	Button b1,upload;
	
	boolean check = true;
	 
	Bitmap bitmap;
	 
	ProgressDialog progressDialog ;
	

	String ImagePath = "image_path" ;
	
	String ServerUploadPath = "http://greensofttech.in/SVMS/dinsert.php";
	 
	String FILENAME="";
	
	String device_name="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_driver_register);
		
		name=(EditText)findViewById(R.id.driver_name);
		
		mno=(EditText)findViewById(R.id.driver_mno);
		
		address=(EditText)findViewById(R.id.driver_address);
		
		ano=(EditText)findViewById(R.id.driver_ano);
		
		lno=(EditText)findViewById(R.id.driver_lno);
		
		view_image_text=(TextView)findViewById(R.id.driver_view_image);
		
		b1=(Button)findViewById(R.id.driver_register);
		
		upload=(Button)findViewById(R.id.driver_upload_image);
		
		
		 D_ID = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
		 
		 device_name=Build.DEVICE;
		
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
				            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) 
				    {
				        //we are connected to a network
				        connected = true;
				    }
				    else
				        connected = false;
				
				if(connected==false)
				{
					Toast.makeText(getApplicationContext(), "Check the internet connection", Toast.LENGTH_LONG).show();

					//finish();
						
				}
				
				 n=name.getText().toString().trim();
				 m=mno.getText().toString().trim();
				 ad=address.getText().toString().trim();
				 ano1=ano.getText().toString().trim();
				 lno1=lno.getText().toString().trim();
				 // GetImageNameEditText = imageName.getText().toString();

	               
				
				if(n.equals("") || m.equals("") || ad.equals("")|| ano1.equals("")|| lno1.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please, Fill All Mandatory Fields", Toast.LENGTH_LONG).show();
				}
				else
				{
					
					if(m.length()==10)
					{
						if(ano1.length()==12)
						{
							if(lno1.length()==10)
							{
								boolean v1=VerhoeffAlgorithm.validateAadharNumber(ano1);
								
								if(v1==true)
								{
								
								 ImageUploadToServerFunction();
									
									
									//insert(n,m,ad,ano1,lno1);
									
									 /*Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
														 finish();
															Intent i=new Intent(DriverRegister.this,DriverLogin.class);
															startActivity(i);*/
								}
								else
								{
									Toast.makeText(getApplicationContext(), "Invalid Aadhar Id", Toast.LENGTH_LONG).show();
								}
							}
							else
							{
								Toast.makeText(getApplicationContext(), "Vehicle Number should be 10 digit", Toast.LENGTH_LONG).show();
							}
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Aadhar Number should be 12 digit", Toast.LENGTH_LONG).show();
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Mobile Number should be 10 digit", Toast.LENGTH_LONG).show();
					}
					
				}
				
			}
		});
		
		
		upload.setOnClickListener(new OnClickListener() 
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

				//	finish();
						
				}
				else
				{
					 Intent intent = new Intent();

		                intent.setType("image/*");

		                intent.setAction(Intent.ACTION_GET_CONTENT);

		                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);
				}
			}
		});
	}
	
	  protected void onActivityResult(int RC, int RQC, Intent I) {

	        super.onActivityResult(RC, RQC, I);

	        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

	       	 Uri uri = I.getData();
	         
	           Cursor cursor = getContentResolver().query(uri, null, null, null, null);
	           int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
	           int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
	           cursor.moveToFirst();
	           String filename = cursor.getString(nameIndex);
        		  
	           
	           view_image_text.setText(""+filename);

	            try {

	                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
 
	              //  imageView.setImageBitmap(bitmap);

	            } catch (IOException e) {

	                e.printStackTrace();
	            }
	        }
	    }
	 public void ImageUploadToServerFunction(){

	        ByteArrayOutputStream byteArrayOutputStreamObject ;

	        byteArrayOutputStreamObject = new ByteArrayOutputStream();

	        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

	        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

	        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

	        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

	            @Override
	            protected void onPreExecute() {

	                super.onPreExecute ();

	                progressDialog = ProgressDialog.show(DriverRegister.this," Uploading","Please Wait",false,false);
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
							Intent i=new Intent(DriverRegister.this,DriverLogin.class);
							startActivity(i);
	                }
	                else 
	                {
	                	 Toast.makeText(DriverRegister.this,"Your aadhar No / Mobile No already registered",Toast.LENGTH_LONG).show();
	                }
	              /*  else if(string               1.equals("Not inserted"))
	                {
	                	Toast.makeText(DriverRegister.this,"Failed to insert",Toast.LENGTH_LONG).show();
	                }
	                else if(string1.equals("failed"))
	                {
	                	 Toast.makeText(DriverRegister.this,"Your aadhar No / Mobile No already registered",Toast.LENGTH_LONG).show();
	                }*/

	                // Printing uploading success message coming from server on android app.
	               

	                // Setting image as transparent after done uploading.
	              //  imageView.setImageResource(android.R.color.transparent);


	            }

	            @Override
	            protected String doInBackground(Void... params) {

	                ImageProcessClass imageProcessClass = new ImageProcessClass();

	                HashMap<String,String> HashMapParams = new HashMap<String,String>();

	              //  HashMapParams.put(ImageName, GetImageNameEditText);

	                HashMapParams.put(ImagePath, ConvertImage);
	                HashMapParams.put("name", n);
	                HashMapParams.put("mno", m);
	                HashMapParams.put("address", ad);
	                HashMapParams.put("ano", ano1);
	                HashMapParams.put("lno", lno1);
	                HashMapParams.put("did", D_ID);
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
			 final Intent mainIntent = new Intent(DriverRegister.this, OverallProcess.class);
			 DriverRegister.this.startActivity(mainIntent);
			 DriverRegister.this.finish();
		}	

}
