package esipe.mobi.daos;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import esipe.mobi.Util.HibernateUtil;
import esipe.mobi.beans.Book;

public class BookDao {
	
	
	/* HTTP - GET */
	public static Book getBookByIsbn(int isbn){
		Book book = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		book = (Book)session.get(Book.class,isbn);
		if(book == null){
			throw new IllegalStateException();
		}
		session.close();
		return book;
	}
	
	/* HTTP - GET */
	@SuppressWarnings("unchecked")
	public static List<Book> getBookByAuthor(String author){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Book> list = (List<Book>)session.createCriteria(Book.class).add(Restrictions.like("author",author)).list();
		session.close();
		if(list.isEmpty()){
			throw new IllegalStateException();
		}
		return list;
	}
	
	
	/* HTTP - GET */
	@SuppressWarnings("unchecked")
	public static List<Book> getAllBooks(){		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Book> list = (List<Book>)session.createCriteria(Book.class).list();
		session.getTransaction().commit();
		session.close();
		if(list.isEmpty()){
			throw new IllegalStateException();
		}
		return list;
		
	}
	
	/* HTTP - POST */
	public static void save(Book b){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			session.save(b);
			session.getTransaction().commit();
		} catch(HibernateException e){
			throw new IllegalStateException();
		} finally {
			session.close();
		}
		
	}
	
	/* HTTP - PUT */
	public static void update(Book newBook, int isbn){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Book initialBook = (Book)session.get(Book.class, isbn);
		initialBook.updateBook(newBook);
		session.update(initialBook);
		session.getTransaction().commit();
		session.close();
		
	}
	
	/* HTTP - DELETE */
	public static void delete(int isbn){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Book b = (Book)session.get(Book.class,isbn);
		if( b == null){
			throw new IllegalStateException();
		}
		System.out.println(b);
		session.delete(b);		
		session.getTransaction().commit();
		session.close();
	}

}
