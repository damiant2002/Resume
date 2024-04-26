package hw5;

public class Book {
	private String title;
	private String author;
	private String pages;
	private String publisher;
	private String year;
	private String copies;
	private String category;

	// constructor

	public Book(String title, String author, String pages, String publisher, String year, String copies,
			String category) {
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.publisher = publisher;
		this.year = year;
		this.copies = copies;
		this.category = category;
	}

	// getters and setters

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

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCopies() {
		return copies;
	}

	public void setCopies(String copies) {
		this.copies = copies;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// displaying book information in an easy-to-understand format

	public String toString() {
		return title + "\n" + author + "\n" + "Page Count: " + pages + "\n" + "Publisher: " + publisher + ", " + year
				+ "\n" + "Copies in stock: " + copies + "\n" + category + "\n\n";
	}

	// for returning the information of the book to the original format the text
	// file can read (used for writing to books.txt)

	public String toStringOrg() {
		return title + "," + author + "," + pages + "," + publisher + "," + year + "," + copies + "," + category;
	}

}
