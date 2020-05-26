package Javabasics;

public class leap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int y=1900;



if((y%4==0) && (y%100!=0)||(y%400==0))
{
System.out.println("leap");	
}
else
{
	System.out.println("not leap");
}
}
}