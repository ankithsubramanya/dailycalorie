package com.example.ankith.dailycalorie;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ankit on 02-05-2017.
 */

public class DataScraper {
    /**
     * takes String data that is returned from fat secret api and converts it into a FoodItem object
     * @param foodName
     * @param foodId
     * @param description
     * @return
     */
    public static FoodItem descriptionToFoodItem(String foodName, long foodId, String description){
        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodName);
        foodItem.setId(foodId);
        foodItem.setDescription(description);
        String [] substrings = description.split(" ");
        ArrayList<String> listOfSubstrings= new ArrayList<String>(Arrays.asList(substrings));
        int startIndexOfServing = listOfSubstrings.indexOf("Per");
        int endIndexOfServing = listOfSubstrings.indexOf("-");
        String serving = "";
        serving += listOfSubstrings.get(startIndexOfServing + 1);
        serving += " ";
        serving += listOfSubstrings.get(endIndexOfServing - 1);
        serving.trim();
        foodItem.setServingSize(serving);
        int startIndexOfCal = listOfSubstrings.indexOf("Calories:");
        String intermediate = listOfSubstrings.get(startIndexOfCal  + 1);
        int endIndex = intermediate.indexOf("k");
        intermediate = intermediate.substring(0, endIndex);
        double calories = Double.parseDouble(intermediate);
        foodItem.setCalories(calories);
        return foodItem;
    }
}
