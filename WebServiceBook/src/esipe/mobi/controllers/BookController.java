package esipe.mobi.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import esipe.mobi.beans.Book;
import esipe.mobi.daos.BookDao;

@Path("/book")
public class BookController {
	
	public BookController(){
	}
	
	@GET
	@Path("test")
	public String test(@Context HttpServletRequest info){
		System.out.println(info);
		return "Hello World";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks() {
		List<Book> listBook ;
		try{
			listBook = BookDao.getAllBooks();	
		}catch(IllegalStateException e){
			return Response.status(Status.NOT_FOUND).build();
		}
		System.out.println("GetAllBook from Mysql Database : "+ listBook.size());		
		return Response.status(Status.OK).entity(listBook).build();
	}

	@GET
	@Path("isbn/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookByID(@PathParam("isbn") int isbn) {
		Book book;
		try {
			book = BookDao.getBookByIsbn(isbn);
		} catch(IllegalStateException e){
			return Response.status(Status.NOT_FOUND).build();
		}		
		System.out.println("Get book from Mysql Database with isbn = " + isbn);		
		return Response.status(Status.OK).entity(book).build();
	}

	@GET
	@Path("author/{author}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookByAuthor(@PathParam("author") String author) {
		List<Book> books;
		try {
			books = BookDao.getBookByAuthor(author);
		}catch(IllegalStateException e){
			return Response.status(Status.NOT_FOUND).build();
		}		
		System.out.println("Get books from Mysql Database with author = "+author);
		return Response.status(Status.OK).entity(books).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postBookByElements(Book b) {
		try {
			BookDao.save(b);
		} catch(IllegalStateException e){
			return Response.status(Status.NO_CONTENT).build();
		}
		System.out.println("Book save in Mysql Database");
		return Response.status(Status.CREATED).build();
	}
	
	@PUT
	@Path("isbn/{isbn}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBookByIsbn(Book b, @PathParam("isbn") int isbn){
		try {
			BookDao.update(b, isbn);
		} catch (IllegalStateException e ){
			return Response.status(Status.NOT_FOUND).build();
		}
		System.out.println("Book with isbn = " + isbn + " update in Mysql Database");
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path("isbn/{isbn}")
	public Response deleteBokoById(@PathParam("isbn") int isbn){
		try {
			BookDao.delete(isbn);
		}
		catch(IllegalStateException e){
			return Response.status(Status.NOT_FOUND).build();
		}
		System.out.println("Book with isbn = " + isbn + " was delete in Mysql Database");
		return Response.status(Status.OK).build();

	}

}
