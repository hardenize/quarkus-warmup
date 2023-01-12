package com.warmup.resource;

import com.oracle.svm.core.annotate.Delete;
import com.warmup.dao.ReviewDao;
import com.warmup.domain.Review;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@RequiredArgsConstructor
@Path("/reviews")
public class ReviewResource {

    private final ReviewDao reviewDao;

    @GET
    public Response list() {
        return Response.ok(reviewDao.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Integer id) {
        return Response.ok(reviewDao.findById(id)).build();
    }

    @POST
    public Response save(Review review) {
        int id = reviewDao.save(review);
        return Response.created(UriBuilder.fromPath("reviews/{id}").build(id)).build();
    }

    @PUT
    public Response update(Review review) {
        if (!reviewDao.exists(review.getReviewId())) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Review not found")
                    .build();
        }
        reviewDao.update(review);
        return Response.noContent().build();
    }

    @Delete
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        if (!reviewDao.exists(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Review not found")
                    .build();
        }
        reviewDao.deleteById(id);
        return Response.noContent().build();
    }

    @Delete
    @Path("/book/{isbn}")
    public Response deleteForBook(@PathParam("isbn") String isbn) {
        reviewDao.deleteForBook(isbn);
        return Response.noContent().build();
    }
}