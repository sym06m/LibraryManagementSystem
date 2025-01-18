
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
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

    
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}


class LibraryMember {
    private String name;
    private int memberId;
    private List<Book> borrowedBooks;


    public LibraryMember(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    
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

    
    @Override
    public String toString() {
        return "LibraryMember{" +
                "name='" + name + '\'' +
                ", memberId=" + memberId +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}

class Library {
    private List<Book> books;
    private List<LibraryMember> members;

    
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    
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


    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", members=" + members +
                '}';
    }
}


public class LibraryManagementSystem {
    public static void main(String[] args) {
        
        Library library = new Library();

    
        Book book1 = new Book("1984", "George Orwell", "123456789");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "987654321");
        library.addBook(book1);
        library.addBook(book2);

        
        LibraryMember member1 = new LibraryMember("Alice", 1);
        LibraryMember member2 = new LibraryMember("Bob", 2);
        library.addMember(member1);
        library.addMember(member2);

    
        member1.borrowBook(book1);
        member2.borrowBook(book1);

    
        member1.returnBook(book1);
        member2.borrowBook(book1);

        
        System.out.println(library);
    }
}
