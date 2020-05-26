package oops;

public class revnum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int num=+121;
int rev=0;
int rem;
int temp=num;

if (num<0)
	System.out.println("not fib ");
else
{
while(num!=0)
{
	rem=num%10;
	rev=rev*10+rem;
	num=num/10;
	
}
if(temp==rev)
 System.out.println("fib");
	
else

	System.out.println("not fib ");

}
}
}