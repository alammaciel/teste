package library;

import java.text.ParseException;


public class Validator {


	public static final int MAXIMUM_LENGTH = 15;


    private UserInterface userInterface;


    Validator(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
        public UserInterface getUserInterface(){
        return userInterface;
    }


	public boolean isValidEmail(String mail) {

		String[] tokens = mail.split("@");

		if (mail == null)
			return false;

		if (mail.lastIndexOf(".") > mail.lastIndexOf("@") && tokens.length == 2
				&& !tokens[0].isEmpty())
			return true;
		else
			return false;
	}


	public boolean isValidPhoneNumber(String phoneNumber) {

		if (phoneNumber == null || phoneNumber.length() > MAXIMUM_LENGTH)
			return false;
		int i = 0;
		while (i < phoneNumber.length()) {
			if (!Character.isDigit(phoneNumber.charAt(i))
					&& !(phoneNumber.charAt(i) == '-'))
				return false;
			else
				i++;
		}
		return true;
	}


	public boolean isValidInteger(String integer) {

		if (integer == null || integer.isEmpty()
				|| integer.length() > MAXIMUM_LENGTH)
			return false;
		int i = 0;
		while (i < integer.length()) {
			if (!Character.isDigit(integer.charAt(i)))
				return false;
			else
				i++;
		}
		return true;
	}


	public boolean isValidString(String inData) {
		if (inData.contains(","))
			return false;
		else
			return true;
	}


	public boolean isValidName(String name) {
		if (name == null)
			return false;
		int i = 0;
		while (i < name.length()) {
			if (!Character.isLetter(name.charAt(i)) && !(name.charAt(i) == ' ')
					&& !(name.charAt(i) == '.'))
				return false;
			else
				i++;
		}

		return true;

	}


	public boolean isValidISBN(String isbn) {
		if (isbn == null || !isbn.contains("-"))
			return false;
		int i = 0;
		while (i < isbn.length()) {
			if (!Character.isDigit(isbn.charAt(i)) && !(isbn.charAt(i) == '-'))
				return false;
			else
				i++;
		}
		return true;
	}


	public boolean isValidDate(String date) {
		try {
			IOManager.parseDate(date);
		} catch (ParseException e) {
			return false;
		}
		return true;

	}

	public boolean isValidBookLine(String[] bookData) {
		boolean valid = isValidName(bookData[1]) && isValidISBN(bookData[3])
				&& isValidDate(bookData[4]) && isValidInteger(bookData[5])
				&& isValidInteger(bookData[6]);
		return valid;
	}


	public boolean isValidMemberLine(String[] memberData) {

		boolean valid = isValidInteger(memberData[0])
				&& isValidName(memberData[1])
				&& isValidPhoneNumber(memberData[5])
				&& isValidInteger(memberData[6])
				&& ((memberData.length == 8) ? isValidEmail(memberData[7])
						: true); // the system allows empty field for e-mail

		return valid;
	}


	public boolean isValidBorrowingLine(String[] borrowingData) {

		boolean valid = isValidISBN(borrowingData[0])
				&& (getUserInterface().getBooks().searchISBN(borrowingData[0]) != -1)
				&& isValidInteger(borrowingData[1])
				&& isValidDate(borrowingData[2])
				&& isValidDate(borrowingData[3])
				&& ((borrowingData.length == 4 || borrowingData[4]
						.equalsIgnoreCase(" ")) ? true
						: isValidDate(borrowingData[4]));
		return valid;
	}


}
