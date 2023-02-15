package com.example.houses;

public class HouseModel {
    private int id;
    private String image;
    private int price;
    private int bedrooms;
    private int bathrooms;
    private int size;
    private String description;
    private String zip;
    private String city;
    private int latitude;
    private int longitude;
    private String createdDate;

    public HouseModel() {
    }

    //    public HouseItem(int id, String image, int price, int bedrooms, int bathrooms, int size, String description, String zip, String city, int latitude, int longitude, String createdDate) {
//        this.id = id;
//        this.image = image;
//        this.price = price;
//        this.bedrooms = bedrooms;
//        this.bathrooms = bathrooms;
//        this.size = size;
//        this.description = description;
//        this.zip = zip;
//        this.city = city;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.createdDate = createdDate;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
