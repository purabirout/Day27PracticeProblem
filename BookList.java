package com.day27PracticeProblem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookList {
	
	void addInfo(Contact value) {
		Scanner scan = new Scanner(System.in);
		System.out.println("First Name :");
		value.firstName = scan.nextLine();
		System.out.println("Last Name :");
		value.lastName = scan.nextLine();
		System.out.println("Enter the address :");
		value.address = scan.nextLine();
		System.out.println("Enter city : ");
		value.city = scan.nextLine();
		System.out.println("Enter state : ");
		value.state = scan.nextLine();
		System.out.println("Enter Phone Number : ");
		value.phoneNumber = scan.nextLine();
		System.out.println("Enter Email : ");
		value.email = scan.nextLine();
		System.out.println("Enter zip : ");
		value.zip = scan.nextLine();
	}

	void showPersonsByCity(String placeName) throws IOException {
		int count = 0;
		ArrayList<String> lines = new ArrayList<String>();
		File dir = new File(
				"C:\\Users\\avije\\purabi\\");
		File[] directoryListing = dir.listFiles();
		BufferedReader reader;
		if (directoryListing != null) {
			for (File f1 : directoryListing) {
				if (f1.getName().matches("(.txt)$")) {
					reader = new BufferedReader(new FileReader(f1));
					String line = reader.readLine();
					while (line != null) {
						lines.add(line);
						line = reader.readLine();
					}
				}
			}
		List<String> matchedContact = lines.stream().filter(x -> x.split(",")[3].equals(placeName))
					.collect(Collectors.toList());
		System.out.println("The number of persons are " + matchedContact.size());
			matchedContact.stream().forEach(x -> System.out.println(x.split(",")[0]));

		} else {
			System.out.println("Booklist is empty");
		}
	}
	
	void showPersonsByState(String placeName)throws IOException {
		int count = 0;
		ArrayList<String> lines = new ArrayList<String>();
		File dir = new File(
				"C:\\\\Users\\\\avije\\\\purabi\\\\");
		File[] directoryListing = dir.listFiles();
		BufferedReader reader;
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (child.getName().matches("(.txt)$")) {
					reader = new BufferedReader(new FileReader(child));
					String line = reader.readLine();
					while (line != null) {
						lines.add(line);
						line = reader.readLine();
					}
				}
			}
			List<String> matchedContact = lines.stream().filter(x -> x.split(",")[4].equals(placeName))
					.collect(Collectors.toList());
			System.out.println("The number of persons are " + matchedContact.size());
			matchedContact.stream().forEach(x -> System.out.println(x.split(",")[0]));

		} else {
			System.out.println("Booklist is empty");
		}
	}
	
	boolean duplicateContact(File file, String firstName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				if (line.split(",")[0].equals(firstName)) {
					reader.close();
					return true;
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			// e.printStackTrace();
		}
		return false;
	}

	void updateContact(File file, String firstName) throws IOException {
		Scanner sc = new Scanner(file);
		StringBuffer buffer = new StringBuffer();
		while (sc.hasNextLine()) {
			buffer.append(sc.nextLine() + System.lineSeparator());
		}
		String fileContents = buffer.toString();
		sc.close();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		while (line != null) {
			if (line.split(",")[0].equals(firstName)) {
				Contact contact = new Contact();
				contact.addContact();
				String oldLine = line;
				String newLine = contact.toString();
				fileContents = fileContents.replaceAll(oldLine, newLine);
				FileWriter writer = new FileWriter(file);
				writer.append(fileContents);
				writer.flush();
				writer.close();
				System.out.println("Contact Updated successfully");
				return;
			}
			line = reader.readLine();
		}
		System.out.println("Contact doesn't exist with the given name " + firstName);

	}

	void deleteContact(File file, String firstName) throws IOException {
		Scanner sc = new Scanner(file);
		StringBuffer buffer = new StringBuffer();
		int track = 0;
		while (sc.hasNextLine()) {
			if (sc.nextLine().split(",")[0].equals(firstName)) {
				track = 1;
				continue;
			}
			buffer.append(sc.nextLine() + System.lineSeparator());
		}
		if (track == 1) {
			String fileContents = buffer.toString();
			sc.close();
			FileWriter writer = new FileWriter(file);
			writer.append(fileContents);
			writer.flush();
			writer.close();
			System.out.println("Contact deleted successfully");
		} else {
			System.out.println("No contact found with the given name");
			return;
		}
	}

	boolean viewSortedResult(File file, int sortingChoice) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		boolean value = false;
		while (line != null) {
			lines.add(line);
			line = reader.readLine();
		}
		reader.close();
		switch (sortingChoice) {
		case 0:
			lines.sort((String x, String y) -> x.split(",")[0].compareTo(y.split(",")[0]));
			lines.forEach((s) -> System.out.println(s));
			break;
		case 1:
			lines.sort((String x, String y) -> x.split(",")[3].compareTo(y.split(",")[3]));
			lines.forEach((s) -> System.out.println(s));
			break;
		case 2:
			lines.sort((String x, String y) -> x.split(",")[4].compareTo(y.split(",")[4]));
			lines.forEach((s) -> System.out.println(s));
			break;
		case 3:
			lines.sort((String x, String y) -> x.split(",")[7].compareTo(y.split(",")[7]));
			lines.forEach((s) -> System.out.println(s));
			break;
		default:
			System.out.println("Enter valid command");
			value = true;
			break;
		}
		return value;
	}

	void operations(File file) throws IOException {
		Scanner input = new Scanner(System.in);
		int condition1 = 0;
		int condition = 0; 
		while (condition == 0) {
			System.out.println("Do you want to add/edit/delete contact (0/1/2) :Press 3 to go back to main menu");
			int response = input.nextInt();
			switch (response) {
			case 0:
				BufferedWriter b = new BufferedWriter(new FileWriter(file));
				PrintWriter p = new PrintWriter(b);
				Contact contact = new Contact();
				contact.addContact();
				String contactDetails = contact.toString();
				System.out.println(contactDetails);
				boolean duplicateContact = duplicateContact(file, contact.firstName);
				if (duplicateContact == true) {
					System.out.println("It is a duplicate contact.");

				} else {
					p.println(contactDetails);
					p.close();
				}
				break;
			case 1:
				System.out.println("Enter the first name of person you want to edit :");
				Scanner scanner = new Scanner(System.in);
				String name1 = scanner.nextLine();
				updateContact(file, name1);
				break;
			case 2:
				System.out.println("Enter the first name of person you want to delete :");
				Scanner scan2 = new Scanner(System.in);
				String name2 = scan2.nextLine();
				deleteContact(file, name2);
				break;
			case 3:
				condition = 1;
				break;
			case 4:
				Scanner scan3 = new Scanner(System.in);
				boolean value = true;
				while (value) {
					System.out.println(
							"Press \n 0 to sort by contact name \n 1 to sort by city \n 2 to sort by state \n 3 to sort by zip");
					int response1 = scan3.nextInt();
					value = viewSortedResult(file, response1);
				}
				break;
			default:
				System.out.println("Enter valid command");
				break;
			}
		}
	}

	int checkBook(String name) throws IOException {
		int result = 0;
		int track = 0;
		File file = new File(
				"E:\\RFP_java\\Day22_AddressBookSystem\\src\\com\\bridgelabz\\" + name
						+ ".txt");
		if (!file.exists()) {
			System.out.println("Booklist was empty. " + name + " is created.");
			file.createNewFile();
		} else {

			System.out.println("Book exist please go ahead");
			operations(file);
			track = 1;
			result = 1;

		}
		return result;
	}
}
