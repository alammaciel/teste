package library;


public class Books {


	private Book[] booksArray;

	private int dataSize;

    private UserInterface userInterface;



        public UserInterface getUserInterface(){
        return userInterface;
    }
        
    Books(UserInterface userInterface) {
       this.userInterface = userInterface;
    }


	public Book[] getBooksArray() {
		return booksArray;
	}


	public void setBooksArray(Book[] booksArray) {
		this.booksArray = booksArray;
	}


	public int getDataSize() {
		return dataSize;
	}


	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}


	public String insertBook(Book aBook) {
		int index = dataSize;
		booksArray[index] = aBook; // inserting the book in the empty location
		dataSize++;
		return "\nLivro Inserido no sistema com sucesso!.\n";
	}


	public String searchBook(String title, String author, String category,
			String isbn) {

		if (title.isEmpty() && author.isEmpty() && category.isEmpty()
				&& isbn.isEmpty()) // checking for empty input from the user
			return "Sem resultados!!\n";

		String results = "Resultados:\n--------\n";
		int foundBooks = 0;
		int i = 0;
		title = title.trim().toLowerCase();
		author = author.trim().toLowerCase();
		category = category.trim().toLowerCase();
		isbn = isbn.trim().toLowerCase();
		while (booksArray[i] != null) // linear search
		{

			if (booksArray[i].getTitle().toLowerCase().contains(title)
					&& booksArray[i].getAuthor().toLowerCase().contains(author)
					&& booksArray[i].getCategory().toLowerCase().contains(
							category)
					&& booksArray[i].getIsbn().toLowerCase().contains(isbn)) {
				foundBooks++;
				results = results + foundBooks + ")" + booksArray[i].getTitle()
						+ "\r\n";
			}
			i++;
		}
		if (foundBooks == 0)
			results = "Sem resultados!!\n";
		return results;
	}


	public int searchISBN(String isbn) {

		if (isbn.isEmpty())
			return -1;
		int index = -1;
		int i = 0;
		boolean found = false;
		while ((!found) && (booksArray[i] != null)) // linear search
		{
			if (booksArray[i].getIsbn().equalsIgnoreCase(isbn)) {
				index = i;
				found = true;

			}
			i++;

		}
		return index;

	}


	public String addCopy(String isbn, int n) {
		String message = "";
		int index = searchISBN(isbn);
		if (index == -1)
			message = "ISBN inválido!\n"; // ISBN validation
		else {
			booksArray[index].setNumberOfCopies(booksArray[index]
					.getNumberOfCopies()
					+ n);
			booksArray[index].setAvailableNumberOfCopies(booksArray[index]
					.getAvailableNumberOfCopies()
					+ n);
			message = "Numero de copias atualizado.\n";
		}
		return message;

	}


	public String deleteBook(String isbn) {
		String message;
		int index = searchISBN(isbn);
		if (index == -1)
			message = "Invalid ISBN\n"; // ISBN Validation
		else {
			System.arraycopy(booksArray, index + 1, booksArray, index,
					booksArray.length - index - 1);
			dataSize--;
			message = "Livro deletado.\n";
		}
		return message;

	}


	public void incrementRanking(Book aBook) {
		int newRanking = aBook.getRanking() + 1;
		aBook.setRanking(newRanking);
	}


	public String displayBooks() {

		String message = "Livros:\n------\n";
		int counter = 0;
		for (int i = 0; i < dataSize; i++) {
			counter++;
			message = message + counter + ")" + booksArray[i].getTitle()
					+ "\r\n";
		}
		if (counter == 0)
			message = "Sem livros!\n";
		return message;

	}

	
}
