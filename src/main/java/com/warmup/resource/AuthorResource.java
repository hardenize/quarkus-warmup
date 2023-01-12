package com.warmup.resource;

import com.warmup.dao.AuthorDao;
import com.warmup.dao.BookDao;
import com.warmup.domain.Author;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@RequiredArgsConstructor
@Path("/authors")
public class AuthorResource {

    private final AuthorDao authorDao;
    private final BookDao bookDao;

    @GET
    public Response list() {
        return Response.ok(authorDao.findAll()).build();
    }

    @GET
    @Path("/grouped")
    public Response listGrouped() {
        return Response.ok(authorDao.findAllGrouped()).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Integer id) {
        return Response.ok(authorDao.findById(id)).build();
    }

    @GET
    @Path("/query")
    public Response queryByFirstNameAndLastName(@QueryParam("firstName") String firstName,
                                                    @QueryParam("lastName") String lastName) {
        return Response.ok(authorDao.findByFirstNameAndLastName(firstName, lastName)).build();
    }

    @POST
    public Response save(Author author) {
        int id = authorDao.save(author);
        return Response.created(UriBuilder.fromPath("authors/{id}").build(id)).build();
    }

    @PUT
    public Response update(Author author) {
        if (!authorDao.exists(author.getPersonId())) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Author not found")
                    .build();
        }
        authorDao.update(author);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{isbn}/{id}")
    public Response deleteForBook(@PathParam("isbn") String isbn, @PathParam("id") Integer id) {
        if (!authorDao.exists(id)) {
            return Response
                    .status(Response.Status.NOT_FOUND).entity("Author not found")
                    .build();

        }
        if (!bookDao.exists(isbn)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Book not found")
                    .build();
        }
        authorDao.deleteForBookByAuthorId(isbn, id);
        return Response.noContent().build();
    }
}
