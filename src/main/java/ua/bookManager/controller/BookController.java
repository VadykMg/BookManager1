package ua.bookManager.controller;

import org.springframework.beans.support.PagedListHolder;
import ua.bookManager.entity.Book;
import ua.bookManager.service.BookService;

import org.jboss.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
    private static final Logger logger = Logger.getLogger(BookController.class);
    private static final int MAX_ROWS_PER_PAGE = 10;

    public BookController() {
        System.out.println("BookController()");
    }

    @Autowired
    private BookService bookService;

    @RequestMapping("createBook")
    public ModelAndView createBook(@ModelAttribute Book book) {
        logger.info("Creating Book. Data: "+ book);
        return new ModelAndView("bookForm");
    }

    @RequestMapping("editBook")
    public ModelAndView editBook(@RequestParam int id, @ModelAttribute Book book) {
        logger.info("Updating the Book for the Id "+id);
        book = bookService.getBook(id);
        book.setAuthor(bookService.getBook(id).getAuthor());
        return new ModelAndView("bookForm", "bookObject", book);
    }

    @RequestMapping("readBook")
    public ModelAndView readBook(@RequestParam int id) {
        logger.info("read the Book for the Id "+id);
        bookService.read(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("saveBook")
    public ModelAndView saveBook(@ModelAttribute Book book) {
        logger.info("Saving the Book. Data : "+ book);
        if(book.getId() == 0){ // if book id is 0 then creating book other updating book
            bookService.createBook(book);
        } else {
            bookService.updateBook(book);
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("deleteBook")
    public ModelAndView deleteBook(@RequestParam int id)
    {
        logger.info("Deleting the Book. Id : " + id);
        bookService.deleteBook(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = {"getAllBooks"})
    public ModelAndView getAllBooks(){
        logger.info("Getting all Books.");
        List<Book> bookList = bookService.getAllBooks();
        return new ModelAndView("booksList", "bookList", bookList);
    }

    @RequestMapping("searchBook")
    public ModelAndView searchBook(@RequestParam("searchBook") String bookName){
        logger.info("Searching the Book. Book Names: "+bookName);
        List<Book> booksList = bookService.getAllBooks(bookName);
        return new ModelAndView("booksList", "bookList", booksList);
    }
    @RequestMapping("searchBookByYear")
    public ModelAndView searchBookByYear(@RequestParam("searchBookByYear") int year){
        logger.info("Searching the Book. Book Names: "+year);
        List<Book> booksList = bookService.getAllBooksByYear(year);
        return new ModelAndView("booksList", "bookList", booksList);
    }

    @RequestMapping(value="/")
    public ModelAndView listOfBooks(@RequestParam(required = false) Integer page) {
        logger.info("Getting all Books.");
        ModelAndView modelAndView = new ModelAndView("booksList");

        List<Book> books = bookService.getAllBooks();
        PagedListHolder<Book> pagedListHolder = new PagedListHolder<>(books);
        pagedListHolder.setPageSize(MAX_ROWS_PER_PAGE);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());

        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            page=1;
        }
        modelAndView.addObject("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            modelAndView.addObject("bookList", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            modelAndView.addObject("bookList", pagedListHolder.getPageList());
        }
        return modelAndView;
    }
}
