package com.example.demo.repository;

import com.example.demo.model.Book;

import java.sql.ResultSet;

public class BookRepository extends AbstractRepository<Book>{

    public BookRepository(){
        super(Book.class);
    }

    public Book mapResultSetToEntity(ResultSet resultSet) {
        Book entity = null;
        try {
            entity = associatedClass.getDeclaredConstructor().newInstance();
            if (entity instanceof Book) {
                Book book = (Book) entity;
                book.setId(resultSet.getInt("id"));

                book.setTitle(resultSet.getString("title"));
                book.setISBN(resultSet.getString("ISBN"));
                book.setTheme(resultSet.getString("theme"));
                book.setPageNbr(resultSet.getInt("page_nbr"));
                book.setFormat(resultSet.getString("format"));
                book.setAuthorFirstname(resultSet.getString("author_firstname"));
                book.setAuthorLastname(resultSet.getString("author_lastname"));
                book.setEditor(resultSet.getString("editor"));

                book.setEditionYear(resultSet.getInt("edition_year"));
                book.setPrice(resultSet.getDouble("price"));
                book.setLanguage(resultSet.getString("language"));
            }
            // Add more if-else blocks for other entity types as needed

        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }
}
