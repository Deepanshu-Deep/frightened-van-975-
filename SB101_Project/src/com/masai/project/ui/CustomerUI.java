package com.masai.project.ui;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import com.masai.project.dao.CustomerDAO;
import com.masai.project.dao.CustomerDAOimpl;
import com.masai.project.dto.CustomerDTO;
import com.masai.project.dto.CustomerDTOimpl;
import com.masai.project.exception.NoRecordFoundException;
import com.masai.project.exception.SomethingWentWrongException;

public class CustomerUI {

	
    //Register new account
	
	public static void RegisterNewAccount(Scanner sc) {
	    System.out.println("\nPlease enter the following details to register for a new account:\n");

	    System.out.print("Customer Id: ");
	    int custommerId = sc.nextInt();

	    CustomerDAO customerDAO = new CustomerDAOimpl();

	    try {
	        if (customerDAO.isCustomerIdExists(custommerId)) {
	            System.out.println("\nCustomer id already exists. Please try again with a different id.");
	            return;
	        }

	        System.out.print("Name: ");
	        sc.nextLine();
	        String name = sc.nextLine();

	        System.out.print("Mobile Number: ");
	        String mobile = sc.next();

	        System.out.print("Address: ");
	        String address = sc.next();

	        System.out.print("Username: ");
	        String username = sc.next();

	        System.out.print("Password: ");
	        String password = sc.next();

	        CustomerDTO customerDTO = new CustomerDTOimpl(custommerId, name, address, mobile, username, password);

	        customerDAO.newRegister(customerDTO);

	        System.out.println("Customer registered successfully");

	    } catch (SomethingWentWrongException | NoRecordFoundException ex) {
	        System.out.println(ex.getMessage());
	    }
	}




	
	
	
	
		
	
//*************************************************************************************************	

	
	//View Customer Details
	public static void viewCustomerInformation() {
		
		CustomerDAO customerDAO = new CustomerDAOimpl();
			
		try {
			List<CustomerDTO> list = customerDAO.viewInformationAboutCustomer();
				
			if (list.isEmpty()) {
				System.out.println("No customer found");
			} else {
				System.out.println();
				for (CustomerDTO customer : list) {
					System.out.println("Customer ID: " + customer.getCustomerId() + ", Name: " + customer.getName() 
						+ ", Mobile Number: " + customer.getMobileNumber() + ", Address: " + customer.getAddress() 
						+ ", Username: " + customer.getUsername() + ", password: " + customer.getPassword() );
					System.out.println();
					
				}
				System.out.println();
			}
				
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex.getMessage());		
		}
	}
	

//************************************************************************************************
	
	
	//view Customer Details By Customer Id
	static void viewCustomerDetailsByCustomerId(Scanner sc) {
		
		System.out.println("Enter the Customer Id");
		int Cid = sc.nextInt();
		
		CustomerDAO customerDAO = new CustomerDAOimpl();
		
		try {
			
			List<CustomerDTO> list = customerDAO.viewCustomerDetailsById(Cid);
			System.out.println();
			Consumer<CustomerDTO> cun = res -> System.out.println(" Customer Name : "+ res.getName() +
					", Address : "+ res.getAddress() + ", Mobile Number : "+ res.getMobileNumber());;
			
			list.forEach(cun);
			
			System.out.println();
			
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex.getMessage());
		}	
	}
	
	
	
	
	

	
//*************************************************************************************************	
	
	//Update Customer Details
	static void updateCustomer(Scanner sc) {
		
		System.out.print("Enter customer id ");
		String cid = sc.next();
		int customerId = Integer.parseInt(cid);

		System.out.print("Enter customer name ");
		sc.nextLine();
		String name = sc.nextLine();

		System.out.print("Enter mobile Number ");
		String mobile = sc.next();

		System.out.print("Enter address ");
		String address = sc.next();

		CustomerDTO customerDTO = new CustomerDTOimpl(customerId, name, address, mobile);

		CustomerDAO customerDAO = new CustomerDAOimpl();
		try {
		    customerDAO.UpdateCustomerDetails(customerDTO);
		    System.out.println("Customer updated successfully");
		} catch(SomethingWentWrongException | NoRecordFoundException ex) {
		    System.out.println(ex.getMessage());
		}
	}
	
	
//*********************************************************************************************
	
	
	//Change password
	
	static void changePassword(Scanner sc) {

				
		System.out.print("Enter customer id ");
		String cid = sc.next();
		int customerId = Integer.parseInt(cid);

	    CustomerDAO customerDAO = new CustomerDAOimpl();

	    try {
	        if (!customerDAO.isCustomerIdExists(customerId)) {
	            System.out.println("\nCustomer id not exists. Please try again with a correct id.");
	            return;
	        }


		System.out.print("Enter password ");
		String password = sc.next();

		CustomerDTO customerDTO = new CustomerDTOimpl(customerId, password);
		customerDAO.changePassword(customerDTO);
		   System.out.println("Passwoord changed successfully");

	    } catch (SomethingWentWrongException | NoRecordFoundException ex) {
	        System.out.println(ex.getMessage());
	    }
		
		
	}
	
	
	




	
	
	
	
	
//*************************************************************************************************	
	
	
	// Existing user login
	 static boolean login(Scanner sc) {

	    System.out.println("Enter the Username : ");
	    String username = sc.next();

	    System.out.println("Enter the Password");
	    String password = sc.next();

	    CustomerDAO customerDAO = new CustomerDAOimpl();

	    try {
	    	
	    	// You can use the user_id to identify the customer who has logged in
	    	
	        int user_id =  customerDAO.login(username, password);

	        if (user_id == -1) {
	            System.out.println("Invalid username or password");
	            return false;
	        } else {
	            System.out.println("\nLogin successful\n");
	            UIMain.UserMenu(sc);
	           
	            return true;
	        }

	    } catch (SomethingWentWrongException | NoRecordFoundException ex) {
	        System.out.println(ex.getMessage());
	        return false;
	    }

	}





	

	
	
	
	
	
	
	
	
}
