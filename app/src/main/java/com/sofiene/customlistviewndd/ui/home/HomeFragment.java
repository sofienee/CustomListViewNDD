package com.sofiene.customlistviewndd.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.sofiene.customlistviewndd.R;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final ListView listView;
        listView = root.findViewById(R.id.ListView);
        ArrayList<HashMap<String, String>> listItem = new ArrayList<>();
        HashMap<String, String> map;
        map = new HashMap<>();
        map.put("titre", "Word");
        map.put("description", "Editeur de text");
        map.put("img", String.valueOf(R.drawable.word));
        listItem.add(map);
        map = new HashMap<>();
        map.put("titre", "Exel");
        map.put("description", "Tableur");
        map.put("img", String.valueOf(R.drawable.excel));
        listItem.add(map);
        map = new HashMap<>();
        map.put("titre", "PowerPoint");
        map.put("description", "Logiciel de présentation");
        map.put("img", String.valueOf(R.drawable.powerpoint));
        listItem.add(map);
        map = new HashMap<>();
        map.put("titre", "Outlook");
        map.put("description", "Client de courrier électronique ");
        map.put("img", String.valueOf(R.drawable.outlook));
        listItem.add(map);
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(),
                listItem,
                R.layout.items,
                new String[]{"img", "titre", "description"},
                new int[]{R.id.img, R.id.titre, R.id.description});
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                HashMap<String,String> map=(HashMap<String,String>)  listView.getItemAtPosition(position);

                                                Toast.makeText(getActivity(), map.get("titre"), Toast.LENGTH_SHORT).show();


                                            }
                                        }
        );


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String,String> map=(HashMap<String,String>)  listView.getItemAtPosition(position);

                final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Sélection item");
                builder.setMessage("Votre choix:"+map.get("titre"));
                builder.setPositiveButton("ok",null);
                builder.show();
                return true;
            }
        });

        return root;

    }

}