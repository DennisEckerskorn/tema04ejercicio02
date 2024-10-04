package com.denniseckerskorn.tema04ejercicio02.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denniseckerskorn.tema04ejercicio02.R;
import com.denniseckerskorn.tema04ejercicio02.models.Country;
import com.denniseckerskorn.tema04ejercicio02.models.OnItemClickListener;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private final List<Country> countries;
    private final OnItemClickListener listener;

    public CountryAdapter(List<Country> countries, OnItemClickListener listener) {
        this.countries = countries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_country, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.bindCountry(country, listener);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivFlag;
        private final TextView tvCountryName;
        private final TextView tvCapital;
        private final TextView tvPopulation;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFlag = itemView.findViewById(R.id.ivFlag);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            tvCapital = itemView.findViewById(R.id.tvCapital);
            tvPopulation = itemView.findViewById(R.id.tvPopulation);
        }

        public void bindCountry(Country country, OnItemClickListener listener) {
            if (country != null) {
                if (country.getFlagResource() != 0) {
                    ivFlag.setVisibility(View.VISIBLE);
                    ivFlag.setImageResource(country.getFlagResource()); //Todo: Puede ser nulo, arreglar
                } else {
                    ivFlag.setVisibility(View.GONE);
                }
                tvCountryName.setText(country.getCountryName());
                tvCapital.setText(country.getCapital());
                tvPopulation.setText(String.valueOf(country.getPopulation()));
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(country);
                }
            });
        }
    }
}
