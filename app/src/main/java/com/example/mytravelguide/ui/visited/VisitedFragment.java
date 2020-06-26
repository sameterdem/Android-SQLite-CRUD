package com.example.mytravelguide.ui.visited;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mytravelguide.DetailActivity;
import com.example.mytravelguide.R;
import com.example.mytravelguide.SQLiteHelper;
import com.example.mytravelguide.TravelList;
import com.example.mytravelguide.TravelListAdapter;
import com.example.mytravelguide.TravelModel;

import java.util.ArrayList;
import java.util.List;


import com.example.mytravelguide.R;

public class VisitedFragment extends Fragment {

    private VisitedViewModel visitedViewModel;

    ListView travelListView;
    List<TravelModel> list;
    SQLiteHelper DB;
    ArrayAdapter<String> adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_unvisited, container, false);

        travelListView = view.findViewById(R.id.travelList);
        DB = new SQLiteHelper(getActivity());

        // Get data.
        list = DB.GetTravelList(1);
        List<TravelList> listData = new ArrayList<>();
        for (int i = 0; i< list.size(); i++) {
            listData.add(i, new TravelList(
                    list.get(i).getTitle(),
                    list.get(i).getCountry(),
                    list.get(i).getCity(),
                    list.get(i).getDescription(),
                    false));
        }

        TravelListAdapter adapter = new TravelListAdapter(getActivity(), listData);
        travelListView.setAdapter(adapter);

        // Open detail
        travelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("ID", list.get(position).getID());
                startActivityForResult(intent,1 );

            }
        });

        // Is empty.
        View empty = view.findViewById(R.id.empty);
        travelListView.setEmptyView(empty);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        list = DB.GetTravelList(1);
        List<TravelList> listData = new ArrayList<>();
        for (int i = 0; i< list.size(); i++) {
            listData.add(i, new TravelList(
                    list.get(i).getTitle(),
                    list.get(i).getCountry(),
                    list.get(i).getCity(),
                    list.get(i).getDescription(),
                    false));
        }

        TravelListAdapter adapter = new TravelListAdapter(getActivity(), listData);
        travelListView.setAdapter(adapter);
    }
}