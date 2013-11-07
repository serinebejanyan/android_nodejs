package com.mpc.productapplication;


import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CakesAdapter extends BaseAdapter{

	ArrayList<CakeApplication> cakesArray;
	private LayoutInflater mInflater;
	
	public CakesAdapter(ArrayList<CakeApplication> cakesList, Context context) {
		this.cakesArray = cakesList;
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return cakesArray.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View converView, ViewGroup parent) {
		View row = converView;
		
		if(row == null){
			row = mInflater.inflate(R.layout.item_cake_list, parent,false);
		}
		
		TextView name = (TextView) row.findViewById(R.id.row_name);
		TextView type  = (TextView) row.findViewById(R.id.row_type);
		TextView brand = (TextView)row.findViewById(R.id.row_brand);
		
		CakeApplication cake = cakesArray.get(position);
		name.setText(cake.getName());
		type.setText(cake.getType());
		brand.setText(cake.getBrand());
		
		return row;
	}

}
