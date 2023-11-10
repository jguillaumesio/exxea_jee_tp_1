package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BookServlet extends HttpServlet {

    private BookRepository bookRepository;

    public void init() {
        this.bookRepository = new BookRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String idString = pathInfo.substring(1);
            try {
                int bookId = Integer.parseInt(idString);
                Book book = this.bookRepository.findById(bookId);

                if (book != null) {
                    request.setAttribute("book", book);
                    request.getRequestDispatcher("/views/detail.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                List<Book> books = this.bookRepository.findAll();
                request.setAttribute("books", books);
                request.getRequestDispatcher("/views/index.jsp").forward(request, response);
                return;
            }
        }
        List<Book> books = this.bookRepository.findAll();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            this.create(request);
        } else if ("remove".equals(action)) {
            this.remove(request);
        } else if ("edit".equals(action)) {
            this.edit(request);
        }
        response.sendRedirect(request.getContextPath() + "/book");
    }


    private void create(HttpServletRequest request) {
        Book book = new Book();
        book.setTitle(request.getParameter("title"));
        book.setISBN(request.getParameter("ISBN"));
        book.setTheme(request.getParameter("theme"));
        book.setPageNbr(Integer.parseInt(request.getParameter("page_nbr")));
        book.setFormat(request.getParameter("format"));
        book.setAuthorFirstname(request.getParameter("author_firstname"));
        book.setAuthorLastname(request.getParameter("author_lastname"));
        book.setEditor(request.getParameter("editor"));
        book.setEditionYear(Integer.parseInt(request.getParameter("edition_year")));
        book.setPrice(Double.parseDouble(request.getParameter("price")));
        book.setLanguage(request.getParameter("language"));
        this.bookRepository.create(book);
    }

    private void edit(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = new Book();
        book.setId(id);
        book.setTitle(request.getParameter("title"));
        book.setISBN(request.getParameter("ISBN"));
        book.setTheme(request.getParameter("theme"));
        book.setPageNbr(Integer.parseInt(request.getParameter("page_nbr")));
        book.setFormat(request.getParameter("format"));
        book.setAuthorFirstname(request.getParameter("author_firstname"));
        book.setAuthorLastname(request.getParameter("author_lastname"));
        book.setEditor(request.getParameter("editor"));
        book.setEditionYear(Integer.parseInt(request.getParameter("edition_year")));
        book.setPrice(Double.parseDouble(request.getParameter("price")));
        book.setLanguage(request.getParameter("language"));
        this.bookRepository.update(book);
    }

    private void remove(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean result = this.bookRepository.delete(id);
    }
}
