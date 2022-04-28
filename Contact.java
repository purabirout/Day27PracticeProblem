package com.day27PracticeProblem;

import java.util.Scanner;

public class Contact {
	String firstName;
	String lastName;
	String address;
	String city;
	String state;
	String phoneNumber;
	String email;
	String zip;
	
	void addContact() {
		Scanner scan=new Scanner(System.in);
		System.out.println("First Name :");
		this.firstName = scan.nextLine();
		System.out.println("Last Name :");
		this.lastName = scan.nextLine();
		System.out.println("Enter the address :");
		this.address = scan.nextLine();
		System.out.println("Enter city : ");
		this.city = scan.nextLine();
		System.out.println("Enter state : ");
		this.state = scan.nextLine();
		System.out.println("Enter Phone Number : ");
		this.phoneNumber = scan.nextLine();
		System.out.println("Enter Email : ");
		this.email = scan.nextLine();
		System.out.println("Enter zip : ");
		this.zip = scan.nextLine();
	}

	@Override
	public String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", phoneNumber=" + phoneNumber + ", email=" + email + ", zip=" + zip + "]";
	}
	
}
