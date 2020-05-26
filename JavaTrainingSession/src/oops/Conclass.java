package oops;

public class Conclass {
	
	public Conclass()
	{
		System.out.println("default");
	}
	
	public Conclass(int x)
	{
		System.out.println("one");

	}
	public Conclass(int x, int y)
	{
		System.out.println("two");

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conclass obj=new Conclass();
		Conclass Obj1=new Conclass(8);


	}

}
