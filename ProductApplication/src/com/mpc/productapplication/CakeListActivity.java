package com.mpc.productapplication;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.RenderScript.RSErrorHandler;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class CakeListActivity extends Activity {
	ListView mCakesListView;
	CakesAdapter mAdapter;
	ArrayList<Cake> cakesArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cake_list);
		cakesArray = new ArrayList<Cake>();
		mCakesListView = (ListView) findViewById(R.id.cake_list);
		mAdapter = new CakesAdapter(getApplicationContext());
		mAdapter.setCakesArray(cakesArray);
		mCakesListView.setAdapter(mAdapter);
		GetCakeTask task = new GetCakeTask();
		task.execute();
	}

	class GetCakeTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			mAdapter.notifyDataSetChanged();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			JSONArray result = doHttpRequest();
			Log.i("result", result.toString());
			try {
				for (int i = 0; i < result.length(); ++i) {
					JSONObject tmpJSON = result.getJSONObject(i);
					Log.i("current", tmpJSON.toString());
					Cake cake = new Cake();
					//cake.setBrand(tmpJSON.getString("brand"));
					cake.setName(tmpJSON.getString("name"));
					//cake.setType(tmpJSON.getString("type"));
					cakesArray.add(cake);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	static JSONArray doHttpRequest() {
		final String SERVER_URL = "http://127.0.0.1:3001/getCakes";
		URL url;
		JSONArray cakeJSONArray = null;
		try {
			url = new URL(SERVER_URL);
			HttpURLConnection urlConnection;
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = urlConnection.getInputStream();

			BufferedReader streamReader = new BufferedReader(
					new InputStreamReader(in, "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();
			String inputStr;
			while ((inputStr = streamReader.readLine()) != null) {
				responseStrBuilder.append(inputStr);
			}
			cakeJSONArray = new JSONArray(responseStrBuilder.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cakeJSONArray;

	}

}
