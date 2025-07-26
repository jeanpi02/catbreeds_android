package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.models.CatBreed;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_CAT_BREED = "cat_breed";

    private ImageView ivCatImage;
    private TextView tvDescription;
    private TextView tvOrigin;
    private TextView tvIntelligence;
    private TextView tvAdaptability;
    private TextView tvEnergyLevel;
    private TextView tvLifeSpan;
    private TextView tvTemperament;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        setupToolbar();
        loadCatBreedData();
    }

    private void initViews() {
        ivCatImage = findViewById(R.id.iv_cat_image);
        tvDescription = findViewById(R.id.tv_description);
        tvOrigin = findViewById(R.id.tv_origin);
        tvIntelligence = findViewById(R.id.tv_intelligence);
        tvAdaptability = findViewById(R.id.tv_adaptability);
        tvEnergyLevel = findViewById(R.id.tv_energy_level);
        tvLifeSpan = findViewById(R.id.tv_life_span);
        tvTemperament = findViewById(R.id.tv_temperament);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }
        
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void loadCatBreedData() {
        CatBreed catBreed = (CatBreed) getIntent().getSerializableExtra(EXTRA_CAT_BREED);
        
        if (catBreed == null) {
            finish();
            return;
        }

        // Configurar el título del toolbar con el nombre de la raza
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(catBreed.getName());
        }

        // Cargar datos en las vistas
        tvDescription.setText(catBreed.getDescription() != null ? catBreed.getDescription() : "No description available");
        tvOrigin.setText("Country: " + (catBreed.getOrigin() != null ? catBreed.getOrigin() : "Unknown"));
        tvIntelligence.setText("Intelligence: " + catBreed.getIntelligence() + "/5");
        tvAdaptability.setText("Adaptability: " + catBreed.getAdaptability() + "/5");
        tvEnergyLevel.setText("Energy Level: " + catBreed.getEnergyLevel() + "/5");
        tvLifeSpan.setText("Life Span: " + (catBreed.getLifeSpan() != null ? catBreed.getLifeSpan() + " years" : "Unknown"));
        tvTemperament.setText("Temperament: " + (catBreed.getTemperament() != null ? catBreed.getTemperament() : "Unknown"));

        // Cargar imagen
        String referenceImageId = catBreed.getReferenceImageId();
        if (referenceImageId != null && !referenceImageId.isEmpty()) {
            String imageUrl = "https://cdn2.thecatapi.com/images/" + referenceImageId + ".jpg";
            
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.loading_placeholder)
                    .error(R.mipmap.hairycat)
                    .into(ivCatImage);
        } else {
            ivCatImage.setImageResource(R.mipmap.hairycat);
        }
    }
} 