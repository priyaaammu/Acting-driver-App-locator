package com.example.ad;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UserViewLocation extends FragmentActivity implements LocationListener 
{

	String str="";
	
	String vno="",address="",mno="",lat="",lng="";
	
	GoogleMap googleMap;
	GPSTracker gps;
	    
	Double latitude;
	Double longitude;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_view_location);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.user_view_map)).getMap();
	    gps=new GPSTracker(this); 
		
		//new TheTask().execute();
	    
	   // latitude=gps.getLatitude();
	   // longitude=gps.getLongitude();
	    Timer t = new Timer();
		  t.scheduleAtFixedRate(new TimerTask()
		  {

		      public void run() 
		      {
		    	  new GetContacts().execute();
	    
	 
		      }

		  },
		  
		  0, 8000);
	    
		
	/*	int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
		 
		
		 
	      
	        if(status!=ConnectionResult.SUCCESS)
	        { 
	        	// Google Play Services are not available
	 
	            int requestCode = 10;
	            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
	            dialog.show();
	 
	        }
	        else 
	        { 

	           // googleMap.setMyLocationEnabled(true);
	 
	            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	 
	            Criteria criteria = new Criteria();
	 
	            String provider = locationManager.getBestProvider(criteria, true);
	 
	            Location location = locationManager.getLastKnownLocation(provider);
	            
	            locationManager.requestLocationUpdates(provider, 20000, 0, this);
	 
	            
	        }*/
	        
	      
	}

	

	public void onPointerCaptureChanged(boolean hasCapture) {
		// TODO Auto-generated method stub
		
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Deprecated
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
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
				str = "http://greensofttech.in/SVMS/user_view_location.php";
						

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
		            	   vno = c.getString("Vno");
							address = c.getString("Address");
							mno = c.getString("Mno");
							latitude = c.getDouble("Lat");
							longitude = c.getDouble("Lng");
							
						
							
							String f_ans=""+vno+""+address+""+mno+""+lat+""+lng;
							  int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
								 if(status!=ConnectionResult.SUCCESS)
							        { 
							        	// Google Play Services are not available
							 
							            int requestCode = 10;
							          //  Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
							          //  dialog.show();
							 
							        }
							        else 
							        { 

							         //  googleMap.setMyLocationEnabled(true);
							 
							            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
							 
							            Criteria criteria = new Criteria();
							 
							            String provider = locationManager.getBestProvider(criteria, true);
							 
							            Location location = locationManager.getLastKnownLocation(provider);
							            
							          //  if(location!=null)
							          //  {
							            
							           // }
							            
							        }
								 
									System.out.println(vno);
									System.out.println(address);
									System.out.println(mno);
									System.out.println(latitude);
									System.out.println(longitude);

									/* googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16));
									
									  googleMap.addMarker(new MarkerOptions().position(
						    	                new LatLng(latitude,longitude)).title(""+f_ans).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus1)));
									*/
									
									 LatLng latLng = new LatLng(latitude, longitude);
					            	 
						            	// googleMap.setMyLocationEnabled(false);
						             	 
						     	        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
						     	        
						     	        MarkerOptions mp = new MarkerOptions();

						     	        mp.position(new LatLng(latitude, longitude));

						     	        mp.title(""+f_ans);

						     	       // googleMap.addMarker(mp);
						     	        
						     	       googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 12));
						     	       
						     	      googleMap.addMarker(new MarkerOptions().position(
						  	                new LatLng(latitude ,longitude)).title(""+address).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus1)));
						  	        
						  	        

		            	   
		               }
		            }
		            catch(Exception e)
		            {
		            	
		            }
			}
	         
	         
		}
	      
		}
	 
	/*
	 * 
	 * 
	 * 
	 * class TheTask extends AsyncTask<String, String, String> 
	{

		@Override
		protected String doInBackground(String... arg0) 
		{
			
			try 
			{
				str = "http://greensofttech.in/SVMS/user_view_location.php";
						

			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				
				

				for (int i = 0; i < dataJsonArr.length(); i++) {

					JSONObject c = dataJsonArr.getJSONObject(i);

					vno = c.getString("Vno");
					address = c.getString("Address");
					mno = c.getString("Mno");
					lat = c.getString("Lat");
					lng = c.getString("Lng");
					
					System.out.println(vno);
					System.out.println(address);
					System.out.println(mno);
					System.out.println(lat);
					System.out.println(lng);
					
					String f_ans=""+vno+""+address+""+mno+""+lat+""+lng;
					
					  googleMap.addMarker(new MarkerOptions().position(
		    	                new LatLng(Double.parseDouble(lat) ,Double.parseDouble(lng))).title(""+f_ans).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus1)));
					
					


				}

				

			} 
			catch (JSONException e) 
			{

				e.printStackTrace();

			}

		}
	}
	 */
}
