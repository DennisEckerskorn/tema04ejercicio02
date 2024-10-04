package com.denniseckerskorn.tema04ejercicio02;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denniseckerskorn.tema04ejercicio02.adapters.CountryAdapter;
import com.denniseckerskorn.tema04ejercicio02.models.Country;
import com.denniseckerskorn.tema04ejercicio02.models.DomParser;
import com.denniseckerskorn.tema04ejercicio02.models.OnItemClickListener;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private List<Country> countries;
    private RecyclerView rvList;
    private DomParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            parser = new DomParser();
            parser.loadFile(this, R.raw.countries);
            countries = parser.getCountries("country", this);
            rvList = findViewById(R.id.rvList);
            rvList.setHasFixedSize(true);
            rvList.setAdapter(new CountryAdapter(countries, this));
            rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            //rvList.setLayoutManager(new GridLayoutManager(this, 2));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al cargar los datos de los países.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onItemClick(Country country) {
        Toast.makeText(MainActivity.this, country.getCountryName(), Toast.LENGTH_SHORT).show();
    }
}