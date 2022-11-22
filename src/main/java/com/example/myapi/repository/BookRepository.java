package com.example.myapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myapi.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}