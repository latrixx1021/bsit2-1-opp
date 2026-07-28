import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main() {

    }

    // ===== Book class =====
    static class Book {
        private String title;
        private String author;
        private boolean isBorrowed;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isBorrowed = false;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isBorrowed() {
            return isBorrowed;
        }

        public void borrow() {
            isBorrowed = true;
        }

        public void returnBook() {
            isBorrowed = false;
        }

        public String describe() {
            String status = isBorrowed ? "Borrowed" : "Available";
            return title + " by " + author + " [" + status + "]";
        }
    }

    // ===== Library class =====
    static class Library {
        private ArrayList<Book> books;

        public Library() {
            books = new ArrayList<Book>();
        }

        public void addBook(Book book) {
            books.add(book);
            System.out.println("Book added: " + book.getTitle());
        }

        public void listBooks() {
            if (books.size() == 0) {
                System.out.println("No books in the library yet.");
                return;
            }

            System.out.println("---- Library Books ----");
            for (int i = 0; i < books.size(); i++) {
                Book b = books.get(i);
                System.out.println((i + 1) + ". " + b.describe());
            }
        }

        private Book findBook(String title) {
            for (int i = 0; i < books.size(); i++) {
                Book b = books.get(i);
                if (b.getTitle().equalsIgnoreCase(title)) {
                    return b;
                }
            }
            return null;
        }

        public void borrowBook(String title) {
            Book b = findBook(title);
            if (b == null) {
                System.out.println("Book not found: " + title);
            } else if (b.isBorrowed()) {
                System.out.println("Sorry, that book is already borrowed.");
            } else {
                b.borrow();
                System.out.println("You borrowed: " + b.getTitle());
            }
        }

        public void returnBook(String title) {
            Book b = findBook(title);
            if (b == null) {
                System.out.println("Book not found: " + title);
            } else if (!b.isBorrowed()) {
                System.out.println("That book was not borrowed.");
            } else {
                b.returnBook();
                System.out.println("You returned: " + b.getTitle());
            }
        }

        public void searchBook(String title) {
            Book b = findBook(title);
            if (b == null) {
                System.out.println("No book found with title: " + title);
            } else {
                System.out.println("Found: " + b.describe());
            }
        }
    }

    // ===== Main program with menu =====
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;

        while (running) {
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add a book");
            System.out.println("2. List all books");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Search a book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                System.out.print("Enter author: ");
                String author = scanner.nextLine();
                library.addBook(new Book(title, author));

            } else if (choice.equals("2")) {
                library.listBooks();

            } else if (choice.equals("3")) {
                System.out.print("Enter title to borrow: ");
                String title = scanner.nextLine();
                library.borrowBook(title);

            } else if (choice.equals("4")) {
                System.out.print("Enter title to return: ");
                String title = scanner.nextLine();
                library.returnBook(title);

            } else if (choice.equals("5")) {
                System.out.print("Enter title to search: ");
                String title = scanner.nextLine();
                library.searchBook(title);

            } else if (choice.equals("0")) {
                System.out.println("Goodbye! Thanks for using the Library System.");
                running = false;

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
