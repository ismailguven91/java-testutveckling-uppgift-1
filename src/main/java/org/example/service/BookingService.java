package org.example.service;

import org.example.BookingRequest;
import org.example.model.Book;

import java.util.ArrayList;

public class BookingService {

    private final ArrayList<Book> books;
    private PayService payService;

    public BookingService(PayService payService) {
        this.payService = payService;
        books = new ArrayList<>();
        books.add(new Book(1, "Bamse", "Action", "Rune Andréasson", "2005-06-15", 50));
        books.add(new Book(2, "Mamma Mu", "Comedy", "Jujja Wieslander", "2016-01-23", 100));
        books.add(new Book(3, "Pippi LångStrump", "Comedy", "Astrid Lindgren", "1945-03-06", 150));
        //books = new Books();
    }

    public boolean loanBook(BookingRequest bookingRequest) {
        for (var item : books) {
            if (item.getName().equalsIgnoreCase(bookingRequest.getBookName()) && !item.isLoaned() && bookingRequest.getPay() >= item.getPrice() && bookingRequest.isPaid()) {
                item.setLoaned(true);
                payService.pay(bookingRequest.getPay());
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(BookingRequest bookingRequest) {
        for (var item : books) {
            if (item.getName().equalsIgnoreCase(bookingRequest.getBookName()) && item.isLoaned()) {
                if (bookingRequest.goodOrBad()) {
                    item.setRate(item.getRate() + 1);
                }
                item.setLoaned(false);
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getAvailableBooks() {
        ArrayList<String> availableBooks = new ArrayList<>();
        for (var item : books) {
            if (!item.isLoaned()) {
                availableBooks.add(item.getName());
            }
        }
        return availableBooks;
    }

    public ArrayList<String> searchBook(String userSearchInput) {
        ArrayList<String> availableBooks = new ArrayList<>();
        for (var item : books) {
            if (item.getName().equalsIgnoreCase(userSearchInput) || item.getAuthor().equalsIgnoreCase(userSearchInput) || item.getGenre().equalsIgnoreCase(userSearchInput) || item.getPublishDate().equalsIgnoreCase(userSearchInput)) {
                availableBooks.add(item.getName());
            }
        }
        return availableBooks;
    }

    public ArrayList<String> searchByRate(int rating) {
        ArrayList<String> availableBooks = new ArrayList<>();
        for (var item : books) {
            if (item.getRate() == rating) {
                availableBooks.add(item.getName());
            }
        }
        return availableBooks;
    }
}
