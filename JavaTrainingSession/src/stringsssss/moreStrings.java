package stringsssss;
import java.util.Comparator; 
import java.util.List; 
import java.util.Arrays; 


public class moreStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//this is $100 
		
		String str ="this payments $$100";
		String str1= "this Payments $100";
		String s="ab";
		String k="ba";
		
		int p;
		System.out.println(str.indexOf('$'));
		 p = str.indexOf('$');
		System.out.println(str.charAt(p));
		System.out.println(str.toUpperCase());
		System.out.println(str.substring(7));
		System.out.println(str.compareTo(str1));
		System.out.println(str.compareToIgnoreCase(str1));
		
		System.out.println("ait");
int l= str.compareTo(str1);
if(l==0)
{
	System.out.println("two string same");
	
}
else 
	System.out.println("two string not same");

System.out.println(s.contains(k));


	}
}
