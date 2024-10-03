package com.denniseckerskorn.tema04ejercicio02;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denniseckerskorn.tema04ejercicio02.adapters.CountryAdapter;
import com.denniseckerskorn.tema04ejercicio02.models.Country;
import com.denniseckerskorn.tema04ejercicio02.models.DomParser;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    private List<Country> countries;
    private RecyclerView rvList;
    private DomParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            parser = new DomParser();
            countries = parser.getCountries(); //TODO
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        rvList = findViewById(R.id.rvList);
        rvList.setHasFixedSize(true);
        rvList.setAdapter(new CountryAdapter(countries));
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }
}