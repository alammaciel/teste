package library;


public class Member {

	private int id;

	private String name;

	private String address;

	private String street;

	private String city;

	private String phoneNumber;

	private int age;

	private String email;


	public Member() {
		this(0, "", "", "", "", "", 0, "");
	}

 Member(int id, String name, String address, String street,
			String city, String phoneNumber, int age, String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.street = street;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.email = email;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String toString() {
		return id + "," + name + "," + address + "," + street + "," + city
				+ "," + phoneNumber + "," + age + "," + email;
	}
}
