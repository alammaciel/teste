package library;

import java.util.Date;


public class Book {

	private String title;

	private String author;

	private String publisher;

	private String isbn;

	private Date publicationDate;

	private int numberOfCopies;

	private int availableNumberOfCopies;

	private String category;

	private int ranking;

	public Book() {
		this("", "", "", "", null, 0, 0, "");

	}


	public Book(String title, String author, String publisher, String isbn,
			Date publicationDate, int numberOfCopies,
			int availableNumberOfCopies, String category) {

		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.publicationDate = publicationDate;
		this.numberOfCopies = numberOfCopies;
		this.availableNumberOfCopies = availableNumberOfCopies;
		this.category = category;
		this.ranking = 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public int getAvailableNumberOfCopies() {
		return availableNumberOfCopies;
	}

	public void setAvailableNumberOfCopies(int availableNumberOfCopies) {
		this.availableNumberOfCopies = availableNumberOfCopies;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getRanking() {
		return ranking;
	}

	public String toString() {
		return title + "," + author + "," + publisher + "," + isbn + ","
				+ IOManager.formatDate(publicationDate) + "," + numberOfCopies
				+ "," + availableNumberOfCopies + "," + category;
	}
}