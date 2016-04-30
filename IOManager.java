package library;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOManager {

    private UserInterface userInterface;

    private String booksFileLocation;

    private String membersFileLocation;

    private String borrowingFileLocation;


    IOManager(UserInterface userInterface) {
        this.userInterface = userInterface;
    }



    public UserInterface getUserInterface() {
        return userInterface;
    }


    public void setBorrowingFileLocation(String borrowingFileLocation) {
        this.borrowingFileLocation = borrowingFileLocation;
    }

    public void setBooksFileLocation(String booksFileLocation) {
        this.booksFileLocation = booksFileLocation;
    }

    public void setMembersFileLocation(String membersFileLocation) {
        this.membersFileLocation = membersFileLocation;
    }

    public String inputString(String arg) {
        String inData = null;
        try {
            InputStreamReader inStream = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(inStream);
            System.out.println(arg);
            inData = stdin.readLine();
        } catch (IOException e) {
            System.out.println("IO Exception " + e);
            System.exit(0);

        }

        if (inData == null) {
            System.exit(0);
        }
        return inData;

    }

    public int inputInt(String arg, int lowerLimit) {
        int value = 0;
        String inData = "";
        try {
            InputStreamReader inStream = null;
            BufferedReader stdin = null;
            do {
                inStream = new InputStreamReader(System.in);
                stdin = new BufferedReader(inStream);
                System.out.println(arg);
                inData = stdin.readLine();
                value = Integer.parseInt(inData);
            } while (value < lowerLimit && (value != -1));
        // forcing the user to input valid integer
        // using do- while loop
        } catch (IOException e) {
            System.out.println("IO Exception " + e);
            System.exit(0);
        } catch (NumberFormatException e) {
            if (inData == null) {
                System.exit(0);
            }
            System.out.println("Number format exception " + e);
            return inputInt(arg, lowerLimit);
        }

        return value;

    }


    public Book[] readBooksFile() throws ParseException {
        int i = 0;
        Book[] booksArray = new Book[1000];
        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    booksFileLocation));
            String s;

            while ((s = in.readLine()) != null) {
                String[] tokens = s.split(",");
                if (tokens.length == 8 || (tokens.length == 7 && s.charAt(s.length() - 1) == ',')) {

                    if (getUserInterface().getValidator().isValidBookLine(tokens)) {
                        booksArray[i] = new Book();
                        booksArray[i].setTitle(tokens[0]);
                        booksArray[i].setAuthor(tokens[1]);
                        booksArray[i].setPublisher(tokens[2]);
                        booksArray[i].setIsbn(tokens[3]);
                        booksArray[i].setPublicationDate(parseDate(tokens[4]));
                        booksArray[i].setNumberOfCopies(Integer.parseInt(tokens[5]));
                        booksArray[i].setAvailableNumberOfCopies(Integer.parseInt(tokens[6]));
                        if (s.charAt(s.length() - 1) != ',') {
                            booksArray[i].setCategory(tokens[7]);
                        } else {
                            booksArray[i].setCategory("");
                        }
                        i++;

                    }

                }
            }
            in.close();
            getUserInterface().getBooks().setDataSize(i);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado! " + e);
            getUserInterface().getBooks().setDataSize(0);
        } catch (NullPointerException e) {
        	System.out.println("Arquivo nao encontrado! " + e);
            getUserInterface().getBooks().setDataSize(0);
        }catch (IOException e) {
            System.out.println("IOException " + e);
        }
        return booksArray;

    }

    public Member[] readMembersFile() {
        int i = 0;
        Member[] membersArray = new Member[1000];
        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    membersFileLocation));
            String s;
            while ((s = in.readLine()) != null) {
                String[] tokens = s.split(",");// splitting each line
                if (tokens.length == 8 || (tokens.length == 7 && s.charAt(s.length() - 1) == ',')) {
                    if (getUserInterface().getValidator().isValidMemberLine(tokens)) {
                        membersArray[i] = new Member();
                        membersArray[i].setId(Integer.parseInt(tokens[0]));
                        membersArray[i].setName(tokens[1]);
                        membersArray[i].setAddress(tokens[2]);
                        membersArray[i].setStreet(tokens[3]);
                        membersArray[i].setCity(tokens[4]);
                        membersArray[i].setPhoneNumber(tokens[5]);
                        membersArray[i].setAge(Integer.parseInt(tokens[6]));
                        if (s.charAt(s.length() - 1) != ',') {
                            membersArray[i].setEmail(tokens[7]);
                        } else 
                        {
                            membersArray[i].setEmail("");
                        }
                        i++;
                    }

                }

            }
            in.close();
            getUserInterface().getMembers().setDataSize(i);
        } catch (FileNotFoundException e) {
            System.out.println("FArquivo nao encontrado! " + e);
            getUserInterface().getMembers().setDataSize(0);
        } catch (NullPointerException e) {
        	System.out.println("Arquivo nao encontrado! " + e);
            getUserInterface().getBooks().setDataSize(0);
        }catch (IOException e) {
            System.out.println("IOException" + e);
        }
        return membersArray;
    }

    public Borrower[] readBorrowingFile() throws ParseException {
        int i = 0;
        Borrower[] dataArray = new Borrower[1000];
        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    borrowingFileLocation));
            String s;
            while ((s = in.readLine()) != null) {
                String[] tokens = s.split(",");
                if (tokens.length == 5 || (tokens.length == 4 && s.charAt(s.length() - 1) == ',')) {
                    if (getUserInterface().getValidator().isValidBorrowingLine(tokens)) {
                        dataArray[i] = new Borrower();
                        dataArray[i].setIsbn(tokens[0]);
                        dataArray[i].setId(Integer.parseInt(tokens[1]));
                        dataArray[i].setDateIssued(parseDate(tokens[2]));
                        dataArray[i].setDueDate(parseDate(tokens[3]));
                        dataArray[i] // dateReturned==null when its field
                                // contains " " or is empty
                                .setDateReturned((tokens.length == 4 || tokens[4].equalsIgnoreCase(" ")) ? null
                                : parseDate(tokens[4]));
                        int index = getUserInterface().getBooks().searchISBN(tokens[0]);
                        getUserInterface().getBooks().incrementRanking(
                                getUserInterface().getBooks().getBooksArray()[index]);
                        i++;
                    }
                }
            }
            in.close();
            getUserInterface().getBorrower().setDataSize(i);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado! " + e);
            getUserInterface().getBorrower().setDataSize(0);
        } catch (NullPointerException e) {
        	System.out.println("Arquivo nao encontrado! " + e);
            getUserInterface().getBooks().setDataSize(0);
        }catch (IOException e) {
            System.out.println("IOException. " + e);
        }
        return dataArray;
    }

    public void printBooksFile() throws IOException {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(booksFileLocation));
            for (int i = 0; getUserInterface().getBooks().getBooksArray()[i] != null; i++) {
                pw.println(getUserInterface().getBooks().getBooksArray()[i].toString());
            }

            pw.close();

        }catch (NullPointerException e) {
        	   System.out.println("Nao pode encontrar esse caminho! " + e);
        } catch (FileNotFoundException e) {
            System.out.println("Nao pode encontrar esse caminho! " + e);

        }
    }


    public void printMembersFile() throws IOException {
        // printing elements of members array
        try {
            PrintWriter pw = new PrintWriter(
                    new FileWriter(membersFileLocation));
            for (int i = 0; getUserInterface().getMembers().getMembersArray()[i] != null; i++) {
                pw.println(getUserInterface().getMembers().getMembersArray()[i].toString());

            }
            pw.close();

        }catch (NullPointerException e) {
        	   System.out.println("Nao pode encontrar o caminho! " + e);
        } catch (FileNotFoundException e) {
            System.out.println("Nao pode encontrar o caminho! " + e);

        }
    }

    public void printBorrowingFile() throws IOException {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(
                    borrowingFileLocation));
            for (int i = 0; getUserInterface().getBorrower().getBorrowingArray()[i] != null; i++) {
                pw.println(getUserInterface().getBorrower().getBorrowingArray()[i].toString());
            }
            pw.close();
        }catch (NullPointerException e) {
        	   System.out.println("Nao pode encontrar o caminho! " + e);
        } catch (FileNotFoundException e) {
            System.out.println("Nao pode encontrar o caminho! " + e);
        }
    }


    public static String formatDate(Date aDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMMM dd yyyy");
        if (aDate == null) {
            return "";
        }
        return formatter.format(aDate);
    }

    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("MMMMM dd yyyy");
        return format.parse(date);
    }
}
