package com.mpc.productapplication;


import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CakesAdapter extends BaseAdapter{

	private ArrayList<Cake> cakesArray = new ArrayList<Cake>();
	private LayoutInflater mInflater;
	
	public CakesAdapter(Context context) {
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setCakesArray(ArrayList<Cake> array){
		this.cakesArray = array;
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
		
		Cake cake = cakesArray.get(position);
		name.setText(cake.getName());
		//type.setText(cake.getType());
		//brand.setText(cake.getBrand());
		
		return row;
	}

}
