package ua.bookManager.dao;

import ua.bookManager.entity.Book;

import java.util.List;

public interface BookDAO {
    public int createBook(Book book);
    public Book updateBook(Book book);
    public Book read(int id);
    public void deleteBook(int id);
    public List<Book> getAllBooks();
    public Book getBook(int id);
    public List<Book> getAllBooks(String bookName);
    public List<Book> getAllBooksByYear(int year);
}
