package library;




public class Members {

	private Member[] membersArray;

	private int dataSize;


    private UserInterface userInterface;



        public UserInterface getUserInterface(){
        return userInterface;
    }
        
    Members(UserInterface userinterface) {
        this.userInterface = userinterface;
    }

	public Member[] getMembersArray() {
		return membersArray;
	}


	public void setMembersArray(Member[] membersArray) {
		this.membersArray = membersArray;
	}


	public int getDataSize() {
		return dataSize;
	}


	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}


	public String register(Member aMember) {

		int index = dataSize;
		membersArray[index] = aMember;
		dataSize++; // incrementing data size
		return "\nRegistro completo.\n";
	}


	public int searchID(int id) {
		int index = -1;
		int i = 0;
		boolean found = false;
		while ((!found) && (membersArray[i] != null))// linear search
		{
			if (membersArray[i].getId() == id) {
				index = i;
				found = true;

			}
			i++;
		}

		return index;
	}


	public String removeMember(int id) {
		String message = "";
		int index = searchID(id);
		if (index == -1)
			message = "Invalid ID"; // ID validation
		else if (getUserInterface().getBorrower().getUnreturnedBooks(id) != 0)
			message = "Retornar os livros!\n";
		else {
			System.arraycopy(membersArray, index + 1, membersArray, index,
					membersArray.length - index - 1);
			dataSize--;
			message = "Membro removido.\n";

		}
		return message;
	}


	public String displayMembers() {

		String message = "Membros\n--------\n";
		int counter = 0;
		for (int i = 0; i < dataSize; i++) {
			counter++;
			message = message + counter + ")" + membersArray[i].getName()
					+ "\r\n";
		}
		if (counter == 0)
			message = "Sem membros\n";
		return message;

	}



}
