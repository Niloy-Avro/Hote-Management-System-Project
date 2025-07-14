package com.niloy.hotel.service;

import com.niloy.hotel.db.DBConnection;
import com.niloy.hotel.model.RoomBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RoomBookingService {

    public ArrayList<RoomBooking> getBookedRooms() {
        ArrayList<RoomBooking> bookedRooms = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM room WHERE is_booked = TRUE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bookedRooms.add(extractRoomFromResultSet(rs));
            }

        } catch (Exception e) {
            System.out.println("Error fetching booked rooms: " + e.getMessage());
        }
        return bookedRooms;
    }

    public ArrayList<RoomBooking> getAvailableRooms() {
        ArrayList<RoomBooking> availableRooms = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM room WHERE is_booked = FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                availableRooms.add(extractRoomFromResultSet(rs));
            }

        } catch (Exception e) {
            System.out.println("Error fetching available rooms: " + e.getMessage());
        }
        return availableRooms;
    }

    private RoomBooking extractRoomFromResultSet(ResultSet rs) throws Exception {
        return new RoomBooking(
                rs.getInt("id"),
                rs.getInt("room_number"),
                rs.getString("type"),
                rs.getBoolean("is_booked"),
                rs.getString("guest_name"),
                rs.getString("guest_contact"),
                rs.getString("guest_gender")
        );
    }

    public boolean bookRoom(int roomNumber, String guestName, String contact, String gender) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE room SET is_booked = TRUE, guest_name = ?, guest_contact = ?, guest_gender = ? WHERE room_number = ? AND is_booked = FALSE";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, guestName);
            ps.setString(2, contact);
            ps.setString(3, gender);
            ps.setInt(4, roomNumber);

            int updated = ps.executeUpdate();
            return updated > 0; // true if update succeeded

        } catch (Exception e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
        return false;
    }

}
