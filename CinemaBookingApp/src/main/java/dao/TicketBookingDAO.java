package dao;

import entity.TicketBooking;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketBookingDAO {
    public List<TicketBooking> getAllBookings() {
        List<TicketBooking> list = new ArrayList<>();
        String sql = "SELECT * FROM fn_get_all_bookings()";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void addBooking(TicketBooking tb) {
        String sql = "{call sp_add_booking(?, ?, ?, ?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString (1, tb.getMovieTitle());
            cstmt.setTimestamp (2, tb.getCustomerName());
            cstmt.setTimestamp (3, tb.getShowTime());
            cstmt.setString (4, tb.getBookingID());
            cstmt.setInt (5, tb.getSeatQuantity());
            cstmt.setString (6, tb.getStatus());
            cstmt.execute();
            System.out.println("Them moi phieu dat ve thanh cong!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteBooking(int bookingID) {
        String sql = "{call sp_delete_booking(?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, bookingId);
            cstmt.execute();
            System.out.println("Xoa phieu dat ve thanh cong!");
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    public List<TicketBooking> getBookingByCustomerName(String customerName) {
    List<TicketBooking> list = new ArrayList<>();
    String sql = "SELECT * FROM fn_get_booking_by_customer(?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatment(sql)) {
        pstmt.setString(1, customerName);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            list.add(mapResultSetToEntity(rs));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
public List<TicketBooking> searchBookingsByMovie(String movieTitle) {
    List<TicketBooking> list = new ArrayList<>();
    String sql = "SELECT * FROM fn_search_booking_by_movie(?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatment(sql)) {
        pstmt setString(1, movieTitle);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            list.add(mapResultSetToEntity(rs));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

private TicketBooking mapResultSetToEntity(ResultSet rs) throws SQLException {
        return  new TicketBooking(
                rs.getInt("booking_id"),
                rs.getString("movie_title"),
                rs.getString("customer_name"),
                rs.getTimestamp("show_time"),
                rs.getTimestamp("booking_date"),
                rs.getInt("seat_quantity"),
                rs.getString("status")
        );
    }
}

