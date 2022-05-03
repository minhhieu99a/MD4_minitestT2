package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.Category;
import com.codegym.service.book.IBookService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;
@Autowired
private ICategoryService categoryService;
    @ModelAttribute("categories")
    public Iterable<Category> findAllCategories(){
        return categoryService.findAll();
    }
    @GetMapping("list")
    public ModelAndView getAllBookPage() {
        List<Book> books = (List<Book>) bookService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> getAllBook() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (!book.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.remove(id);
        return new ResponseEntity<>(book.get(), HttpStatus.NO_CONTENT);
    }
}
