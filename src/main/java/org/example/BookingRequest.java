package org.example;

public class BookingRequest {

    private String bookName;
    private int Pay;
    boolean paid;
    private boolean GiveRating;
    private String userID;


    public BookingRequest(String userID, String bookName, int pay, boolean paid) {
        this.userID = userID;
        this.bookName = bookName;
        Pay = pay;
        this.paid = paid;
    }

    public BookingRequest(String bookName, boolean GiveRating) {
        this.bookName = bookName;
        this.GiveRating = GiveRating;
    }

    public String getBookName() {
        return bookName;
    }

    public int getPay() {
        return Pay;
    }

    public boolean goodOrBad() {
        return GiveRating;
    }

    public boolean isPaid() {
        return paid;
    }
}
