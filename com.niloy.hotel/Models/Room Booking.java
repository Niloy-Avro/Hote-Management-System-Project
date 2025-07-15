package com.niloy.hotel.model;

public class RoomBooking {
    private int id;
    private int roomNumber;
    private String type;
    private boolean isBooked;
    private String guestName;
    private String guestContact;
    private String guestGender;

    public RoomBooking (int id, int roomNumber, String type, boolean isBooked, String guestName, String guestContact, String guestGender) {
        this.id=id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.isBooked = isBooked;
        this.guestName = guestName;
        this.guestContact = guestContact;
        this.guestGender = guestGender;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }

    public String getGuestName() { return guestName; }
    public void setGuestName(String guestName) { this.guestName = guestName; }

    public String getGuestContact() { return guestContact; }
    public void setGuestContact(String guestContact) { this.guestContact = guestContact; }

    public String getGuestGender() { return guestGender; }
    public void setGuestGender(String guestGender) { this.guestGender = guestGender; }


    public void display() {
        System.out.println("Room " + roomNumber + " | " + type + " | " +
                (isBooked ? "Booked" : "Available") +
                (isBooked ? " | Guest: " + guestName : ""));
    }
}
