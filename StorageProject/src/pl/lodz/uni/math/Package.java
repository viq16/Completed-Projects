package pl.lodz.uni.math;

import java.util.Date;

public class Package {
	private int priority,  movesCount;
	private String number;
	typeOfPackage type;
	private Date data;
	public enum typeOfPackage{
		TOYS,CARPARTS,GUNS,FOOD
	}

	Package (String _number,int _priority, String _type)
	{

		this.type=typeOfPackage.valueOf(_type);
		this.setPriority(_priority);
		this.setNumber(_number);
		setMovesCount(0);
		setData(new Date());
	}
	
	public void setMovesCount(){
		movesCount++;
	}

	public int getMovesCount() {
		return movesCount;
	}

	public void setMovesCount(int movesCount) {
		this.movesCount = movesCount;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
