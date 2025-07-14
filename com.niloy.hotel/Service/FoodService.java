package com.niloy.hotel.service;

import com.niloy.hotel.db.DBConnection;
import com.niloy.hotel.model.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodService {

    private List<Food> foodList;

    public FoodService() {
        foodList = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM food ORDER BY id ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                foodList.add(new Food(id, name, price));
            }

            rs.close();
            ps.close();
            conn.close();

//            System.out.println("Food items loaded from DB: " + foodList.size()); //to check running or not

        } catch (Exception e) {
            System.out.println("Error fetching food list: " + e.getMessage());
        }
    }

    public List<Food> getAllFoods() {
        return foodList;
    }

    public Food getFoodById(int id, int quantity) {
        for (Food food : foodList) {
            if (food.getId() == id) {
                return new Food(id, food.getName(), quantity * food.getPrice());
            }
        }
        return null;
    }
}
