package com.codegym.repository.book;

import com.codegym.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBookRepository extends PagingAndSortingRepository<Book,Long> {
}
