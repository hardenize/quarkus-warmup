package com.warmup.resource;

import com.warmup.dao.AuthorDao;
import com.warmup.domain.Author;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;

@RequiredArgsConstructor
@Path("/authors")
public class AuthorResource {

    private final AuthorDao authorDao;

    @GET
    public List<Author> list() {
        return authorDao.findAll();
    }

    @GET
    @Path("/{id}")
    public Author get(@PathParam("id") Integer id) {
        return authorDao.findById(id);
    }

    @GET
    @Path("/query")
    public List<Author> queryByFirstNameAndLastName(@QueryParam("firstName") String firstName,
                                                    @QueryParam("lastName") String lastName) {
        return authorDao.findByFirstNameAndLastName(firstName, lastName);
    }

    @POST
    public Author save(Author author) {
        int id = authorDao.save(author);
        return authorDao.findById(id);
    }
}
