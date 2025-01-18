// Class representing a Book
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    // Constructor
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    // Getters and setters
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

    // Overriding equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return isbn.equals(book.isbn);
    }

    // Overriding hashCode method
    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}

// Class representing a Library Member
class LibraryMember {
    private String name;
    private int memberId;
    private List<Book> borrowedBooks;

    // Constructor
    public LibraryMember(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Methods to borrow and return books
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            System.out.println(name + " borrowed " + book.getTitle());
        } else {
            System.out.println(book.getTitle() + " is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            System.out.println(name + " returned " + book.getTitle());
        } else {
            System.out.println(name + " does not have " + book.getTitle());
        }
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "LibraryMember{" +
                "name='" + name + '\'' +
                ", memberId=" + memberId +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }

    // Overriding equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryMember that = (LibraryMember) obj;
        return memberId == that.memberId;
    }

    // Overriding hashCode method
    @Override
    public int hashCode() {
        return Integer.hashCode(memberId);
    }
}

// Class representing the Library
class Library {
    private List<Book> books;
    private List<LibraryMember> members;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // Methods to add and remove books
    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " added to the library.");
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            System.out.println(book.getTitle() + " removed from the library.");
        } else {
            System.out.println(book.getTitle() + " not found in the library.");
        }
    }

    // Methods to add and remove members
    public void addMember(LibraryMember member) {
        members.add(member);
        System.out.println(member.getName() + " is now a library member.");
    }

    public void removeMember(LibraryMember member) {
        if (members.remove(member)) {
            System.out.println(member.getName() + " removed from library membership.");
        } else {
            System.out.println(member.getName() + " not found in library members.");
        }
    }

    // Sorting books by title
    public void sortBooksByTitle() {
        books.sort(Comparator.comparing(Book::getTitle));
        System.out.println("Books sorted by title:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Sorting members by name
    public void sortMembersByName() {
        members.sort(Comparator.comparing(LibraryMember::getName));
        System.out.println("Members sorted by name:");
        for (LibraryMember member : members) {
            System.out.println(member);
        }
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", members=" + members +
                '}';
    }
}

// Main class to test the system
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Creating library
        Library library = new Library();

        // Adding books
        Book book1 = new Book("1984", "George Orwell", "123456789");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "987654321");
        Book book3 = new Book("Brave New World", "Aldous Huxley", "123123123");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Sorting books
        library.sortBooksByTitle();

        // Adding members
        LibraryMember member1 = new LibraryMember("Alice", 1);
        LibraryMember member2 = new LibraryMember("Bob", 2);
        LibraryMember member3 = new LibraryMember("Charlie", 3);
        library.addMember(member1);
        library.addMember(member2);
        library.addMember(member3);

        // Sorting members
        library.sortMembersByName();

        // Borrowing books
        member1.borrowBook(book1);
        member2.borrowBook(book1);

        // Returning books
        member1.returnBook(book1);
        member2.borrowBook(book1);

        // Printing library state
        System.out.println(library);
    }
}
