package com.example.ad;

import java.text.DecimalFormat;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class CalculatingDistance 
{
public static void main(String args[])
{
	CalculatingDistance c= new CalculatingDistance();
	double d1=10.790483,d2=78.704674,d3=10.826460,d4=78.680717;
	LatLng st1=new LatLng(d1, d2);
	LatLng st2=new LatLng(d3, d4);
	System.out.println(""+c.CalculationByDistance(st1,st2));
}

public  double CalculationByDistance(LatLng st1, LatLng st2) {
    int Radius = 6371;// radius of earth in Km
    double lat1 = st1.latitude;
    double lat2 = st2.latitude;
    double lon1 = st1.longitude;
    double lon2 = st2.longitude;
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
            + Math.cos(Math.toRadians(lat1))
            * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
            * Math.sin(dLon / 2);
    double c = 2 * Math.asin(Math.sqrt(a));
    double valueResult = Radius * c;
    double km = valueResult / 1;
    DecimalFormat newFormat = new DecimalFormat("####");
    int kmInDec = Integer.valueOf(newFormat.format(km));
    double meter = valueResult % 1000;
    int meterInDec = Integer.valueOf(newFormat.format(meter));
    Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
            + " Meter   " + meterInDec);

    return Radius * c;
}
}
