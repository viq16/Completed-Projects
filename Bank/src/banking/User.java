package banking;

import java.util.ArrayList;
import java.util.Date;

public class User {

	private String userName,userSurname,id;
	private Date date;
	private ArrayList<BankAccount> userBankAccounts = new ArrayList<BankAccount>();
	private ArrayList<String> userTransactions = new ArrayList<String>();
	
	public User(String _userName,String _userSurname, Date _date, String _id) {
		this.setUserName(_userName);
		this.setUserSurname(_userSurname);
		this.setDate(_date);
		this.setId(_id);;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<BankAccount> getUserBankAccounts() {
		return userBankAccounts;
	}

	public void setUserBankAccounts(ArrayList<BankAccount> userBankAccounts) {
		this.userBankAccounts = userBankAccounts;
	}
	
	public void addUserBankAccount(BankAccount bankAccount) {
		userBankAccounts.add(bankAccount);
	}

	public ArrayList<String> getUserTransactions() {
		return userTransactions;
	}

	public void AddUserTransaction(String userTransaction) {
		userTransactions.add(userTransaction);
	}
	
	public BankAccount getBankAccByAccNumber(String accNumber) {
		BankAccount bAcc = null;
		for(BankAccount acc: userBankAccounts) {
			if(acc.getAccNumber() == accNumber) {
				bAcc = acc;
			}
		}
		return bAcc;
	}
}
