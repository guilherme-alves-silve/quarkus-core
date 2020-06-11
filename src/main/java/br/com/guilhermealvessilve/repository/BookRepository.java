package br.com.guilhermealvessilve.repository;

import br.com.guilhermealvessilve.data.Book;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class BookRepository {

    private static final ConcurrentMap<Long, Book> BOOKS;
    private static final AtomicLong ID = new AtomicLong();

    static {
        BOOKS = new ConcurrentHashMap<>();
        final var id = ID.incrementAndGet();
        BOOKS.put(id, new Book(id, "The Freelancer's bible", "John Doe", 300));
    }

    public Collection<Book> getAll() {
        return BOOKS.values();
    }

    public Book add(Book book) {
        book.setId(ID.incrementAndGet());
        BOOKS.put(book.getId(), book);
        return book;
    }

    public Book update(Long id, Book book) {
        BOOKS.remove(id);
        BOOKS.put(id, book);
        return book;
    }

    public Book delete(Long id) {
        return BOOKS.remove(id);
    }

    public Optional<Book> get(Long id) {
        return Optional.ofNullable(BOOKS.get(id));
    }
}
