package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.CatBreedsAdapter;
import com.example.myapplication.api.CatApiService;
import com.example.myapplication.models.CatBreed;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.thecatapi.com/v1/";

    private RecyclerView rvCatBreeds;
    private ProgressBar progressBar;
    private TextView tvEmptyState;
    private SearchView searchView;
    
    private CatBreedsAdapter adapter;
    private CatApiService apiService;
    
    // Lista completa para búsqueda local
    private List<CatBreed> allCatBreeds = new ArrayList<>();

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
        
        initViews();
        setupRecyclerView();
        setupSearchView();
        setupRetrofit();
        
        loadCatBreeds();
    }
    
    private void initViews() {
        rvCatBreeds = findViewById(R.id.rv_cat_breeds);
        progressBar = findViewById(R.id.progress_bar);
        tvEmptyState = findViewById(R.id.tv_empty_state);
        searchView = findViewById(R.id.search_view);
    }
    
    private void setupRecyclerView() {
        adapter = new CatBreedsAdapter();
        rvCatBreeds.setLayoutManager(new LinearLayoutManager(this));
        rvCatBreeds.setAdapter(adapter);
    }
    
    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterCatBreeds(query);
                return true;
            }
            
            @Override
            public boolean onQueryTextChange(String newText) {
                filterCatBreeds(newText);
                return true;
            }
        });
    }
    
    private void setupRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        apiService = retrofit.create(CatApiService.class);
    }
    
    private void loadCatBreeds() {
        showLoading();
        
        Call<List<CatBreed>> call = apiService.getBreeds(20, 1);
        call.enqueue(new Callback<List<CatBreed>>() {
            @Override
            public void onResponse(Call<List<CatBreed>> call, Response<List<CatBreed>> response) {
                hideLoading();
                
                if (response.isSuccessful() && response.body() != null) {
                    allCatBreeds = new ArrayList<>(response.body());
                    
                    if (allCatBreeds.isEmpty()) {
                        showEmptyState();
                    } else {
                        showCatBreeds(allCatBreeds);
                    }
                } else {
                    showError("Error al cargar las razas de gatos");
                }
            }
            
            @Override
            public void onFailure(Call<List<CatBreed>> call, Throwable t) {
                hideLoading();
                showError("Error de conexión. Verifica tu internet.");
            }
        });
    }
    
    private void filterCatBreeds(String query) {
        if (allCatBreeds.isEmpty()) {
            return;
        }
        
        if (query == null || query.trim().isEmpty()) {
            // Mostrar todas las razas si no hay búsqueda
            showCatBreeds(allCatBreeds);
            return;
        }
        
        // Filtrar localmente
        List<CatBreed> filteredBreeds = new ArrayList<>();
        String searchQuery = query.toLowerCase().trim();
        
        for (CatBreed breed : allCatBreeds) {
            // Buscar en nombre, origen y temperamento
            boolean matchesName = breed.getName() != null && 
                    breed.getName().toLowerCase().contains(searchQuery);

            
            if (matchesName) {
                filteredBreeds.add(breed);
            }
        }
        
        if (filteredBreeds.isEmpty()) {
            showEmptyState();
        } else {
            showCatBreeds(filteredBreeds);
        }
    }
    
    // Métodos para manejar la interfaz de usuario
    
    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        rvCatBreeds.setVisibility(View.GONE);
        tvEmptyState.setVisibility(View.GONE);
    }
    
    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
    
    private void showCatBreeds(List<CatBreed> catBreeds) {
        rvCatBreeds.setVisibility(View.VISIBLE);
        tvEmptyState.setVisibility(View.GONE);
        adapter.setCatBreeds(catBreeds);
    }
    
    private void showError(String message) {
        rvCatBreeds.setVisibility(View.GONE);
        tvEmptyState.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    
    private void showEmptyState() {
        rvCatBreeds.setVisibility(View.GONE);
        tvEmptyState.setVisibility(View.VISIBLE);
    }
}