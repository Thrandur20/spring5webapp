package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("Jim Jones");
        publisher.setAddressLine1("77 Eluna Apartments, 4 Wapping Lane");
        publisher.setState("London");
        publisher.setCity("London");
        publisher.setZip("E1W 2RG");

        publisherRepository.save(publisher);

        System.out.println("Started Bootstrap");
        System.out.println("Number of publishers: " + publisherRepository.count());

        Author tudor = new Author("Tudor", "Dodonete");
        Book ddd = new Book("Domain Driven Design", "123123");
        tudor.getBooks().add(ddd);
        ddd.getAuthors().add(tudor);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(tudor);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "123456789");
        noEJB.setPublisher(publisher);

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher number of books: " + publisher.getBooks().size());
    }
}
