package com.warmup.resource;

import com.warmup.dao.BookDao;
import com.warmup.domain.Author;
import com.warmup.domain.Book;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@RequiredArgsConstructor
@Path("/books")
public class BookResource {


    private final BookDao bookDao;

    @GET
    public List<Book> list() {
        return bookDao.findAll();
    }

    @GET
    @Path("/{isbn}")
    public Book get(@PathParam("isbn") String isbn) {
        return bookDao.findById(isbn);
    }

    @POST
    public Book save(Book book) {
        return bookDao.save(book);
    }
}
