package pl.lodz.uni.math;

import java.util.List;

import pl.lodz.uni.math.Package.typeOfPackage;

import java.util.ArrayList;

public class Qube {

	private int x,y,z;
	private int newCordX,newCordY;
	List <Package> [][] floor;
	
	ArrayList <Package> listPackage = new ArrayList<Package>();
	ArrayList <String> movesHistory = new ArrayList<String>();
	
	private static Qube INSTANCE = null;
	
	protected Qube (int _x,int _y,int _z){
		this.x=_x;
		this.y=_y;
		this.z=_z;
	}
 
    public static Qube getInstance(int _x,int _y,int _z){
        if(INSTANCE==null)
            INSTANCE = new Qube(_x,_y,_z);
        return INSTANCE;
    }
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getZ(){
		return z;
	}
	
	public void createSlots(){ //stworzyc listy(stosy) dla kazdego pola w tablicy (ZROBIONE)
		floor = new List[x][y];
		for (int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				floor[i][j]= (new ArrayList <Package>());
			}
		}
		
	}
	
	public void addPackages(Package p,int x, int y)
	{
		int prior=0,maxprior=0,licznik=x-1;
		boolean isAdded = false;
		for (int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				isAdded = addIfEmpty(i, j, p, prior, maxprior);
				listPackage.add(p);
				if(isAdded == true)
				{
					break;
				}
			}
			if(isAdded == true)
			{
				break;
			}
			if (licznik == i)
			{
				for(int h=0;h<x;h++)
				{
					for (int k=0;k<y;k++)
					{
						if(floor[h][k].isEmpty() == true)
						{
							floor[h][k].add(p);
							break;
						}
					}
				}
			}
		}
	}
	
	public void movePackages(Package p,int x, int y,int xAvoid,int yAvoid)
	{
		int prior=0,maxprior=0,licznik=x-1;
		boolean isAdded = false;
		for (int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				if(i == xAvoid && j == yAvoid) {
					
				}
				else 
				{
					isAdded = addIfEmpty(i, j, p, prior, maxprior);
					if(isAdded == true)
					{
						newCordX=i;
						newCordY=j;
						break;
					}
				}
			}
			if(isAdded == true)
			{
				p.setMovesCount();
				break;
			}
			if (licznik == i)
			{
				for(int h=0;h<x;h++)
				{
					for (int k=0;k<y;k++)
					{
						if(floor[h][k].isEmpty() == true)
						{
							floor[h][k].add(p);
							p.setMovesCount();
							newCordX=h;
							newCordY=k;
							break;
						}
					}
				}
			}
		}
	}
	
	private int[] goThruAllSlots(int x,int y,String packageNumber) {
		int x1=0,y1=0;
		for (int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				for(int listItems=0;listItems<floor[i][j].size();listItems++)
				{
					if (floor[i][j].get(listItems).getNumber() == packageNumber)
					{
						x1=i;
						y1=j;
						break;
					}
				}
		}
	}
		return new int[] {x1,y1};
	}
	
	private boolean ifOnlyPackage(int [] miejsce) {
		if (floor[miejsce[0]][miejsce[1]].size() == 1) {
		return true;
		}else
		{
			return false;
		}
	}
	
	public void showPackages ()
	{
		int iterator=0;
		for (int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				System.out.println("Miejsce "+ i +" "+ j +":");
				for(int listItems=0;listItems<floor[i][j].size();listItems++)
				{
					System.out.println(floor[i][j].get(listItems)+ " ");
					iterator ++;
				}
				System.out.println("Ilosc paczek w liscie" + iterator);
				iterator=0;
			}
			System.out.println("\n");	
		}
	}
	
	public Package getPackageByNumber(String packageNumber) {
		Package p = null;
		int slotOfPackage =0, listSize=0;
		int [] miejsce = goThruAllSlots(x,y,packageNumber);
		boolean ifOnly1 = ifOnlyPackage(miejsce);
		listSize = floor[miejsce[0]][miejsce[1]].size();
		for(int listItems=0;listItems<listSize;listItems++) {
			if (floor[miejsce[0]][miejsce[1]].get(listItems).getNumber() == packageNumber) {
				slotOfPackage = listItems;
			}
		}
			if(ifOnly1 == true) {
					p = floor[miejsce[0]][miejsce[1]].get(slotOfPackage);
			}
			else
			{
				for(int getToPackage=listSize-1;getToPackage>slotOfPackage;getToPackage--) {
					//-------------------------
					movePackageToGetPackage(miejsce,getToPackage,x,y );
					p = floor[miejsce[0]][miejsce[1]].get(getToPackage-1);
				}
			}
		return p;
	}
	
	private boolean addIfEmpty(int i, int j, Package p, int zPrior, int zMaxPrior) {
		boolean add =false;
		if(floor[i][j].isEmpty() == true && p.getPriority() == 1)
		{
			floor[i][j].add(p);
			add =true;
		}
		else if (floor[i][j].isEmpty() == false)
		{
			for(int listItems=0;listItems<floor[i][j].size();listItems++)
			{
				zPrior = floor[i][j].get(listItems).getPriority();
				if (zPrior > zMaxPrior)
				{
					zMaxPrior = zPrior;
				}
			}
			if (p.getPriority() >zMaxPrior)
			{
				if(add == false) {
				floor[i][j].add(p);
				add=true;
				}
			}
		}
		return add;
	}
	
	private void movePackageToGetPackage(int [] miejsce, int getToPackage, int x, int y )
	{
		Package toMove = floor[miejsce[0]][miejsce[1]].get(getToPackage);
		movePackages(toMove,x,y,miejsce[0],miejsce[1]);
		int [] cords = getNewCoordinates(newCordX,newCordY);
		saveMovesHistory(miejsce[0],miejsce[1],toMove,cords[0],cords[1]);
		floor[miejsce[0]][miejsce[1]].remove(getToPackage);
	}
	
	public void getPackageByType(typeOfPackage typeOfPackages)
	{
		
		for(int i=0; i<listPackage.size(); i++)
		{
			if(listPackage.get(i).type == typeOfPackages)
			{
				System.out.println("Paczka numer :" + listPackage.get(i).getNumber() + listPackage.get(i).type);
			}
		}
	}
	
	private void saveMovesHistory(int x, int y, Package p, int x1, int y1) {
		movesHistory.add("Paczka " + p.getNumber() + " " + x + "," + y + "->" + x1 + "," + y1);
	}
	
	private int[] getNewCoordinates(int x1,int y1) {
		return new int[] {x1,y1};
	}
	
	public void showHistoryOfMoves() {
		for(String s: movesHistory)
		{
			System.out.println(s);
		}
	}
	
	
}



