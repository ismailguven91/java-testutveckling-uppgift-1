package org.example.model;

public class Book {

    private int id;
    private int rate = 0;
    private String name;
    private String genre;
    private String author;
    private String publishDate;
    private boolean isLoaned;
    private int price;

    public Book(int id, String name, String genre, String author, String publish_date, int price) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.publishDate = publish_date;
        this.price = price;
        isLoaned = false;
    }

    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
    public String getName() {
        return name;
    }
    public String getGenre() {
        return genre;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublishDate() {
        return publishDate;
    }
    public boolean isLoaned() {
        return isLoaned;
    }
    public void setLoaned(boolean loaned) {
        isLoaned = loaned;
    }
    public int getPrice() {
        return price;
    }
}
