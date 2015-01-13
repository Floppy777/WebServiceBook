package esipe.mobi.daos;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import esipe.mobi.Util.HibernateUtil;
import esipe.mobi.beans.Book;
import esipe.mobi.exceptions.RestNoContentException;
import esipe.mobi.exceptions.RestNotFoundException;
import esipe.mobi.exceptions.RestNotModifiedException;

public class BookDao {
	
	
	/* HTTP - GET */
	public static Book getBookByIsbn(int isbn) throws RestNotFoundException {
		Book book = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		book = (Book)session.get(Book.class,isbn);
		if(book == null){
			throw new RestNotFoundException();
		}
		session.close();
		return book;
	}
	
	/* HTTP - GET */
	@SuppressWarnings("unchecked")
	public static List<Book> getBookByAuthor(String author) throws RestNotFoundException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = (List<Book>)session.createCriteria(Book.class).add(Restrictions.like("author",author)).list();
		session.close();
		if(list.isEmpty()){
			throw new RestNotFoundException();
		}
		return list;
	}
	
	
	/* HTTP - GET */
	@SuppressWarnings("unchecked")
	public static List<Book> getAllBooks() throws RestNotFoundException{		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Book> list = (List<Book>)session.createCriteria(Book.class).list();
		session.getTransaction().commit();
		session.close();
		if(list.isEmpty()){
			throw new RestNotFoundException();
		}
		return list;
		
	}
	
	/* HTTP - POST */
	public static void save(Book b) throws RestNoContentException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(b);
			session.getTransaction().commit();
		} catch(HibernateException e){
			throw new RestNoContentException();
		} finally {
			session.close();
		}
		
	}
	
	/* HTTP - PUT */
	public static void update(Book newBook, int isbn) throws RestNotFoundException, RestNotModifiedException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Book initialBook = (Book)session.get(Book.class, isbn);
		if(initialBook == null){
			throw new RestNotFoundException();
		}
		if(newBook.equals(initialBook)){
			throw new RestNotModifiedException();
		}
		initialBook.updateBook(newBook);
		session.update(initialBook);
		session.getTransaction().commit();
		session.close();
		
	}
	
	/* HTTP - DELETE */
	public static void delete(int isbn) throws RestNotFoundException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Book b = (Book)session.get(Book.class,isbn);
		if( b == null){
			throw new RestNotFoundException();
		}
		System.out.println(b);
		session.delete(b);		
		session.getTransaction().commit();
		session.close();
	}

}
