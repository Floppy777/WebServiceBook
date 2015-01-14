package esipe.mobi.daos;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import esipe.mobi.Util.HibernateUtil;
import esipe.mobi.beans.Book;

public class BookDao {
	
	
	/* HTTP - GET */
	public static Book getBookByIsbn(int isbn){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Book book = null;
		try {
			book = (Book)session.get(Book.class,isbn);
			if(book == null){
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		}finally {
			if(session != null){
				session.close();
			}
		}
		return book;
	}
	
	/* HTTP - GET */
	@SuppressWarnings("unchecked")
	public static List<Book> getBookByAuthor(String author) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
		try {
			list = (List<Book>)session.createCriteria(Book.class).add(Restrictions.like("author",author)).list();
			if(list.isEmpty()){
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} finally {
			if(session != null){
				session.close();
			}
		}
		return list;
	}
	
	
	/* HTTP - GET */
	@SuppressWarnings("unchecked")
	public static List<Book> getAllBooks() {		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = null;
		try {
			session.beginTransaction();
			list = (List<Book>)session.createCriteria(Book.class).list();
			session.getTransaction().commit();
			if(list.isEmpty()){
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		}finally {
			if(session != null){
				session.close();
			}
		}
		return list;
	}
	
	/* HTTP - POST */
	public static void save(Book b) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(b);
			session.getTransaction().commit();
		} catch(HibernateException e){
			throw new WebApplicationException(Status.NO_CONTENT);
		} finally {
			session.close();
		}
		
	}
	
	/* HTTP - PUT */
	public static void update(Book newBook, int isbn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Book initialBook = (Book)session.get(Book.class, isbn);
			if(initialBook == null){
				throw new WebApplicationException(Status.NOT_FOUND);
			}
			if(newBook.equals(initialBook)){
				throw new WebApplicationException(Status.NOT_MODIFIED);
			}
			initialBook.updateBook(newBook);
			session.update(initialBook);
			session.getTransaction().commit();
		} finally {
			if(session != null){
				session.close();
			}
		}		
	}
	
	/* HTTP - DELETE */
	public static void delete(int isbn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Book b = (Book)session.get(Book.class,isbn);
			if( b == null){
				throw new WebApplicationException(Status.NOT_FOUND);
			}
			System.out.println(b);
			session.delete(b);		
			session.getTransaction().commit();
		} finally {
			if(session != null){
				session.close();
			}
		}
	
	}

}
