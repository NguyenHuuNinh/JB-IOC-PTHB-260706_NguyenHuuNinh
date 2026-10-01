package entity;

import java.sql.Timestamp;

public class TicketBooking {
    private int bookingID;
    private String movieTitle;
    private String customerName;
    private Timestamp showTime;
    private Timestamp bookingDate;
    private int seatQuantity;
    private String status;

    public TicketBooking() {}

    public TicketBooking(int bookingID, String movieTitle, String customerName, Timestamp showTime, Timestamp bookingDate, int seatQuantity, String status) {
        this.bookingID = bookingID;
        this.movieTitle = movieTitle;
        this.customerName = customerName;
        this.showTime = showTime;
        this.bookingDate = bookingDate;
        this.seatQuantity = seatQuantity;
        this.status = status;
    }
    public int getBookingID() {return bookingID;}
    public void setBookingID(int bookingID) {this.bookingID = bookingID;}
    public String getMovieTitle() {return movieTitle;}
    public void setMovieTitle(String movieTitle) {this.movieTitle = movieTitle;}
    public String getCustomerName() {return customerName;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}
    public Timestamp getShowTime(){return showTime;}
    public void setShowTime(Timestamp showTime){this.showTime = showTime;}
    public int getSeatQuantity(){return seatQuantity;}
    public void setSeatQuantity(int seatQuantity){this.seatQuantity = seatQuantity;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}

@Override
public String toString(){
    return String.format("ID: %-5d | Phim: %-20s | Khach: %-20s | Suat: %-20s | Ngay dat: %-20s | So ghe: %-5s | Trang thai %s, bookingID, movieTitle, customerName, showTime, bookingDate, seatQuantity, status");
}

}
