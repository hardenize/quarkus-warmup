package com.warmup.resource;

import com.oracle.svm.core.annotate.Delete;
import com.warmup.dao.BookDao;
import com.warmup.domain.Book;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@RequiredArgsConstructor
@Path("/books")
public class BookResource {

    private final BookDao bookDao;

    @GET
    public Response list() {
        return Response.ok(bookDao.findAll()).build();
    }

    @GET
    @Path("/{isbn}")
    public Response get(@PathParam("isbn") String isbn) {
        return Response.ok(bookDao.findById(isbn)).build();
    }

    @POST
    public Response save(Book book) {
        Book saved = bookDao.save(book);
        return Response.created(UriBuilder.fromPath("books/{id}").build(saved.getIsbn())).build();
    }

    @PUT
    public Response update(Book book) {
        if (!bookDao.exists(book.getIsbn())) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Book does not exist")
                    .build();
        }
        bookDao.update(book);
        return Response.noContent().build();
    }

    @Delete
    @Path("/{isbn}")
    public Response delete(@PathParam("isbn") String isbn) {
        if (!bookDao.exists(isbn)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Book does not exist")
                    .build();
        }
        bookDao.deleteById(isbn);
        return Response.noContent().build();
    }
}
