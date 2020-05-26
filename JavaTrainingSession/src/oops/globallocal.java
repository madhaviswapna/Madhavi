package oops;

public class globallocal {
int j=10;
int i =8;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		globallocal k= new globallocal();
		int i =16;
		System.out.println(i);
		System.out.println(k.i);
	}
	public void ghf()
	{
		globallocal j= new globallocal();

int p = j.i;
System.out.println(p);
}
}
