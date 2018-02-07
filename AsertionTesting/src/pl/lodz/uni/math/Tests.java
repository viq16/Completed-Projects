package pl.lodz.uni.math;
import org.junit.Assert;
import org.junit.Test;
public class Tests {
	@Test
	public void DecisionTest() {
		Core ArrayGen = new Core();
		String tempDec = ArrayGen.DecisionLine();
		assert tempDec == "Char" && tempDec=="Int" : "Incorrect digit";
	}
	@Test
	public void EnterSizeTest() {
		Core ArrayGen = new Core();
		int tempSize = ArrayGen.EnterArraySize();
		Assert.assertTrue(tempSize==(int)tempSize);
	}
	@Test
	public void CheckArray() {
		Core ArrayGen = new Core();
		char[] tempCArray = ArrayGen.CreateCharArray(5);
		Assert.assertEquals(tempCArray[0], 'a');
	}
	@Test
	public void CheckArrayElement() {
		Core ArrayGen = new Core();
		char[] tempCArray = ArrayGen.CreateCharArray(5);
		Assert.assertNull(tempCArray[4]);
	}
	@Test
	public void CheckTwoArrays() {
		Core ArrayGen = new Core();
		char[] tempCArray = ArrayGen.CreateCharArray(5);
		int[] tempIArray = ArrayGen.CreateIntArray(5);
		Assert.assertSame(tempCArray, tempIArray);
	}
	@Test
	public void Error() {
		Assert.fail("Fail error");
	}
}
