package oops;

public class fiboc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int f1=0;
		int f2=1;
		int f3=1;
		
		
		System.out.print(f1+","+f2);
		

		for(int i=2; i<=7;i++)
		{ 
			 f3=f2+f1;
				System.out.print(","+f3);

			f1=f2;

			f2=f3;
			


		}
		}
	

}
