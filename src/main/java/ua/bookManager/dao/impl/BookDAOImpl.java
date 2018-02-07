package ua.bookManager.dao.impl;

import ua.bookManager.dao.BookDAO;
import ua.bookManager.entity.Book;
import ua.bookManager.util.HibernateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    public BookDAOImpl() {
        System.out.println("BookDAOImpl");
    }

    @Autowired
    private HibernateUtil hibernateUtil;


    @Override
    public int createBook(Book book) {
        book.setReadAlready(false);
        return (int) hibernateUtil.create(book);
    }

    @Override
    public Book updateBook(Book book) {
        Book resBook = hibernateUtil.update(book);
        resBook.setReadAlready(false);
        return resBook;
    }

    @Override
    public Book read(int id) {
        Book book = hibernateUtil.fetchById(id, Book.class);
        if(book.getReadAlready() == false)
        book.setReadAlready(true);
        return book;
    }

    @Override
    public void deleteBook(int id) {
        Book book = new Book();
        book.setId(id);
        hibernateUtil.delete(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return hibernateUtil.fetchAll(Book.class);
    }

    @Override
    public Book getBook(int id) {
        return hibernateUtil.fetchById(id, Book.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getAllBooks(String bookName) {
        String query = "SELECT t.* FROM book t WHERE t.title like '%"+ bookName +"%'";
        List<Object[]> bookObjects = hibernateUtil.fetchAll(query);
        List<Book> books = new ArrayList<Book>();
        for(Object[] bookObject: bookObjects) {
            Book book = new Book();
            int id = (int) bookObject[0];
            String title = (String) bookObject[1];
            String description = (String) bookObject[2];
            String author = (String) bookObject[3];
            String isbn = (String) bookObject[4];
            int printYear = (int) bookObject[5];
            boolean readAlready = (boolean) bookObject[6];
            book.setId(id);
            book.setTitle(title);
            book.setDescription(description);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setPrintYear(printYear);
            book.setReadAlready(readAlready);
            books.add(book);
        }
        System.out.println(books);
        return books;
    }

    @SuppressWarnings("unchecked")
    public List<Book> getAllBooksByYear(int year) {
        String query = "SELECT t.* FROM book t WHERE t.printYear like '%"+ year +"%'";
        List<Object[]> bookObjects = hibernateUtil.fetchAll(query);
        List<Book> books = new ArrayList<Book>();
        for(Object[] bookObject: bookObjects) {
            Book book = new Book();
            int id = (int) bookObject[0];
            String title = (String) bookObject[1];
            String description = (String) bookObject[2];
            String author = (String) bookObject[3];
            String isbn = (String) bookObject[4];
            int printYear = (int) bookObject[5];
            boolean readAlready = (boolean) bookObject[6];
            book.setId(id);
            book.setTitle(title);
            book.setDescription(description);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setPrintYear(printYear);
            book.setReadAlready(readAlready);
            books.add(book);
        }
        System.out.println(books);
        return books;
    }

}
