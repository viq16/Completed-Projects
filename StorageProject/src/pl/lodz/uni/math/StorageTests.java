package pl.lodz.uni.math;

import org.junit.Assert;
import org.junit.Test;

public class StorageTests {

	@Test
	public void createCube() {
		int x,y,z;
		x=3;
		y=3;
		z=4;
		Qube qube= Qube.getInstance(x,y,z);
		Assert.assertEquals(3, qube.getX());
		Assert.assertEquals(3, qube.getY());
		Assert.assertEquals(4, qube.getZ());
	}
	@Test
	public void identifyPackages() {
		Package p1=new Package("1",1,"TOYS");
		Package p2=new Package("2",2,"FOOD");
		
		Assert.assertEquals("1", p1.getNumber());
		Assert.assertEquals("2", p2.getNumber());
		Assert.assertEquals(1, p1.getPriority());
		Assert.assertEquals(2, p2.getPriority());
		Assert.assertEquals("TOYS", p1.type.toString());
		Assert.assertEquals("FOOD", p2.type.toString());
	}
	@Test
	public void testOutput() {
		int x,y,z;
		x=3;
		y=3;
		z=4;
		Qube qube= Qube.getInstance(x,y,z);
		Package p1=new Package("1",1,"TOYS");
		Package p2=new Package("2",1,"FOOD");
		
		qube.createSlots();
		qube.addPackages(p1,x,y);
		qube.addPackages(p2,x,y);
		
		Assert.assertEquals(true, qube.floor[0][0].size()==1);
		Assert.assertEquals(true, qube.floor[0][1].size()==1);
		Assert.assertEquals(true, qube.floor[0][0].get(0) == p1);
		Assert.assertEquals(true, qube.floor[0][1].get(0) == p2);
	}
	
	@Test
	public void getPackageByNumberTest() {
		int x,y,z;
		x=3;
		y=3;
		z=4;
		Qube qube= Qube.getInstance(x,y,z);
		Package p1=new Package("1",1,"TOYS");
		Package p2=new Package("2",1,"FOOD");
		Package p3=new Package("3",1,"FOOD");
		Package p4=new Package("4",2,"FOOD");
		Package p5=new Package("5",3,"FOOD");
		
		qube.createSlots();
		qube.addPackages(p1,x,y);
		qube.addPackages(p2,x,y);
		qube.addPackages(p3,x,y);
		qube.addPackages(p4,x,y);
		qube.addPackages(p5,x,y);
		

		Assert.assertEquals(p1, qube.getPackageByNumber("1"));
		Assert.assertEquals(true, qube.floor[0][1].get(1) == p5);
		Assert.assertEquals(true, qube.floor[0][2].get(1) == p4);

	}
}
