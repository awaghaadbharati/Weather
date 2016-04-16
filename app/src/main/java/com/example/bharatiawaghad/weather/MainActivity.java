package com.example.bharatiawaghad.weather;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button=(Button)findViewById(R.id.button);
        final EditText location=(EditText)findViewById(R.id.editText);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setText("");
            }
        });
        /*final TextView weather=(TextView) findViewById(R.id.textView2);
        final TextView weatherDescription=(TextView) findViewById(R.id.textView3);*/
        final RequestQueue queue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://api.openweathermap.org/data/2.1/find/name?q=" + location.getText().toString() + "&type=like";
                //Toast.makeText(getBaseContext(),url,Toast.LENGTH_SHORT).show();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("list");
                            //System.out.println("Length" + jsonArray.length());
                            /*static*/
                            ArrayList<CityWeatherDetails> cityList = new ArrayList<CityWeatherDetails>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject cityDetails = (JSONObject) jsonArray.get(i);
                               // System.out.println("Hi");
                                JSONArray weather=(JSONArray)cityDetails.get("weather");
                                String cityName = (String)cityDetails.get("name");
                                String weatherDescription= (String)((JSONObject)weather.get(0)).get("main");
                                double temp=(Double)((JSONObject)cityDetails.get("main")).get("temp");
                                System.out.println("Speed"+((JSONObject)cityDetails.get("wind")).get("speed"));
                                try
                                {
                                    double speed=(double)((JSONObject)cityDetails.get("wind")).get("speed");
                                    CityWeatherDetails row = new CityWeatherDetails(cityName,weatherDescription,temp,speed);
                                    cityList.add(row);
                                }
                                catch (ClassCastException e)
                                {
                                    System.out.println("Error"+e);
                                }
                                //Double temp =100.9d;
                                //int speed =100;
                               // CityWeatherDetails row = new CityWeatherDetails(cityName,weatherDescription,temp,speed);
                                //JSONObject temp=(JSONObject)cityDetails.get("main");

                            }
                            ListView cityListView = (ListView) findViewById(R.id.cityListView);
                            CustomBaseAdapter  CityListadapter=new CustomBaseAdapter(getBaseContext(),cityList);
                            //SimpleAdapter CityListadapter = new SimpleAdapter(getBaseContext(), cityList, R.layout.row, new String[]{"Name", "Temperature", "Weather", "Wind"}, new int[]{R.id.location, R.id.temperature, R.id.weather, R.id.wind});
                            cityListView.setAdapter(CityListadapter);
                            CityListadapter.notifyDataSetChanged();
                            System.out.println("Hello");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //      mTextView.setText("That didn't work!");
                        Toast.makeText(getBaseContext(), "That did not work", Toast.LENGTH_SHORT).show();
                        System.out.println("Error:" + volleyError);
                    }
                });
                queue.add(jsonObjectRequest);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
