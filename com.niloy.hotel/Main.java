package com.niloy.hotel;

import com.niloy.hotel.model.Food;
import com.niloy.hotel.model.RoomBooking;
import com.niloy.hotel.service.FoodService;
import com.niloy.hotel.service.RoomBookingService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FoodService foodService = new FoodService();
        int ch;

        do {
            System.out.println("===Hotel Management System===");
            System.out.println("1. Room Booking");
            System.out.println("2. Order Food");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("5. Enter option: ");

            ch=sc.nextInt();

            switch(ch) {
                case 1:
                    System.out.println("Room Booking Selected.");

                    System.out.println("Booked Rooms:");
                    for (RoomBooking room : RoomBookingService.getBookedRooms()) {
                        room.display();
                    }

                    System.out.println("Available Rooms:");
                    for (RoomBooking room : RoomBookingService.getAvailableRooms()) {
                        room.display();
                    }

                    System.out.print("Enter Room Number to Book: ");
                    int roomNumber = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.print("Enter Guest Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Contact: ");
                    String contact = sc.nextLine();

                    System.out.print("Enter Gender: ");
                    String gender = sc.nextLine();

                    boolean success = RoomBookingService.bookRoom(roomNumber, name, contact, gender);
                    if (success) {
                        System.out.println("✅ Room Booked Successfully!");
                    } else {
                        System.out.println("❌ Booking Failed! Room might be already booked.");
                    }

                    break;
                case 2:
                    System.out.println("Food Order Selected.");
                    System.out.println("\nFood Menu:");
                    List<Food> foodMenu = foodService.getAllFoods();

                    for (Food food : foodMenu) {
                        System.out.println(food.getId() + ". " + food.getName() + " - ₹" + food.getPrice());
                    }

                    System.out.print("Enter food ID to order: ");
                    int foodId = sc.nextInt();



                    boolean found = false;
                    for (Food food : foodMenu) {
                        if (food.getId() == foodId) {
                            System.out.println("You ordered: " + food.getName() + " (₹" + food.getPrice() + ")");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Invalid food ID");
                    }
                    break;
                case 3:
                    System.out.println("Checkout Selected.");

                    System.out.println("Enter Room NUmber to Checkout: ");
                    int roomNumber_forCheckout = sc.nextInt();

                    boolean success_forCheckout = RoomBookingService.checkoutRoom(roomNumber_forCheckout);
                    if(success_forCheckout) {
                        System.out.println("Check Out Successful");
                    }
                    else {
                        System.out.println("Checkout Failed. Room may already be vacent or doesn't exist");
                    }

                    break;
                case 4:
                    System.out.println("Thank You for visiting");
                    break;
                default:
                    System.out.println("Invalid Choice! Try Again.");
            }
        } while (ch != 4);
    }
}
