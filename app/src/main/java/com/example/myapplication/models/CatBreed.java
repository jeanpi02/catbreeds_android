package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CatBreed implements Serializable {
    
    @SerializedName("weight")
    private Weight weight;
    
    @SerializedName("id")
    private String id;
    
    @SerializedName("name")
    private String name;
    
    @SerializedName("temperament")
    private String temperament;
    
    @SerializedName("origin")
    private String origin;
    
    @SerializedName("country_codes")
    private String countryCodes;
    
    @SerializedName("country_code")
    private String countryCode;
    
    @SerializedName("description")
    private String description;
    
    @SerializedName("life_span")
    private String lifeSpan;
    
    @SerializedName("adaptability")
    private int adaptability;
    
    @SerializedName("affection_level")
    private int affectionLevel;
    
    @SerializedName("child_friendly")
    private int childFriendly;
    
    @SerializedName("energy_level")
    private int energyLevel;
    
    @SerializedName("intelligence")
    private int intelligence;
    
    @SerializedName("wikipedia_url")
    private String wikipediaUrl;
    
    @SerializedName("reference_image_id")
    private String referenceImageId;
    
    @SerializedName("image")
    private Image image;
    
    public CatBreed() {}
    
    // Getters and Setters
    public Weight getWeight() {
        return weight;
    }
    
    public void setWeight(Weight weight) {
        this.weight = weight;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTemperament() {
        return temperament;
    }
    
    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    public String getCountryCodes() {
        return countryCodes;
    }
    
    public void setCountryCodes(String countryCodes) {
        this.countryCodes = countryCodes;
    }
    
    public String getCountryCode() {
        return countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLifeSpan() {
        return lifeSpan;
    }
    
    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }
    
    public int getAdaptability() {
        return adaptability;
    }
    
    public void setAdaptability(int adaptability) {
        this.adaptability = adaptability;
    }
    
    public int getAffectionLevel() {
        return affectionLevel;
    }
    
    public void setAffectionLevel(int affectionLevel) {
        this.affectionLevel = affectionLevel;
    }
    
    public int getChildFriendly() {
        return childFriendly;
    }
    
    public void setChildFriendly(int childFriendly) {
        this.childFriendly = childFriendly;
    }
    
    public int getEnergyLevel() {
        return energyLevel;
    }
    
    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }
    
    public int getIntelligence() {
        return intelligence;
    }
    
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    
    public String getWikipediaUrl() {
        return wikipediaUrl;
    }
    
    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }
    
    public String getReferenceImageId() {
        return referenceImageId;
    }
    
    public void setReferenceImageId(String referenceImageId) {
        this.referenceImageId = referenceImageId;
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
} 