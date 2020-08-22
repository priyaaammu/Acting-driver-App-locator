package com.example.ad;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter1 extends BaseAdapter 
{

	private Activity activity;
	// private ArrayList&lt;HashMap&lt;String, String&gt;&gt; data;
	private static ArrayList title,notice,duration_arr,amount_arr;
	private static LayoutInflater inflater = null;

	public CustomAdapter1(Activity a, ArrayList b, ArrayList q) 
	{
	    activity = a;
	    this.title = b;
	    this.notice=q;
	   // this.duration_arr = duration;
	   // this.amount_arr=amount;

	    inflater = (LayoutInflater) activity
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	 
	
	public int getCount() 
	{
		
		return title.size();
	}


	public Object getItem(int position) 
	{
		
		 return position;
	}


	public long getItemId(int position) 
	{


		 return position;
	}

	
	public View getView(int position, View convertView, ViewGroup parent)
	{

		 View vi = convertView;
		    if (convertView == null)
		        vi = inflater.inflate(R.layout.list1, null);

		    TextView busname = (TextView) vi.findViewById(R.id.list1_name); 
		    String song = title.get(position).toString();
		    busname.setText(song);


		    TextView departure = (TextView) vi.findViewById(R.id.list1_q); 
		    String song2 = notice.get(position).toString();
		    departure.setText(song2);
		    
		   /* TextView duration2 = (TextView) vi.findViewById(R.id.duration1); 
		    String song3 = duration_arr.get(position).toString();
		    duration2.setText(song3);

		    TextView amount2 = (TextView) vi.findViewById(R.id.amount1); 
		    String song4 = amount_arr.get(position).toString();
		    amount2.setText("(Rs."+song4+")");*/

		    
		    

		return vi;
		
        
	}



	public void onClick(View v)
	{


	}

}
