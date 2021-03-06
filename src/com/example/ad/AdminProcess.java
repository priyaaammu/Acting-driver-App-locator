package com.example.ad;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;


public class AdminProcess extends Activity 
{
	
	String name="",q="",str="";
	ListView list;
	ImageView i1,i2,i3,i4;
	
	CustomAdapter adapter;
	static ArrayList<String> bus_array = new ArrayList<String>();
	static ArrayList<String> departure_array = new ArrayList<String>();
	
	String n="",ano="";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_process);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		bus_array.clear();
		departure_array.clear();
		
list = (ListView) findViewById(R.id.list_admin_process);

i1=(ImageView)findViewById(R.id.i1);
i2=(ImageView)findViewById(R.id.i2);
i3=(ImageView)findViewById(R.id.i3);
i4=(ImageView)findViewById(R.id.i4);
i1.setOnClickListener(new OnClickListener() 
{
	
	public void onClick(View v)
	{
		//bus_array.clear();
		//departure_array.clear();
		finish();
		Intent in=new Intent(AdminProcess.this,AdminProcess.class);
		startActivity(in);
	}
});
i2.setOnClickListener(new OnClickListener() 
{
	
	public void onClick(View v)
	{
		
		finish();
		Intent in=new Intent(AdminProcess.this,ViewUser.class);
		startActivity(in);
	}
});
i3.setOnClickListener(new OnClickListener() 
{
	
	public void onClick(View v)
	{
		finish();
		Intent in=new Intent(AdminProcess.this,ViewBus.class);
		startActivity(in);
		
	}
});
i4.setOnClickListener(new OnClickListener() 
{
	
	public void onClick(View v)
	{
		
		finish();
	}
});

		
	//	new TheTask().execute();
new GetContacts().execute();
	}
	
	
	
	 private class GetContacts extends AsyncTask<String, String, String> {
	      @Override
	      protected void onPreExecute() {
	         super.onPreExecute();
	         
	      }

		@Override
		protected String doInBackground(String... arg0) 
		{
			// TODO Auto-generated method stub
			
			
			try 
			{
				str = "http://greensofttech.in/SVMS/view_query.php";
						

			} 
			catch (Exception e) 
			{
				
				e.printStackTrace();
			}

			return str;
			
			
	        }
	         
	     	@Override
			protected void onPostExecute(String result) 
			{

				super.onPostExecute(result);
				
				HttpHandler sh = new HttpHandler();
		         // Making a request to url and getting response
		       //  String url = "http://greensofttech.in/SVMS/user_view_location.php";
		         String jsonStr = sh.makeServiceCall(result);
		      
		       
		         if (jsonStr != null) 
		         {
		            try 
		            {
		               JSONObject jsonObj = new JSONObject(jsonStr);
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
		               // Getting JSON Array node
		               JSONArray contacts = jsonObj.getJSONArray("Message");
		            
		               // looping through All Contacts
		               for (int i = 0; i < contacts.length(); i++) 
		               {
		            	   JSONObject c = contacts.getJSONObject(i);  
		            	 
						n=c.getString("Name");
						ano=c.getString("Ano");
						
						System.out.println(n);
						System.out.println(ano);
							
						bus_array.add(n);
						departure_array.add(ano);

		            	   
		               }
		              
		            	   
		               adapter = new CustomAdapter(AdminProcess.this, bus_array,departure_array);
						list.setAdapter(adapter);
		            }
		            catch(Exception e)
		            {
		            	
		            }
			}
	         
	         
		}
	      
		}

/*	class TheTask extends AsyncTask<String, String, String> 
	{

		@Override
		protected String doInBackground(String... arg0) 
		{
			
			try 
			{
				str = "http://greensofttech.in/SVMS/view_query.php";
						
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				//e.printStackTrace();
				Toast.makeText(getApplicationContext(), "Not Available", Toast.LENGTH_LONG).show();

				Intent in=new Intent(AdminProcess.this,AdminHome.class);
				startActivity(in);
			}

			return str;

		}

		@Override
		protected void onPostExecute(String result) 
		{

			super.onPostExecute(result);

			String response = result.toString();
			JSONArray dataJsonArr = null;
			try 
			{

				JsonParser jParser = new JsonParser();

				JSONObject json = jParser.getJSONFromUrl(result);

				dataJsonArr = json.getJSONArray("Message");

				for (int i = 0; i < dataJsonArr.length(); i++) 
				{

					JSONObject c = dataJsonArr.getJSONObject(i);

					name = c.getString("Name");
					q = c.getString("Ano");
					System.out.println(name);
					System.out.println(q);
					
					bus_array.add(name);
					departure_array.add(q);
				

				}

				adapter = new CustomAdapter(AdminProcess.this, bus_array,departure_array);
				list.setAdapter(adapter);
				list.setOnItemClickListener(new OnItemClickListener() 
				{

					
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
						
					Intent in=new Intent(A.this,BusMap.class);
					startActivity(in);
					
					
					}
				});

			} 
			catch (JSONException e) 
			{

				e.printStackTrace();

			}

		}
	}*/
	
	public void onBackPressed()
	{
		 final Intent mainIntent = new Intent(AdminProcess.this, AdminProcess.class);
		 AdminProcess.this.startActivity(mainIntent);
		 AdminProcess.this.finish();
	}
	
}
