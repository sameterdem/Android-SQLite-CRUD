package com.example.mytravelguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samet ERDEM on 25.06.2020.
 */
public class TravelListAdapter extends BaseAdapter {
    List<TravelList> travelDataList = new ArrayList<>();
    LayoutInflater inflater;
    Context context;

    public TravelListAdapter(Context _context, List<TravelList> _travelListData) {
        this.context = _context;
        this.travelDataList = _travelListData;
    }

    @Override
    public int getCount() {
        return travelDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = LayoutInflater.from(context);
        View travel_list_view = inflater.inflate(R.layout.travel_list_view, null);
        TextView  title = (TextView) travel_list_view.findViewById(R.id.title);
        TextView  country = (TextView) travel_list_view.findViewById(R.id.country);
        TextView  description = (TextView) travel_list_view.findViewById(R.id.description);

        // Set
        title.setText(travelDataList.get(position).getTitle());
        description.setText(travelDataList.get(position).getDescription());
        country.setText(travelDataList.get(position).getCountry()+" / "+travelDataList.get(position).getCity());

        return travel_list_view;
    }
}
