package com.mpc.productapplication;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText mName;
	EditText mType;
	EditText mWeight;
	EditText mPrice;
	EditText mBrand;
	Button mSubmit;
	Button mGet;

	ArrayList<CakeApplication> CakeArray = new ArrayList<CakeApplication>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mName = (EditText) findViewById(R.id.name);
		mType = (EditText) findViewById(R.id.type);
		mWeight = (EditText) findViewById(R.id.weight);
		mPrice = (EditText) findViewById(R.id.price);
		mBrand = (EditText) findViewById(R.id.brand);

		mSubmit = (Button) findViewById(R.id.submit_button);
		mGet = (Button) findViewById(R.id.get_button);

		mSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				CakeApplication cake = new CakeApplication();

				cake.setBrand(mBrand.getText().toString());
				cake.setPrice(Float.valueOf(mPrice.getText().toString()));
				cake.setWeight(Float.valueOf(mWeight.getText().toString()));
				cake.setName(mName.getText().toString());
				cake.setType(mType.getText().toString());
				ServerCommunicationTask task = new ServerCommunicationTask();
				task.execute(cake);
				// CakeArray.add(cake);

			}
		});
		mGet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent openCakeList = new Intent(MainActivity.this, CakeListActivity.class);
				startActivity(openCakeList);
			}
		});
	}

	public static ArrayList<CakeApplication> sortPrice(
			ArrayList<CakeApplication> priceArray) {
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class ServerCommunicationTask extends
			AsyncTask<CakeApplication, Void, String> {

		@Override
		protected String doInBackground(CakeApplication... params) {
			CakeApplication cake = params[0];
			JSONObject json = new JSONObject();
			try {
				json.put("name", cake.getName());
				json.put("brand", cake.getBrand());
				json.put("type", cake.getType());
				json.put("price", cake.getPrice());
				json.put("weight", cake.getWeight());
				Log.i("request", json.toString());
				doHttpRequest(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	static void doHttpRequest(JSONObject requestParam) {
		final String SERVER_URL = "http://192.168.0.77:3001/addCake";
		URL url;
		try {
			url = new URL(SERVER_URL);
			String postString = requestParam.toString();

			HttpURLConnection urlConnection;
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-type",
					"application/json;charset = utf-8");
			urlConnection.setReadTimeout(10000);
			urlConnection.setConnectTimeout(15000);
			urlConnection.connect();

			OutputStream outPut = new BufferedOutputStream(
					urlConnection.getOutputStream());
			outPut.write(postString.getBytes());
			outPut.flush();
			int responseCode = urlConnection.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
