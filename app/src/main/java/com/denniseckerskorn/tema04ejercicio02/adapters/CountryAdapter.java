package com.denniseckerskorn.tema04ejercicio02.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denniseckerskorn.tema04ejercicio02.R;
import com.denniseckerskorn.tema04ejercicio02.models.Country;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private List<Country> countries;

    public CountryAdapter(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFlag;
        private TextView tvCountryName;
        private TextView tvCapital;
        private TextView tvPopulation;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFlag = itemView.findViewById(R.id.ivFlag);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            tvCapital = itemView.findViewById(R.id.tvCapital);
            tvPopulation = itemView.findViewById(R.id.tvPopulation);
        }

        public void bindCountry(Country country) {
            ivFlag.setImageResource(country.getFlagResource());
            tvCountryName.setText(country.getCountryName());
            tvCapital.setText(country.getCapital());
            tvPopulation.setText(String.valueOf(country.getPopulation()));
        }
    }
}
