package com.example.bharatiawaghad.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bharati Awaghad on 16-04-2016.
 */
public class CustomBaseAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CityWeatherDetails> cityWeatherDetailsArrayList;
    LayoutInflater inflater;
    public ArrayList<CityWeatherDetails> getCityWeatherDetailsArrayList() {
        return cityWeatherDetailsArrayList;
    }

    public CustomBaseAdapter(Context context, ArrayList<CityWeatherDetails> cityWeatherDetailsArrayList) {
        this.context = context;
        this.cityWeatherDetailsArrayList = cityWeatherDetailsArrayList;
        inflater=LayoutInflater.from(context);
    }

    /*  public CustomBaseAdapter(ArrayList<CityWeatherDetails> cityWeatherDetailsArrayList) {

                this.cityWeatherDetailsArrayList = cityWeatherDetailsArrayList;
            }
    */
    public void setCityWeatherDetailsArrayList(ArrayList<CityWeatherDetails> cityWeatherDetailsArrayList) {
        this.cityWeatherDetailsArrayList = cityWeatherDetailsArrayList;
    }

    @Override
    public int getCount() {
        return cityWeatherDetailsArrayList.size();
    }

    @Override
    public CityWeatherDetails getItem(int position) {
        return cityWeatherDetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            /*LayoutInflater layoutInflater=getLayoutInflater();
            View row;
            row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView location, temperature, weather, wind;
            location=(TextView)findViewById(R.id.location);
            temperature=(TextView)findViewById(R.id.temperature);
            weather=(TextView)findViewById(R.id.weather);
            wind=(TextView)findViewById(R.id.wind);
            System.out.println("Position" + position);
            System.out.println("City Name"+getCityWeatherDetailsArrayList().get(position).getCityName());
            location.setText(getCityWeatherDetailsArrayList().get(position).getCityName());
            temperature.setText(getCityWeatherDetailsArrayList().get(position).getTemperature().toString());
            weather.setText(getCityWeatherDetailsArrayList().get(position).getWeatherDescription());
            wind.setText(getCityWeatherDetailsArrayList().get(position).getWindSpeeed());
            return row;*/

        MyViewHolder myViewHolder;
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.row,null);
            myViewHolder= new MyViewHolder();
            convertView.setTag(myViewHolder);
        }
        else
        {
            myViewHolder=(MyViewHolder)convertView.getTag();
        }

        myViewHolder.location=(TextView)convertView.findViewById(R.id.location);
        myViewHolder.temperature=(TextView)convertView.findViewById(R.id.temperature);
        myViewHolder.weather=(TextView)convertView.findViewById(R.id.weather);
        myViewHolder.wind=(TextView)convertView.findViewById(R.id.wind);
        System.out.println("Position" + position);
        System.out.println("City Name" + getCityWeatherDetailsArrayList().get(position).getCityName());
        myViewHolder.location.setText(getCityWeatherDetailsArrayList().get(position).getCityName());
        myViewHolder.temperature.setText(String.valueOf(getCityWeatherDetailsArrayList().get(position).getTemperature()));
        myViewHolder.weather.setText(getCityWeatherDetailsArrayList().get(position).getWeatherDescription());
        System.out.println("Speed"+getCityWeatherDetailsArrayList().get(position).getWindSpeeed());
        myViewHolder.wind.setText(String.valueOf(getCityWeatherDetailsArrayList().get(position).getWindSpeeed()));
        return convertView;
    }
    private static class MyViewHolder{
        TextView location, temperature, weather, wind;

    }
}


