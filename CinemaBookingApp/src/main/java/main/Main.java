package main;

import dao.TicketBookingDAO;
import entity.TicketBooking;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketBookingDAO dao = new TicketBookingDAO();
        int choice = 0;

        do {
            System.out.println("CINEMA TICKET BOOKING");
            System.out.println("1. Danh sach tat ca cac phieu dat ve");
            System.out.println("2. Them moi phieu dat ve");
            System.out.println("3. Cap nhat thong tin phieu dat ve");
            System.out.println("4. Xoa phieu dat ve");
            System.out.println("5. Tim kiem phieu dat ve theo ten khach hang");
            System.out.println("6. Tim kiem phieu dat ve theo ten phim");
            System.out.println("7. Thoat");
            System.out.println("Chon chuc nangg tu 1 den 7");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le");
                continue;
            }
            switch (choice) {
                case 1:
                    List<TicketBooking> allBookings = dao.getAllBookings();
                    if (allBookings.isEmpty()) {
                        System.out.println("Chua co du lieu phieu dat ve");
                    } else {
                        allBookings.forEach(System.out::println);
                    }
                    break;
                case 2:
                    TicketBooking newBooking = inputBookingInfo(scanner);
                    if (newBooking != null) dao.addBooking(newBooking);
                    break;
                case 3:
                    System.out.println("Nhap ID phieu dat ve can cap nhat");
                    try {
                        int updateID = Integer.parseInt(scanner.nextLine());
                        TicketBooking updateBooking = inputBookingInfo(scanner);
                        if (updateBooking != null) {
                            updateBooking.setBookingID(updateID);
                            dao.updateBooking(updateBooking);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID phai la so");
                    }
                    break;
                case 4:
                    System.out.println("Nhap ID phieu dat ve can xoa");
                    try {
                        int deleteID = Integer.parseInt(scanner.nextLine());
                        System.out.println("ban co chac muon xoa phieu ID" + deleteID + "khong? (Y/N): ");
                        String confirm = scanner.nextLine();
                        if (confirm.equals("Y")) {
                            dao.deleteBooking(deleteID);
                        } else {
                            System.out.println("Da huy xoa");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID phai la so");
                    }
                    break;
                case 5:
                    System.out.println("Nhap ten khach hang can tim (chinh xac)");
                    String cusName = scanner.nextLine();
                    List<TicketBooking> ListByMovie = dao.searchBookingsByMovie(cusName);
                    if (ListByCus.isEmpty()) System.out.println("Khong tim thay ket qua");
                    else listByCus.foreach(System.out::println);
                    break;
                case 6:
                    System.out.println("Nhap ten phim can tim (chinh xac)");
                    String movieSearch = scanner.nextLine();
                    List<TicketBooking> ListByMovie = dao.searchBookingsByMovie(movieSearch);
                    if (ListByMovie.isEmpty()) System.out.println("Khong tim thay ket qua");
                    else listByMovie.forEach(System.out::println);
                    break;
                case 7:
                    System.out.println("Da thoat");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Chon tu 1 den 7");
            }
        } while (choice != 7);
        scanner.close();
    }
}



