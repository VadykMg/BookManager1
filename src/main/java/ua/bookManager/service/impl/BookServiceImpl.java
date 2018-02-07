package ua.bookManager.service.impl;

import ua.bookManager.dao.BookDAO;
import ua.bookManager.entity.Book;
import ua.bookManager.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    public BookServiceImpl() {
        System.out.println("BookServiceImpl()");
    }

    @Autowired
    private BookDAO bookDAO;

    @Override
    public int createBook(Book book) {
        return bookDAO.createBook(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public Book getBook(int id) {
        return bookDAO.getBook(id);
    }

    @Override
    public List<Book> getAllBooks(String bookName) {
        return bookDAO.getAllBooks(bookName);
    }

    public List<Book> getAllBooksByYear(int year) {
        return bookDAO.getAllBooksByYear(year);
    }


    @Override
    public Book read(int id){
        return bookDAO.read(id);
    }
}
