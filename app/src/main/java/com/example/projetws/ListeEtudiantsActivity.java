package com.example.projetws;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetws.adapter.EtudiantAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListeEtudiantsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EtudiantAdapter etudiantAdapter;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etudiants);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        etudiantAdapter = new EtudiantAdapter(this);
        recyclerView.setAdapter(etudiantAdapter);

        // Initialisation de la RequestQueue de Volley
        requestQueue = Volley.newRequestQueue(this);

        // Appel de la méthode pour récupérer la liste des étudiants depuis le Web Service
        fetchEtudiants();
    }

    private void fetchEtudiants() {
        String url = "http://localhost/projet/ws/loadEtudiant.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, (String) null, // Utilisez (String) null ici
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Traitement de la réponse JSON et ajout des étudiants à l'adaptateur
                        etudiantAdapter.setEtudiants(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gérer les erreurs de requête
                    }
                });
        requestQueue.add(request);
    }


}
