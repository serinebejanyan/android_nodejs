package com.mpc.productapplication;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class CakeListActivity extends Activity {
	ListView mCakesListView;
	CakesAdapter mAdapter;
	ArrayList<CakeApplication> cakesArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cake_list);

		cakesArray = new ArrayList<CakeApplication>();

		CakeApplication cake1 = CakeApplication.generateCake();
		cakesArray.add(cake1);

		CakeApplication cake2 = CakeApplication.generateCake();
		cakesArray.add(cake2);

		mCakesListView = (ListView) findViewById(R.id.cake_list);
		mAdapter = new CakesAdapter(cakesArray, getApplicationContext());
		mCakesListView.setAdapter(mAdapter);
	}

	class GetCakeTask extends AsyncTask<Void, Void, Void> {

		
		protected void onPostExecute() {
			super.onPostExecute(null);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
