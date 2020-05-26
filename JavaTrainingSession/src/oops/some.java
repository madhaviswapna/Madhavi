package oops;

public class some {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num=321;
		int arm=1;
		int p;
		int armstrong=0;
		
		while(num!=0)
			
		{
			p=num%10;
			armstrong=armstrong+(p*p*p);					
			num=num/10;	
			
			
		}
		
		System.out.println(armstrong);
		
	}

}
//	rev=rev+p*p*p;
