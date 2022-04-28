package com.day27PracticeProblem;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddressBook {
	
	BookList booklist = new BookList();

	void addContact(File file) throws IOException {
		Contact contact = new Contact();
		contact.addContact();
		String contactDetails = contact.toString();
		Scanner sc = new Scanner(file);
		StringBuffer sb = new StringBuffer();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		while(sc.hasNext()) {
			System.out.println(1);
			sb.append(sc.nextLine());
			sb.append("\n");
		}
		boolean duplicateContact = booklist.duplicateContact(file, contact.firstName);
		if (duplicateContact == true) {
			System.out.println("It is a duplicate contact.");
			bw.close();
			return;
		} else {
			sb.append(contactDetails+"\n");
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			System.out.println("Contact added successfully");
		}

	}

	void deletePerson(String name,String bookname) throws IOException {
		File file = new File("C:\\Users\\avije\\purabi\\" +bookname+".txt");
		booklist.deleteContact(file, name);
	}

	void editPerson(String name, String bookname) throws IOException {
		File file = new File("C:\\Users\\avije\\purabi\\" +bookname+".txt");
		booklist.updateContact(file, name);

	}
	boolean viewSortedResult(int option, String bookName) throws IOException {
		File file = new File("C:\\Users\\avije\\purabi\\" + bookName + ".txt");
		return booklist.viewSortedResult(file, option);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Address Book Program ");

		BookList shelf = new BookList();

		while (true) {
			AddressBook addressBook = new AddressBook();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the name of Book you want to  access or add  or type 'city' to search persons by city or type 'state' to search by state or press 'q' to quit");
			String bookName = scanner.nextLine();
			if (bookName.equals("q")) {
				System.out.println("The program is closed");
				break;
			}
			else if(bookName.equals("city")) {
				Scanner scan = new Scanner(System.in);
				System.out.println("Enter the name of city  :");
				String placeName = scan.nextLine();
				shelf.showPersonsByCity(placeName);
				continue;
			}
			else if(bookName.equals("state")) {
				Scanner scan = new Scanner(System.in);
				System.out.println("Enter the name of state  :");
				String placeName = scan.nextLine();
				shelf.showPersonsByState(placeName);
				continue;
			}
			int result = shelf.checkBook(bookName);
													
			int condition = 0;
			File file = new File("C:\\Users\\avije\\purabi\\" +bookName+".txt");
			while (true) {
				if (result == 1) {
					break;
				}
				System.out
						.println("Do you want to add/edit/delete the contact (0/1/2) :Press 3 to Go back to main menu");
				Scanner scan = new Scanner(System.in);
				int input = scan.nextInt();

				if (input == 0) {

					addressBook.addContact(file);

				} else if (input == 1) {
					Scanner scanner1 = new Scanner(System.in);
					System.out.println("Enter the first name of person you to edit ");
					String name = scanner1.nextLine();
					addressBook.editPerson(name,bookName);

				} else if (input == 2) {
					Scanner scanner2 = new Scanner(System.in);
					System.out.println("Enter the first name of the person you want to delete : ");
					String name = scanner2.nextLine();
					addressBook.deletePerson(name,bookName);
				}

				else if (input == 3) {
					break;
				}
				else if (input == 4) {
					Scanner scanner4 = new Scanner(System.in);
					boolean value = true;
					while (value) {
						System.out.println(
								"Press \n 0 to sort by contact name \n 1 to sort by city \n 2 to sort by state \n 3 to sort by zip");
						int response = scanner4.nextInt();
						value = addressBook.viewSortedResult(response, bookName);
					}
				}
				else {
					System.out.println("Enter the valid command");
				}
			}
		}

	}
}
