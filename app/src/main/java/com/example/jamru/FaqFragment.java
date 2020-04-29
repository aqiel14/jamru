package com.example.jamru;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TwoLineListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FaqFragment extends Fragment {

    public FaqFragment(){
        //empty Constructor
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
        View v = inflater.inflate(R.layout.fragment_faq, container, false);

        ListView resultsListView = (ListView) v.findViewById(R.id.faqMenu);

        HashMap<String, String> nameAddresses = new HashMap<>();
        nameAddresses.put(getString(R.string.Q1), getString(R.string.A1));
        nameAddresses.put(getString(R.string.Q2), getString(R.string.A2));
        nameAddresses.put(getString(R.string.Q3), getString(R.string.A3));

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(getContext(), listItems, R.layout.fragment_faq,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.question, R.id.answer});

        Iterator it = nameAddresses.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        resultsListView.setAdapter(adapter);

        return v;
    }
}
