package library;

import java.text.ParseException;
import java.util.Date;



public class Borrowing {


	private Borrower[] borrowingArray;


	private int dataSize;


    private UserInterface userInterface;


    Borrowing(UserInterface userInterface) {
      this.userInterface = userInterface;
    }
    public UserInterface getUserInterface(){
        return userInterface;
    }

	public Borrower[] getBorrowingArray() {
		return borrowingArray;
	}


	public void setBorrowingArray(Borrower[] borrowingArray) {
		this.borrowingArray = borrowingArray;
	}


	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}

	public String borrow(String isbn, int id, Date due) throws ParseException {
		String message = "";

		int bookIndex = getUserInterface().getBooks().searchISBN(isbn);

		int memberID = getUserInterface().getMembers().searchID(id);

		if (bookIndex == -1)
			message = "ISBN invalido\n";// validating ISBN

		else if (memberID == -1)// validating ID
			message = "ID invalido\n";

		else if (getUserInterface().getBooks().getBooksArray()[bookIndex]
				.getAvailableNumberOfCopies() <= 0)
			message = "Sem copias disponiveis!\n";

		else if (getUnreturnedBooks(id) >= 3)
			message = "Nao pode emprestar mais que 3 livros!\n";

		else {
			Book borrowedBook = getUserInterface().getBooks().getBooksArray()[bookIndex];
			int index = dataSize;
			borrowingArray[index] = new Borrower(isbn, id, new Date(), due,
					null); // inserting borrowing data in the array
			dataSize++;
			borrowedBook.setAvailableNumberOfCopies(borrowedBook
					.getAvailableNumberOfCopies() - 1);
			getUserInterface().getBooks().incrementRanking(borrowedBook);
			message = "Dados atualizados.\n";
		}

		return message;

	}


	public int getUnreturnedBooks(int memberID) {
		int numberOfBooks = 0;
		int i = 0;
		while (borrowingArray[i] != null) {
			if ((borrowingArray[i].getId() == memberID)
					&& (borrowingArray[i].getDateReturned() == null))
				numberOfBooks++;
			i++;
		}
		return numberOfBooks;
	}


	private int searchBook(String isbn, int id) {
		int index = -1;
		int i = 0;
		boolean found = false;
		while ((!found) && (borrowingArray[i] != null))// linear search
		{
			if (borrowingArray[i].getIsbn().equalsIgnoreCase(isbn)
					&& (borrowingArray[i].getId() == id)
					&& (borrowingArray[i].getDateReturned() == null))

			{
				index = i;
				found = true;
			}
			i++;
		}

		return index;
	}

	public String returnBook(String isbn, int id, Date dateReturned) {

		int index = searchBook(isbn, id);
		String message = "";

		if (index == -1)
			message = "Dados invalidos!\n";

		else {

			borrowingArray[index].setDateReturned(dateReturned);
			int bookIndex = getUserInterface().getBooks().searchISBN(isbn);
			if (bookIndex != -1)// ISBN validation
			{
				Book returnedBook = getUserInterface().getBooks().getBooksArray()[bookIndex];
				returnedBook.setAvailableNumberOfCopies(returnedBook
						.getAvailableNumberOfCopies() + 1); // increasing
															// available copies
				message = "Dados atualizados.\n"; // available copies
			}
			else message = "Livro deletado!";

		}
		return message;

	}

	public String getOverDueBooks() {
		int counter = 0;
		String message = "Retorno:\n---------------\n";

		int i = 0;
		while (borrowingArray[i] != null)// linear search
		{
			if (borrowingArray[i].getDueDate().before(new Date())
					&& borrowingArray[i].getDateReturned() == null) {
				counter++;
				int bookIndex = getUserInterface().getBooks().searchISBN(
						borrowingArray[i].getIsbn());
				if (bookIndex == -1)
					message = "ISBN: "
							+ borrowingArray[i].getIsbn() + " deletado!\n";
				else
					message = message
							+ counter
							+ ")"
							+ getUserInterface().getBooks().getBooksArray()[bookIndex]
									.getTitle() + "\r\n";
			}

			i++;

		}

		if (counter == 0)
			message = "Sem retorno.\n";

		return message;

	}


	public String getPopularBooks() {

		String message = "Livros mais populares:\n-------------------\n";
		int length = getUserInterface().getBooks().getDataSize();
		int counter = 0;
		int i, j;

		for (i = length - 1; i > 0 && counter != 10; i--) {

			for (j = length - 1; j >= length - i; j--)
				// bubble sort
				if (getUserInterface().getBooks().getBooksArray()[j].getRanking() > getUserInterface()
						.getBooks().getBooksArray()[j - 1].getRanking()) {
					Book temp = new Book(); // swap
					temp = getUserInterface().getBooks().getBooksArray()[j];
					getUserInterface().getBooks().getBooksArray()[j] = getUserInterface()
							.getBooks().getBooksArray()[j - 1];
					getUserInterface().getBooks().getBooksArray()[j - 1] = temp;
				}
			counter++;
			message = message + counter + ")"
					+ getUserInterface().getBooks().getBooksArray()[j].getTitle()
					+ "\r\n";
		}
		if (counter == 0)
			message = "Lista nao disponivel.\n";
		return message;

	}

}
