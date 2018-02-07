package pl.lodz.uni.math;

public class Main {

	public static void main(String[] args) {
		Core ArrayGen = new Core();
		String tempDec = ArrayGen.DecisionLine();
		int tempSize = ArrayGen.EnterArraySize();	
		if(tempDec=="Char") {
			char[] tempCArray = ArrayGen.CreateCharArray(tempSize);
			ArrayGen.PrintCharArray(tempSize, tempCArray);
		}
		else if(tempDec=="Int") {
			int[] tempIArray = ArrayGen.CreateIntArray(tempSize);
			ArrayGen.PrintIntArray(tempSize, tempIArray);
		}
		else {
			System.out.println("It's not working.");
			System.exit(0);
		}
	}

}
