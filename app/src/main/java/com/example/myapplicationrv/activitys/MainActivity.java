package com.example.myapplicationrv.activitys;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationrv.R;
import com.example.myapplicationrv.adapters.CustomeAdapter;
import com.example.myapplicationrv.classes.myData;
import com.example.myapplicationrv.models.Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Data> arr;
    private RecyclerView recyclerView;
    private CustomeAdapter customeAdapter;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.rvcon);
        searchBar = findViewById(R.id.search_bar); // שדה חיפוש

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arr = new ArrayList<>();
        for (int i = 0; i < myData.nameArray.length; i++) {
            arr.add(new Data(
                    myData.nameArray[i],
                    myData.description[i],
                    myData.drawableArray[i],
                    myData.id_[i]
            ));
        }

        customeAdapter = new CustomeAdapter(arr);
        recyclerView.setAdapter(customeAdapter);

        // מאזין לשינויי טקסט בחיפוש
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filter(String text) {
        ArrayList<Data> filteredList = new ArrayList<>();
        for (Data item : arr) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        customeAdapter.updateList(filteredList);
    }
}
