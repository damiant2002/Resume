package hw5;

public class Customer {
	private String first;
	private String last;
	private String email;
	private String phone;
	private String address;

	// constructor
	
	public Customer(String first, String last, String email, String phone, String address) {
		this.first = first;
		this.last = last;
		this.email = email;
		this.phone = phone;
		this.address = address;

	}

	// getters and setters
	
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// displaying customer information in an easy-to-understand format
	
	public String toString() {
		return first + " " + last + "\n" + email + "\n" + phone + "\n" + address + "\n\n";
	}

}
