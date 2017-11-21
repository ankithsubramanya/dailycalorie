package com.example.ankith.dailycalorie;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ankit on 02-05-2017.
 * Parcelable POJO that packaged the information recieved from fat secret API
 */

public class FoodItem implements Parcelable{
    private String name;
    private long id;
    private String servingSize;
    private double calories;
    private String description;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getServingSize() {
        return servingSize;
    }
    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }
    public double getCalories() {
        return calories;
    }
    public void setCalories(double calories) {
        this.calories = calories;
    }
    public int getHashCode(){
        return (int)(this.id / 37 + this.calories * 29);
    }
    public boolean equals(FoodItem other){
        return this.id == other.getId();
    }
    public String toString(){
        String result = "";
        result += (name + " | ");
        result += ("ID : " + id + " | ");
        result += ("Serving : " + servingSize + " | ");
        result += ("Calories per serving : " + calories + " kcal");
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(this.id);
        dest.writeString(this.servingSize);
        dest.writeDouble(this.calories);
        dest.writeString(this.description);
    }

    public FoodItem() {
    }

    protected FoodItem(Parcel in) {
        this.name = in.readString();
        this.id = in.readLong();
        this.servingSize = in.readString();
        this.calories = in.readDouble();
        this.description = in.readString();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel source) {
            return new FoodItem(source);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };
}
