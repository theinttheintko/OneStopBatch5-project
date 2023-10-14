package com.example.bookshop;

import com.example.bookshop.dao.BookDao;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class BookShopApplication {
    private final BookDao bookDao;

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

    @Bean
    @Transactional
    //@Profile("test")
    public ApplicationRunner runner() {
        return r -> {
            List.of(
                            new Book("The Pianist: The Extraordinary Story of One Man's Survival in Warsaw, 1939–45", 35.5, "Władysław Szpilman", "https://source.unsplash.com/366x200/?sweet", LocalDate.of(1946, 1, 1), Category.HISTORICAL),
                            new Book("The Book Thief", 40.5, "Markus Zusak", "https://source.unsplash.com/366x200/?books", LocalDate.of(2005, 3, 21), Category.HISTORICAL),
                            new Book("Little Women", 40.5, "Louisa May Alcott", "https://source.unsplash.com/366x200/?girl", LocalDate.of(1968, 3, 21), Category.NOVEL),
                            new Book("Coraline", 45.5, "Neil Gaiman", "https://source.unsplash.com/366x200/?cat", LocalDate.of(2002, 7, 2), Category.HORROR),
                            new Book("Pride and Prejudice", 35.5, "Jane Austen", "https://source.unsplash.com/366x200/?love", LocalDate.of(1813, 1, 28), Category.ROMANCE),
                            new Book("The Great Gatsby", 35.5, "F. Scott Fitzgerald", "https://source.unsplash.com/366x200/?ocean", LocalDate.of(1925, 4, 10), Category.FICTION),
                            new Book("Dune", 35.5, "Frank Herbert", "https://source.unsplash.com/366x200/?dog", LocalDate.of(1965, 8, 10), Category.SCIENCE_FICTION)

                    )
                    .forEach(bookDao::save);
        };

    }
}
