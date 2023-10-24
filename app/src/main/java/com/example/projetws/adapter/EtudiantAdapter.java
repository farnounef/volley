package com.example.projetws.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetws.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.EtudiantViewHolder> {

    private JSONArray etudiants;
    private Context context;

    public EtudiantAdapter(Context context) {
        this.context = context;
        this.etudiants = new JSONArray();
    }

    public void setEtudiants(JSONArray etudiants) {
        this.etudiants = etudiants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EtudiantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.etudiant_item, parent, false);
        return new EtudiantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantViewHolder holder, int position) {
        try {
            JSONObject etudiant = etudiants.getJSONObject(position);
            holder.nomTextView.setText(etudiant.getString("nom"));
            holder.prenomTextView.setText(etudiant.getString("prenom"));
            // Ajoutez d'autres attributs d'étudiant au besoin
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return etudiants.length();
    }

    static class EtudiantViewHolder extends RecyclerView.ViewHolder {
        TextView nomTextView;
        TextView prenomTextView;

        EtudiantViewHolder(@NonNull View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.nomTextView);
            prenomTextView = itemView.findViewById(R.id.prenomTextView);
            // Ajoutez d'autres vues au besoin
        }
    }
}
