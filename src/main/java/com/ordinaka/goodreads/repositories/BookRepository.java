package com.ordinaka.goodreads.repositories;

import com.ordinaka.goodreads.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {

}
