package hw5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchBookTextField;
	private JTextField searchCustTextField;
	private JTextField rentBookTextField;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public MainFrame() {
		String title = "", author = "", pages = "", publisher = "", year = "", copies = "", category = "", first = "",
				last = "", email = "", phone = "", address = "";
		int count = -1, countC = -1;
		ArrayList<Book> BookArrList = new ArrayList<>();
		ArrayList<Customer> CustomerArrList = new ArrayList<>();
		Book[] BookList = new Book[30];
		Customer[] CustomerList = new Customer[30];

		/**
		 * Reads the file containing the book information and creates an object for each line. 
		 * File MUST be in the format: title,author,page count,publisher,year,copies in stock,genre
		 */
		
		try (Scanner fileScanner = new Scanner(Paths.get("books.txt"))) {
			while (fileScanner.hasNextLine()) {
				count++;
				String line = fileScanner.nextLine();

				String[] split = line.split(",");
				for (int i = 0; i < 1; i++) {
					title = split[0];
					author = split[1];
					pages = split[2];
					publisher = split[3];
					year = split[4];
					copies = split[5];
					category = split[6];

					BookList[count] = new Book(title, author, pages, publisher, year, copies, category);
					BookArrList.add(BookList[count]);

				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		/*
		 * Reads the file containing the information for the customers and creates an
		 * object for each individual.
		 * File MUST be in the format: first,last,email,phone number,address
		 */
		
		try (Scanner fileScanner = new Scanner(Paths.get("customers.txt"))) {
			while (fileScanner.hasNextLine()) {
				countC++;
				String line2 = fileScanner.nextLine();
				String[] split1 = line2.split(",");
				for (int i = 0; i < 1; i++) {
					first = split1[0];
					last = split1[1];
					email = split1[2];
					phone = split1[3];
					address = split1[4];

				}
				CustomerList[countC] = new Customer(first, last, email, phone, address);
				CustomerArrList.add(CustomerList[countC]);
			}
			fileScanner.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		
		// creates panels, text fields, etc. for the frame
		
		setTitle("Bookstore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelWelcome = new JLabel("Welcome to Generic Bookstore!");
		labelWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		labelWelcome.setBounds(10, 11, 312, 42);
		contentPane.add(labelWelcome);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 312, 197);
		contentPane.add(scrollPane);

		JTextArea displaytextArea = new JTextArea();
		displaytextArea.setEditable(false);
		scrollPane.setViewportView(displaytextArea);

		JButton btnDisplayBook = new JButton("Display Books");
		btnDisplayBook.setBounds(10, 272, 145, 36);
		contentPane.add(btnDisplayBook);

		JButton btnDisplayCustomers = new JButton("Display Customers");
		btnDisplayCustomers.setBounds(177, 272, 145, 36);
		contentPane.add(btnDisplayCustomers);

		JLabel lblRentBook = new JLabel("Rent a Book by Title");
		lblRentBook.setBounds(10, 358, 117, 14);
		contentPane.add(lblRentBook);

		rentBookTextField = new JTextField();
		rentBookTextField.setBounds(10, 383, 312, 20);
		contentPane.add(rentBookTextField);
		rentBookTextField.setColumns(10);

		JLabel lblSearchBook = new JLabel("Search Book by Title");
		lblSearchBook.setBounds(10, 414, 117, 14);
		contentPane.add(lblSearchBook);

		searchBookTextField = new JTextField();
		searchBookTextField.setText("");
		searchBookTextField.setBounds(10, 439, 312, 20);
		contentPane.add(searchBookTextField);
		searchBookTextField.setColumns(10);

		JLabel lblSearchCust = new JLabel("Search Customer by Name");
		lblSearchCust.setBounds(10, 470, 218, 14);
		contentPane.add(lblSearchCust);

		searchCustTextField = new JTextField();
		searchCustTextField.setToolTipText("firstname or lastname OR firstname lastname");
		searchCustTextField.setBounds(10, 495, 312, 20);
		contentPane.add(searchCustTextField);
		searchCustTextField.setColumns(10);

		// displays list of books
		
		btnDisplayBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displaytextArea.setText("");
				for (int i = 0; i < BookArrList.size(); i++) {
					displaytextArea.append(BookList[i].toString());
				}
			}
		});

		// displays list of customers
		
		btnDisplayCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displaytextArea.setText("");
				for (int i = 0; i < CustomerArrList.size(); i++) {
					displaytextArea.append(CustomerList[i].toString());
				}
			}
		});

		/*
		 * Rents a book based on the title the user has input and updates the book text
		 * file to show that one less copy is in the store. Program changes and reads
		 * all text in lower case so capitalization is not an issue. Displays a
		 * confirmation the book is rented and how many copies remain.
		 */
		
		rentBookTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displaytextArea.setText("");
				String rentB = rentBookTextField.getText().toLowerCase();
				for (int i = 0; i < BookArrList.size(); i++) {
					if (BookList[i].getTitle().toLowerCase().equals(rentB)) {
						int copy = Integer.valueOf(BookList[i].getCopies());
						if (copy == 0) {
							displaytextArea.setText("All copies of this book have already been rented.");
						} else {
							copy -= 1;
							BookList[i].setCopies(String.valueOf(copy));
							try (PrintWriter output = new PrintWriter("books.txt")) {
								for (Book updateCopy : BookArrList) {
									output.println(updateCopy.toStringOrg());
								}
								output.close();
							} catch (Exception err) {
								System.out.println("Error: " + err.toString());
							}
							displaytextArea.setText("Book successfully rented.\n" + BookList[i].getCopies()
									+ " copies of this book remain");
						}
					}
				}
				if (displaytextArea.getText().equals("")) {
					displaytextArea.setText("No such book exists.\n(Double-check spelling and spacing)");
				}

			}
		});

		// searches and displays the information of a book based on the user inputed title
		
		searchBookTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displaytextArea.setText("");
				String searchB = searchBookTextField.getText().toLowerCase();
				for (int i = 0; i < BookArrList.size(); i++) {
					if (BookList[i].getTitle().toLowerCase().equals(searchB)) {
						displaytextArea.setText(BookList[i].toString());
					}
					if (displaytextArea.getText().equals("")) {
						displaytextArea.setText("No such book exists.\n(Double-check spelling and spacing)");
					}
				}

			}
		});

		/*
		 * Searches and displays the information of a customer based on the user inputed
		 * name. Can search for them based on only the first name, only the last, or
		 * both first and last together separated by a single space. When searching by
		 * only first or last name, any customers with that name will display. Program changes and reads
		 * all text in lower case so capitalization is not an issue.
		 */
		
		searchCustTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displaytextArea.setText("");
				String searchC = searchCustTextField.getText().toLowerCase();
				for (int i = 0; i < CustomerArrList.size(); i++) {
					if (searchC.equals(
							CustomerList[i].getFirst().toLowerCase() + " " + CustomerList[i].getLast().toLowerCase())) {
						displaytextArea.append(CustomerList[i].toString());

					} else if (CustomerList[i].getFirst().toLowerCase().equals(searchC)) {
						displaytextArea.append(CustomerList[i].toString());

					} else if (CustomerList[i].getLast().toLowerCase().equals(searchC)) {
						displaytextArea.append(CustomerList[i].toString());

					}

				}
				if (displaytextArea.getText().equals("")) {

					displaytextArea.setText("No such customer shops here.\n(Double-check spelling and spacing)");

				}
			}
		});
	}
}
