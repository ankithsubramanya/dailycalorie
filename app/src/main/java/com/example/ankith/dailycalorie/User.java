package com.example.ankith.dailycalorie;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * Created by ankit on 01-05-2017.
 * represents a single user of the application
 */

public class User implements Parcelable{
    public void setId(String id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public User(){}
    String email;
    String id;
    double weight;

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public boolean isMale() {
        return isMale;
    }

    public HashMap<String, Integer> getFoodsConsumed() {
        return foodsConsumed;
    }

    public void setFoodsConsumed(HashMap<String, Integer> foodsConsumed) {
        this.foodsConsumed = foodsConsumed;
    }

    public void setListOfFoodItems(ArrayList<FoodItem> listOfFoodItems) {
        this.listOfFoodItems = listOfFoodItems;
    }

    public void setFriends(ArrayList<Long> friends) {
        this.friends = friends;
    }

    public Double getCaloriesLost() {
        return caloriesLost;
    }

    public void setCaloriesLost(Double caloriesLost) {
        this.caloriesLost = caloriesLost;
    }

    double height;
    int age;
    double targetWeight;
    boolean isMale;
    HashMap<String, Integer> foodsConsumed;// The Integer here represents servings of each
    ArrayList<FoodItem> listOfFoodItems;
    ArrayList<Long> friends;

    public ArrayList<FoodItem> getListOfFoodItems() {
        return listOfFoodItems;
    }
    Double caloriesLost = 0.0;

    private FoodItem getFoodItemWithId(String id){
        long foodId = Long.parseLong(id);
        FoodItem foodItem = new FoodItem();
        for (FoodItem item : listOfFoodItems){
            if (item.getId()==foodId){
                return item;
            }
        }
        return null;
    }
    private double getCaloriesFromId(String id){
        FoodItem item = getFoodItemWithId(id);
        return item.getCalories();
    }

    public double getTotalCaloriesGained(){
        double totalCalories = 0;
        if (foodsConsumed == null){
            foodsConsumed = new HashMap<>();
        }
        Set<String> foods = foodsConsumed.keySet();
        for (String food : foods){
            totalCalories += (foodsConsumed.get(food))*(getCaloriesFromId(food));
        }
        return totalCalories;
    }
    public double getTotalCaloriesLost(){
        if (caloriesLost == null){
            caloriesLost = 0.0;
        }
        return caloriesLost;
    }
    public double getCaloricDeficit(){
        return this.getTotalCaloriesLost() - this.getTotalCaloriesGained();
    }
    public boolean removeFoodById(long id, int servings){
        boolean doneOperation = false;
        Set<String> foods = foodsConsumed.keySet();
        HashSet<String> foodsToRemove = new HashSet<>();
        Iterator<String> iterator = foods.iterator();
        String food;
        while (iterator.hasNext()){
            food = iterator.next();
            if (Long.parseLong(food)==id){
                int operationsLeft = servings;
                while (operationsLeft > 0){
                    foodsConsumed.put(food,foodsConsumed.get(food) - 1);
                    operationsLeft--;
                }
                if (foodsConsumed.get(food)<=0){
                    foodsToRemove.add(food);
                }
                doneOperation = true;
            }
        }
        for (String foodString: foodsToRemove){
            foodsConsumed.remove(foodString);
        }
        return doneOperation;
    }
    public boolean removeFoodById(long id){
        if (foodsConsumed == null) return false;
        ArrayList<FoodItem> foodsToRemove = new ArrayList<FoodItem>();
        for (FoodItem item : listOfFoodItems){
            if (item.getId()==id){
                foodsToRemove.add(item);
            }
        }
        listOfFoodItems.removeAll(foodsToRemove);
        return removeFoodById(id, this.foodsConsumed.get(Long.toString(id)));
    }
    private void addFood(Long foodId, int servings){
        if (foodsConsumed == null){
            foodsConsumed = new HashMap<String, Integer>();
        }
        if (!foodsConsumed.containsKey(foodId.toString())){
            foodsConsumed.put(foodId.toString(),servings);
            return;
        }
        int currentServings = foodsConsumed.get(foodId);
        foodsConsumed.put(foodId.toString(), currentServings + servings);
    }
    public void addFood(FoodItem item, int servings){
        if (listOfFoodItems == null){
            listOfFoodItems = new ArrayList<>();
        }
        listOfFoodItems.add(item);
        addFood(item.getId(), servings);
    }
    public void addCaloriesLost(double lostFromExcersice){
        caloriesLost += lostFromExcersice;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setMale(boolean isMale){
        this.isMale = isMale;
    }
    public ArrayList<Long> getFriends() {
        return friends;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    // returns the remaining calories that the user can consume
    public double getCaloriesLeft(){
        double caloriesGoal;
        caloriesGoal = 2200;
        return caloriesGoal - getTotalCaloriesGained() + getTotalCaloriesLost();
    }
    public String getId(){
        return this.id;
    }
    public double getTargetWeight() {
        return targetWeight;
    }
    public void addFriend(User other) {
        if (friends == null){
            friends = new ArrayList<Long>();
        }
        if (friends.contains(other.getId())) return; // makes sure there is only one copy of the friend in the array list
        friends.add(Long.parseLong(other.getId()));
    }
    public void addFriend (long userId){
        if (friends == null){
            friends = new ArrayList<Long>();
        }
        if (friends.contains(userId)) return; // makes sure there is only one copy of the friend in the array list
        friends.add(userId);
    }
    public boolean removeFriend (User other){
        if (friends == null) return false;
        if (!friends.contains(other))return false;
        friends.remove(other);
        return true;
    }
    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }
    public String getEmail(){
        return this.email;
    }
    public String getDescription(){
        String descritption = "";
        descritption += "Calories consumed today : " + this.getTotalCaloriesGained() + " | Calories lost today : " + this.getTotalCaloriesLost();
        return descritption;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.id);
        dest.writeDouble(this.weight);
        dest.writeDouble(this.height);
        dest.writeInt(this.age);
        dest.writeDouble(this.targetWeight);
        dest.writeByte(this.isMale ? (byte) 1 : (byte) 0);
        dest.writeSerializable(this.foodsConsumed);
        dest.writeTypedList(this.listOfFoodItems);
        dest.writeList(this.friends);
        dest.writeValue(this.caloriesLost);
    }

    protected User(Parcel in) {
        this.email = in.readString();
        this.id = in.readString();
        this.weight = in.readDouble();
        this.height = in.readDouble();
        this.age = in.readInt();
        this.targetWeight = in.readDouble();
        this.isMale = in.readByte() != 0;
        this.foodsConsumed = (HashMap<String, Integer>) in.readSerializable();
        this.listOfFoodItems = in.createTypedArrayList(FoodItem.CREATOR);
        this.friends = new ArrayList<Long>();
        in.readList(this.friends, Long.class.getClassLoader());
        this.caloriesLost = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
