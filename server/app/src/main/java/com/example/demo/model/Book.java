package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "theme")
    private String theme;

    @Column(name = "page_nbr")
    private int page_nbr;

    @Column(name = "format")
    private String format;

    @Column(name = "author_firstname")
    private String author_firstname;

    @Column(name = "author_lastname")
    private String author_lastname;

    @Column(name = "editor")
    private String editor;

    @Column(name = "edition_year")
    private int edition_year;

    @Column(name = "price")
    private double price;

    @Column(name = "language")
    private String language;

    public Book(String title, String ISBN, String theme, int page_nbr, String format, String author_firstname, String author_lastname, String editor, int edition_year, double price, String language) {
        this.title = title;
        this.ISBN = ISBN;
        this.theme = theme;
        this.page_nbr = page_nbr;
        this.format = format;
        this.author_firstname = author_firstname;
        this.author_lastname = author_lastname;
        this.editor = editor;
        this.edition_year = edition_year;
        this.price = price;
        this.language = language;
    }

    public Book() {}

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTheme() {
        return theme;
    }

    public int getPageNbr() {
        return page_nbr;
    }

    public String getFormat() {
        return format;
    }

    public String getAuthorFirstname() {
        return author_firstname;
    }

    public String getAuthorLastname() {
        return author_lastname;
    }

    public String getEditor() {
        return editor;
    }

    public int getEditionYear() {
        return edition_year;
    }

    public double getPrice() {
        return price;
    }

    public String getLanguage() {
        return language;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setPageNbr(int page_nbr) {
        this.page_nbr = page_nbr;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setAuthorFirstname(String author_firstname) {
        this.author_firstname = author_firstname;
    }

    public void setAuthorLastname(String author_lastname) {
        this.author_lastname = author_lastname;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setEditionYear(int edition_year) {
        this.edition_year = edition_year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
