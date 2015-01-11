package esipe.mobi.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;

@Entity
@Table(name="book")
@XmlRootElement
public class Book {

	@Id
	@Column(name="isbn",nullable=false)
	private int isbn;
	
	@Column(name="author")
	private String author;
	
	@Column(name="title")
	private String title;
	
	@Column(name="style")
	private String style;

	public Book() {
	}

	public Book(int isbn, String author, String title, String style) {			
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.style = style;
	}

	public int getIsbn() {
		return isbn;
	}

	@XmlElement
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getStyle() {
		return style;
	}

	@XmlElement
	public void setStyle(String style) {
		this.style = style;
	}

	public void updateBook(Book b) {
		if (b == null) {
			throw new NullPointerException();
		}
		this.isbn = b.getIsbn();
		this.title = b.getTitle();
		this.author = b.getAuthor();
		this.style = b.getStyle();
	}

	public static Book copy(Book b) {
		if (b == null) {
			throw new NullPointerException();
		}
		return new Book(b.getIsbn(), b.getAuthor(), b.getTitle(), b.getStyle());
	}
	
	@Override
	public String toString(){
		return "{"+"isbn : "+ this.isbn + "author : " + this.author + " title : " + this.title + " style : "+this.style + "}";
	}
	

}
