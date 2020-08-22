package com.example.ad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Dialog;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DriverLocation extends FragmentActivity implements LocationListener 
{

	GoogleMap googleMap;
	GPSTracker gps;
	    
	Double latitude;
	Double longitude;
	
	String lat="",lng="";
	
	String mno="";
	

	 
	
     
     String address="",city="",state="",country="",postalcode="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver_location);
		googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	    gps=new GPSTracker(this); 
	
	//	latitude=gps.getLatitude();
	//	longitude=gps.getLongitude();
		System.out.println("latitude:  "+latitude);
		System.out.println("longitude:  "+longitude);
		 int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
		 
		 mno=GlobalVariables.MNO;
		 
	      
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
	 
	            if(location!=null)
	            {
	               onLocationChanged(location);
	            }
	        }
		
		
	}

	public void onLocationChanged(Location loc)
	{
		 latitude = loc.getLatitude();
    	 
         longitude = loc.getLongitude();
		
		
		 LatLng latLng = new LatLng(latitude, longitude);
		 
		 Geocoder geocoder;
	     List<Address> addresses;
	     geocoder = new Geocoder(this, Locale.getDefault());
	     
	     try
	     {

	      addresses = geocoder.getFromLocation(latitude, longitude, 1);
	      
	      address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
	      city = addresses.get(0).getLocality();
	       state = addresses.get(0).getAdminArea();
	       country = addresses.get(0).getCountryName();
	       postalcode = addresses.get(0).getPostalCode();
	     }
	     catch(Exception e)
	     {
	    	 
	     }
		 
		      
    	 
    	// googleMap.setMyLocationEnabled(true);
	     
     	 
	        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
	        
	        MarkerOptions mp = new MarkerOptions();

	        mp.position(new LatLng(latitude, longitude));
	        
	        googleMap.clear();
	        
	        mp.title(""+address+""+city);
	        
	        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16));
	        
	        googleMap.addMarker(new MarkerOptions().position(
	                new LatLng(latitude ,longitude)).title(""+address).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus1)));
	        
	        
	        
	        

	      // mp.title("Current Location");

	      // googleMap.addMarker(mp);
	        
	   
	    //  googleMap.addMarker(new MarkerOptions().position(
	         //       new LatLng(latitude, longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.bus1)));
      
	        try
	        {
	     //  Geocoder geocoder;
	    //  List<Address> addresses;
	   //   geocoder = new Geocoder(this, Locale.getDefault());

	      addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

	
	      
	     lat=""+latitude;
	     lng=""+longitude;
	     
	     
	    
	      
	    
	/*      System.out.println("Lat "+lat);
	     System.out.println("Lng "+lng);
	    System.out.println("BUS NO "+GlobalVariables.BUSNO);
	   System.out.println("Address "+address);
	    	      */

	      
	  /*   Timer t = new Timer();
	  t.scheduleAtFixedRate(new TimerTask()
	  {

	      public void run() 
	      {*/
	     if(lat != null)
	     {
	         
	    	  insert(GlobalVariables.VNO,address+" "+city,lat,lng); 
	     }
	    	  
	     /* }

	  },
	  
	  0, 8000);*/
	      
	        }
	        catch(Exception e)
	        {
	        	
	        }
	 
    
	}

	public void onProviderDisabled(String arg0)
	{
		
	}

	public void onProviderEnabled(String arg0)
	{
		
	}

	public void onStatusChanged(String arg0, int arg1, Bundle arg2) 
	{
		
	}
	private void insert(final String v,final  String o,final  String l,final  String lo){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramUsername = params[0];
                String paramAddress = params[1];
 
           
                
                
                
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("busno", v));
                nameValuePairs.add(new BasicNameValuePair("address", o));
                nameValuePairs.add(new BasicNameValuePair("lat", l));
                nameValuePairs.add(new BasicNameValuePair("lng", lo));
                nameValuePairs.add(new BasicNameValuePair("mno", mno));
                try 
                {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://greensofttech.in/SVMS/bus_location.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 
                    HttpResponse response = httpClient.execute(httpPost);
 
                    HttpEntity entity = response.getEntity();
 
          
                } 
                catch (ClientProtocolException e)
                {
 
                }
                catch (IOException e) 
                {
 
                }
                return "success";
            }
 
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
 
               // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(v,o,l,lo);
    }
 

	
}
