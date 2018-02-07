package banking;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class BankingMain {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		BankingTransactions Transactions = new BankingTransactions();
		Bank mBank=new Bank("mBank","MBANKPL1");
		mBank.createUser("Janusz", "Tracz", sdf.parse("22/01/1990"), "JT1");
		mBank.createBankAccountForUser("JT1", "1234567812345678", "Main Account");
		mBank.createUser("Karol", "Matiz", sdf.parse("22/01/1989"), "KM1");
		mBank.createBankAccountForUser("KM1", "2345678923456789", "Main Account");
		//mBank.getBankAccountByUserIdAndAccNumber("KM1", "12345678").setAccNumber("12121212124317");
		Transactions.makeDepositByAccNumber(mBank, 700, "2345678923456789", "First payment");
		Transactions.makeDepositByAccNumber(mBank, 700, "2345678923456789", "Second payment");
		Transactions.makeDepositByAccNumber(mBank, 700, "1234567812345678", "First payment");
		Transactions.makeDepositByAccNumber(mBank, 700, "1234567812345678", "Second payment");
		
		System.out.println("Status for Karol Matiz Account No. 2345678923456789 : " + mBank.getBankAccountByAccNumber("2345678923456789").getAccBalance());
		System.out.println("Status for Janusz Tracz Account No. 1234567812345678 : " + mBank.getBankAccountByAccNumber("1234567812345678").getAccBalance());

		
		Transactions.makeWithdrawByAccNumber(mBank, 200, "2345678923456789", "Shopping");
		
		mBank.showUserTransactions("KM1");
		Transactions.makeBankTransferByAccNumber(mBank, 1000, "2345678923456789", "Payment for sofa", "1234567812345678");
		
		mBank.showBankTransactions();
		
		System.out.println("Status for Karol Matiz Account No. 2345678923456789 : " + mBank.getBankAccountByAccNumber("2345678923456789").getAccBalance());
		System.out.println("Status for Janusz Tracz Account No. 1234567812345678 : " + mBank.getBankAccountByAccNumber("1234567812345678").getAccBalance());

		
		Bank bZWBK = new Bank("BZWBK","BZWBKPL1");
		bZWBK.createUser("Leon", "Polak", sdf.parse("22/01/1977"), "LP1");
		bZWBK.createBankAccountForUser("LP1", "1234567812345679", "Main Account");
		bZWBK.createUser("Maria", "Rokita", sdf.parse("22/01/1979"), "MR1");
		bZWBK.createBankAccountForUser("LP1", "2345678023456780", "Main Account");
		Transactions.makeDepositByAccNumber(bZWBK, 1000, "1234567812345679", "First payment");
		Transactions.makeDepositByAccNumber(bZWBK, 1000, "1234567812345679", "For new sofa");
		Transactions.makeDepositByAccNumber(bZWBK, 1000, "2345678023456780", "First payment");

		System.out.println("Status for Leon Polak Account No. 1234567812345679 : " + bZWBK.getBankAccountByAccNumber("1234567812345679").getAccBalance());

		Transactions.makeInternationalBankTransferByAccNumberAndSwift(bZWBK, mBank, "MBANKPL1", 1000, "1234567812345679", "Buy some new clothes", "2345678923456789");
		
		mBank.showBankTransactions();
		bZWBK.showBankTransactions();
		
		System.out.println("Status for Karol Matiz Account No. 2345678923456789 : " + mBank.getBankAccountByAccNumber("2345678923456789").getAccBalance());
		System.out.println("Status for Leon Polak Account No. 1234567812345679 : " + bZWBK.getBankAccountByAccNumber("1234567812345679").getAccBalance());
		
		mBank.showUserTransactions("KM1");
		bZWBK.showUserTransactions("LP1");

		//PrintWriter fileBank = new PrintWriter("Account Transactions.txt", true);
		PrintWriter fileBank = new PrintWriter(new FileWriter("Account Transactions.txt", true));

		fileBank.println(bZWBK.getBankTransactions());
		fileBank.println(mBank.getBankTransactions());
		fileBank.flush();
		fileBank.close();
	}

}
