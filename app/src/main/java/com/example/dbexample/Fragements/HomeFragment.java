package com.example.dbexample.Fragements;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dbexample.Data.profileData;
import com.example.dbexample.EditableActivity;
import com.example.dbexample.MainActivity;
import com.example.dbexample.R;
import com.example.dbexample.adapters.recyclerAdapter;
import com.example.dbexample.databasePackages.dbProfiles;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static recyclerAdapter ra;
    private static Context cntxt;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Fetch data from the database
        cntxt = requireContext();
        dbProfiles db = new dbProfiles(requireContext());

        ArrayList<profileData> profileDataList = db.getAllEmployees();
        // Create the adapter with the fetched data
        this.ra = new recyclerAdapter(profileDataList, requireActivity());
        this.ra.setOnItemClickListener(new recyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClicking(int position) {
                // Create an Intent to open EditableActivity
                Intent intent = new Intent(requireContext(), EditableActivity.class);
                profileData p = profileDataList.get(position);
                intent.putExtra("position", position);
                intent.putExtra("Name", p.getName());
                intent.putExtra("ID", String.valueOf(p.getProfileID()));
                intent.putExtra("Age", String.valueOf(p.getAge()) );
                intent.putExtra("Phone", p.getPhone());
                intent.putExtra("JobDesc", p.getJobDesc());

                startActivity(intent);
            }
        });

        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Set up the RecyclerView
        RecyclerView myRecyclerView = view.findViewById(R.id.recycler);
        myRecyclerView.setAdapter(ra);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }

    public static void getNotifiedChanges(){
//        ra.notifyDataSetChanged();
        ra = new recyclerAdapter((new dbProfiles(cntxt)).getAllEmployees(),new MainActivity());
    }

}