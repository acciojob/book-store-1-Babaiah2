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
    public ResponseEntity<Book> getBookById(@PathVariable("id") String Id){

        int newId = Integer.parseInt(Id);
        for(Book b:bookList){
            if(b.getId()==newId)
                return new ResponseEntity(b,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null,HttpStatus.ACCEPTED);

    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") String id) {
        int newId = Integer.parseInt(id);
        for (Book b : bookList) {
            if (b.getId() == newId) {
                bookList.remove(newId);
                break;
                // return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);

            }
        }
            return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);

    }


    // get request /get-all-books
    // getAllBooks()
        @GetMapping("/get-all-books")
        public ResponseEntity<List<Book>> getAllBooks(){
            return new ResponseEntity<>(bookList,HttpStatus.ACCEPTED);
        }

    // delete request /delete-all-books
    // deleteAllBooks()
        @DeleteMapping("/delete-all-books")
        public ResponseEntity<String> deleteAllBooks(){
            bookList.clear();
            return new ResponseEntity<>("deleted",HttpStatus.ACCEPTED);
        }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
        @GetMapping("/get-books-by-author")
        public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author") String name){
        List<Book> list = new ArrayList<>();
         for(Book b:bookList){
             if(b.getAuthor().equals(name)){
                 list.add(b);
             }
         }
         return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
        }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre") String genreName){
        List<Book> list = new ArrayList<>();

        for(Book b:bookList){
            if(b.getGenre().equals(genreName)){
                list.add(b);
            }
        }
        return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
    }
}
