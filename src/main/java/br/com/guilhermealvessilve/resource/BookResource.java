package br.com.guilhermealvessilve.resource;

import br.com.guilhermealvessilve.data.Book;
import br.com.guilhermealvessilve.repository.BookRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/book")
public class BookResource {

    @Inject
    BookRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        return Response.ok(repository.getAll())
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") Long id) {

        final var optBook = repository.get(id);
        if (optBook.isPresent()) {
            return Response.ok(repository.get(id))
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(@Valid Book book) throws URISyntaxException {
        repository.add(book);
        return Response.created(new URI("/book/" + book.getId()))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(
            @PathParam("id") Long id,
            @Valid Book book
    ) {
        return Response.ok(repository.update(id, book))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") Long id) {
        return Response.ok(repository.delete(id))
                .build();
    }
}
