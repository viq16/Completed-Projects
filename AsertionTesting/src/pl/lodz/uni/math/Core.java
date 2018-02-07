package pl.lodz.uni.math;

import java.util.Scanner;

public class Core {
	public Core() {}
	public String DecisionLine() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Do you want Char or Int Array? [1/2] ");
		int n = reader.nextInt();
		if(n==1) {
			return "Char";
		}
		else if(n==2) {
			return "Int";
		}	
		else {
			return "Over";
		}
	}
	public int EnterArraySize() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a size of an Array: ");
		int n = reader.nextInt();
		reader.close();
		return n;
	}
	public int[] CreateIntArray(int tempSize) {
		int[] DArray = new int[tempSize];
		for(int i=0; i<tempSize; i++)
		{
			DArray[i] = i;
		}
		return DArray;
	}
	public char[] CreateCharArray(int tempSize) {
		char[] CArray = new char[tempSize];
		char temp;
		for(int i=0; i<tempSize; i++)
		{
			temp = (char) (i+97);
			CArray[i] = temp;
		}
		return CArray;
	}
	public void PrintCharArray(int tempSize, char[] CArray) {
		for(int i=0; i<tempSize; i++)
		{
			System.out.println(i+1 + ". " + CArray[i] + "\n");
		}
	}
	public void PrintIntArray(int tempSize, int[] DArray) {
		for(int i=0; i<tempSize; i++)
		{
			System.out.println(i+1 + ". " + DArray[i] + "\n");
		}
	}
	public void showAscii() {
		char character = 'a';
		int ascii = (int) character;
		System.out.println("a w kodzie ASCII to:" + ascii);
	}
}
