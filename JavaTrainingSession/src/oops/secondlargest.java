package oops;
import java.util.Arrays; 

public class secondlargest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

int[] s= {15,8,3,2,1,6};

int temp=0;
int i;

for( i=0;i<s.length-1;i++)
{
	for(int j=i+1;j<s.length-1;j++)

	{
	if(s[i]>s[j])
{
	temp=s[i];
	s[i]=s[j];
	s[j]=temp;

	}
	}}
System.out.println(s[i-2]);

for( i=0;i<s.length-1;i++)
{
	System.out.println(s[i]);

}
	}
}
/*Arrays.sort(s);
for(int i=0;i<s.length;i++)
{
	System.out.println(s[i]);

}*/
	
