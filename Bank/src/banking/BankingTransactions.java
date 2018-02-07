package banking;

import java.util.ArrayList;

public class BankingTransactions {
	enum typeOfAction{
		bankDeposit,
		bankTransferTo,
		bankTransferFrom,
		bankWithdraw,
		bankInternationalTransferTo,
		bankInternationalTransferFrom
	}
	
	public BankingTransactions() {}

	public void makeDepositByAccNumber(Bank bank, float deposit, String accNumber,String title) throws Exception {
		float newAccAmountOfMoney = 0;
		int licznik = 1;
		for(BankAccount bankAcc: bank.getBankAccounts()) {
			if(bankAcc.getAccNumber() == accNumber) {
				newAccAmountOfMoney = bankAcc.getAccBalance() + deposit;
				bankAcc.setAccBalance(newAccAmountOfMoney);
				String userName = bank.getUserById(bankAcc.getOwnerId()).getUserName();
				String userSurname = bank.getUserById(bankAcc.getOwnerId()).getUserSurname();
				bankAcc.addAccTransaction(saveTransaction(userName, userSurname,null,null, deposit, title, typeOfAction.bankDeposit));
				bank.getUserById(bankAcc.getOwnerId()).AddUserTransaction(saveTransaction(userName, userSurname,null,null, deposit, title, typeOfAction.bankDeposit));
				bank.addBankTransaction(saveTransaction(userName, userSurname,null,null, deposit, title, typeOfAction.bankDeposit));
				break;
			}
			if(licznik == bank.getBankAccounts().size()) {
				throw new Exception("Account Number for Deposit does not exist");
			}
			licznik++;
		}
		
	}
	
	public void makeBankTransferByAccNumber(Bank bank, float cash, String accNumberSender,String title, String accNumberTaker) throws Exception {
		float newAccAmountOfMoney = 0;
		String userNameSender = "",userSurnameSender= "", bankAccSenderId= "";
		String userNameTaker= "",userSurnameTaker= "", bankAccTakerId= "";
		boolean senderDone = false;
		int licznik = 1;
		for(BankAccount bankAcc: bank.getBankAccounts()) {
			if(bankAcc.getAccNumber() == accNumberSender) {
				if(bankAcc.getAccBalance() > cash) {
				newAccAmountOfMoney = bankAcc.getAccBalance() - cash;
				bankAcc.setAccBalance(newAccAmountOfMoney);
				senderDone = true;
				}else {
					throw new Exception("Not enough money");
				}
				userNameSender = bank.getUserById(bankAcc.getOwnerId()).getUserName();
				userSurnameSender = bank.getUserById(bankAcc.getOwnerId()).getUserSurname();
				bankAccSenderId = bankAcc.getOwnerId();
				break;
			}
			if(licznik == bank.getBankAccounts().size()) {
				throw new Exception("Account Number Sender does not exist");
			}
			licznik++;
		}
		licznik = 1;
		for(BankAccount bankAcc: bank.getBankAccounts()) {
			if(bankAcc.getAccNumber() == accNumberTaker) {
				if(senderDone == true) {
					newAccAmountOfMoney = bankAcc.getAccBalance() + cash;
					bankAcc.setAccBalance(newAccAmountOfMoney);
				}
				userNameTaker = bank.getUserById(bankAcc.getOwnerId()).getUserName();
				userSurnameTaker = bank.getUserById(bankAcc.getOwnerId()).getUserSurname();
				bankAccTakerId = bankAcc.getOwnerId();
				break;
			}
			if(licznik == bank.getBankAccounts().size()) {
				throw new Exception("Account Number Taker does not exist");
			}
			licznik++;
		}
		bank.addBankTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankTransferTo));
		bank.addBankTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankTransferFrom));
		bank.getUserById(bankAccSenderId).AddUserTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankTransferTo));
		bank.getUserById(bankAccTakerId).AddUserTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankTransferFrom));
		bank.getUserById(bankAccSenderId).getBankAccByAccNumber(accNumberSender).addAccTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankTransferTo));
		bank.getUserById(bankAccTakerId).getBankAccByAccNumber(accNumberTaker).addAccTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankTransferFrom));
	}
	
	public void makeWithdrawByAccNumber(Bank bank, float withdraw, String accNumber,String title) throws Exception {
		float newAccAmountOfMoney = 0;
		int licznik = 1;
		for(BankAccount bankAcc: bank.getBankAccounts()) {
			if(bankAcc.getAccNumber() == accNumber) {
				if(bankAcc.getAccBalance()>withdraw) {
					newAccAmountOfMoney = bankAcc.getAccBalance() - withdraw;
					bankAcc.setAccBalance(newAccAmountOfMoney);
					String userName = bank.getUserById(bankAcc.getOwnerId()).getUserName();
					String userSurname = bank.getUserById(bankAcc.getOwnerId()).getUserSurname();
					bankAcc.addAccTransaction(saveTransaction(userName, userSurname,null,null, withdraw, title, typeOfAction.bankWithdraw));
					bank.getUserById(bankAcc.getOwnerId()).AddUserTransaction(saveTransaction(userName, userSurname,null,null, withdraw, title, typeOfAction.bankWithdraw));
					bank.addBankTransaction(saveTransaction(userName, userSurname,null,null, withdraw, title, typeOfAction.bankWithdraw));
					break;
				}else {
					throw new Exception("Not enough money");
				}
			}
			if(licznik == bank.getBankAccounts().size()) {
				throw new Exception("Account Number for withdraw does not exist");
			}
			licznik++;
		}
	}
	
	public void makeInternationalBankTransferByAccNumberAndSwift(Bank bankSender,Bank bankTaker,String swiftTaker, float cash, String accNumberSender,String title, String accNumberTaker) throws Exception {
		float newAccAmountOfMoney = 0;
		int licznik = 1;
		String userNameSender = "",userSurnameSender= "", bankAccSenderId= "";
		String userNameTaker= "",userSurnameTaker= "", bankAccTakerId= "";
		boolean senderDone = false;
		for(BankAccount bankAcc: bankSender.getBankAccounts()) {
			if(bankAcc.getAccNumber() == accNumberSender) {
				if(bankAcc.getAccBalance() > cash) {
					if(swiftTaker == bankTaker.getSwift()) {
						newAccAmountOfMoney = bankAcc.getAccBalance() - cash;
						bankAcc.setAccBalance(newAccAmountOfMoney);
						senderDone = true;
					}else {
						throw new Exception("Bad SWIFT code");
					}
				}else {
					throw new Exception("Not enough money");
				}
				userNameSender = bankSender.getUserById(bankAcc.getOwnerId()).getUserName();
				userSurnameSender = bankSender.getUserById(bankAcc.getOwnerId()).getUserSurname();
				bankAccSenderId = bankAcc.getOwnerId();
				break;
			}
			if(licznik == bankSender.getBankAccounts().size()) {
				throw new Exception("Account Number Sender does not exist");
			}
			licznik++;
		}
		licznik = 1;
		for(BankAccount bankAcc: bankTaker.getBankAccounts()) {
			if(bankAcc.getAccNumber() == accNumberTaker) {
				if(senderDone == true && bankTaker.getSwift() == swiftTaker) {
					newAccAmountOfMoney = bankAcc.getAccBalance() + cash;
					bankAcc.setAccBalance(newAccAmountOfMoney);
				}else {
					throw new Exception("Bad SWIFT code");
				}
				userNameTaker = bankTaker.getUserById(bankAcc.getOwnerId()).getUserName();
				userSurnameTaker = bankTaker.getUserById(bankAcc.getOwnerId()).getUserSurname();
				bankAccTakerId = bankAcc.getOwnerId();
				break;
			}
			if(licznik == bankTaker.getBankAccounts().size()) {
				throw new Exception("Account Number Taker does not exist");
			}
			licznik++;
		}
		bankSender.addBankTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankInternationalTransferTo));
		bankTaker.addBankTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankInternationalTransferFrom));
		bankSender.getUserById(bankAccSenderId).AddUserTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankInternationalTransferTo));
		bankTaker.getUserById(bankAccTakerId).AddUserTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankInternationalTransferFrom));
		bankSender.getUserById(bankAccSenderId).getBankAccByAccNumber(accNumberSender).addAccTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankInternationalTransferTo));
		bankTaker.getUserById(bankAccTakerId).getBankAccByAccNumber(accNumberTaker).addAccTransaction(saveTransaction(userNameSender, userSurnameSender,userNameTaker,userSurnameTaker, cash, title, typeOfAction.bankInternationalTransferFrom));
	}
	
	private String saveTransaction(String userNameSender, String userSurnameSender,String userNameTaker, String userSurnameTaker,float deposit, String title, typeOfAction type) {
		String message="";
		if(type == typeOfAction.bankDeposit) {
			message="User " +userNameSender+ " "+ userSurnameSender + " made a deposit " + deposit + " with title '" + title + "'";
		}else if(type == typeOfAction.bankTransferTo) {
			message="User " +userNameSender+ " "+ userSurnameSender + " made a transfer of " + deposit + " with title '"+title+ "' to user " +userNameTaker+ " "+ userSurnameTaker;
		}else if(type == typeOfAction.bankTransferFrom) {
			message="User " +userNameTaker+ " "+ userSurnameTaker + " took a transfer of " + deposit + " with title '"+title+ "' from user " +userNameSender+ " "+ userSurnameSender;
		}else if(type == typeOfAction.bankWithdraw) {
			message="User " +userNameSender+ " "+ userSurnameSender + " made a withdraw " + deposit + " with title '" + title+ "'";
		}else if(type == typeOfAction.bankInternationalTransferTo) {
			message="User " +userNameSender+ " "+ userSurnameSender + " made an interantional transfer of " + deposit + " with title '"+title+ "' to user " +userNameTaker+ " "+ userSurnameTaker;
		}else if(type == typeOfAction.bankInternationalTransferFrom) {
			message="User " +userNameTaker+ " "+ userSurnameTaker + " took an interantional transfer of " + deposit+ " with title '"+ title + "' from user " + userNameSender +" "+ userSurnameSender;
		}
		return message;
		
	}
	
}
