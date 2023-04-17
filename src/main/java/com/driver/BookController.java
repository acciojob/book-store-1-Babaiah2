package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;


    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get_book_by_id/{id}")
    public Book getBookById(@PathVariable("id") int Id){
        for(Book b:bookList){
            if(b.getId()==Id)
                return b;
        }
        return null;

    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public void deleteBookById(@PathVariable("id") int id) {
        for (Book b : bookList) {
            if (b.getId() == id) {
                bookList.remove(id);
                break;
            }
        }
    }


    // get request /get-all-books
    // getAllBooks()
        @GetMapping("/get-all-books")
        public List<Book> getAllBooks(){
            return bookList;
        }

    // delete request /delete-all-books
    // deleteAllBooks()
        @DeleteMapping("/delete-all-books")
        public void deleteAllBooks(){
            bookList.clear();
        }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
        @GetMapping("/get-books-by-author")
        public List<Book> getBooksByAuthor(@RequestParam("author") String name){
        List<Book> list = new ArrayList<>();
         for(Book b:bookList){
             if(b.getAuthor().equals(name)){
                 list.add(b);
             }
         }
         return list;
        }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public List<Book> getBooksByGenre(@RequestParam("genre") String genreName){
        List<Book> list = new ArrayList<>();

        for(Book b:bookList){
            if(b.getGenre().equals(genreName)){
                list.add(b);
            }
        }
        return list;
    }
}
