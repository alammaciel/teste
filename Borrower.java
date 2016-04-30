package library;

import java.util.Date;


public class Borrower {

	private String isbn;

	private int id;

	private Date dateIssued;

	private Date dueDate;

	private Date dateReturned;

	public Borrower() {
		this("", 0, null, null, null);
	}


	public Borrower(String isbn, int id, Date dateIssued, Date dueDate,
			Date dateReturned) {
		this.isbn = isbn;
		this.id = id;
		this.dateIssued = dateIssued;
		this.dueDate = dueDate;
		this.dateReturned = dateReturned;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDateIssued() {
		return dateIssued;
	}


	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Date getDateReturned() {
		return dateReturned;
	}


	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}


	public String toString() {
		return isbn + "," + id + "," + IOManager.formatDate(dateIssued) + ","
				+ IOManager.formatDate(dueDate) + ","
				+ IOManager.formatDate(dateReturned);

	}

}
