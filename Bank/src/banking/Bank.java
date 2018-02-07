package banking;

import java.util.ArrayList;
import java.util.Date;

public class Bank {

	private ArrayList<User> users  = new ArrayList<User>();
	private ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	private String bankName,swift;
	private ArrayList<String> BankTransactions  = new ArrayList<String>();
	
	public ArrayList<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public ArrayList<String> getBankTransactions() {
		return BankTransactions;
	}

	public void addBankTransaction(String bankTransaction) {
		BankTransactions.add(bankTransaction);
	}

	public void changeUserName(String Name, String UserId) {
		User user = getUserById(UserId);
		user.setUserName(Name);
	}
	
	public void changeUserSurname(String Surname, String UserId) {
		User user = getUserById(UserId);
		user.setUserSurname(Surname);
	}
	
	public void changeUserDate(Date date, String UserId) {
		User user = getUserById(UserId);
		user.setDate(date);
	}

	public Bank( String _bankName, String _swift) {
		this.swift = _swift;
		this.setBankName(_bankName);
	}
	
	public void createUser(String name,String surname,Date date, String id) {
		User user = new User(name, surname, date, id);
		this.users.add(user);
	}
	
	public User getUserById(String id) {
		User findUser = null;
		for(User user: users) {
			if(user.getId() == id) {
				findUser = user;
			}
		}
		return findUser;
	}
	
	public User getUserByAccNumber(String accNumber) {
		User findUser = null;
		String OwnerId = "";
		for(BankAccount acc: bankAccounts) {
			if(acc.getAccNumber() == accNumber) {
				OwnerId = acc.getOwnerId();
				findUser =  getUserById(OwnerId);
				}
		}
		return findUser;
	}
	public BankAccount getBankAccountByAccNumber(String accNumber) {
		BankAccount bAcc = null;
		for(BankAccount bankAcc: bankAccounts) {
			if(bankAcc.getAccNumber() == accNumber) {
				bAcc = bankAcc;
			}
		}
		return bAcc;
	}
	
	public void createBankAccountForUser(String userId,String accNumber,String accName) throws Exception {
		User user = getUserById(userId);
		BankAccount bankAccount = new BankAccount(0, accNumber, userId);
		user.addUserBankAccount(bankAccount);
		bankAccounts.add(bankAccount);
	}
	
	public BankAccount getBankAccountByUserIdAndAccNumber(String userId,String accNumber) {
		BankAccount bAcc = null;
		User findUser = null;
		for(User user: users) {
			if(user.getId() == userId) {
				findUser = user;
			}
		}
		bAcc = findUser.getBankAccByAccNumber(accNumber);
		return bAcc;
	}
	
	public void showBankTransactions() {
		for(String trans: BankTransactions)
		System.out.println(trans);
	}
	
	public void showUserTransactions(String userId) {
		System.out.println(getUserById(userId).getUserTransactions());
	}
	
	public void showAccountTransactions(String accNumber) {
		System.out.println(getBankAccountByAccNumber(accNumber).getAccTransactions());
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String _swift) {
		swift = _swift;
	}
}
