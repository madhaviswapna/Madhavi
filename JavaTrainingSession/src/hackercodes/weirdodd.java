package hackercodes;
import java.util.HashMap;

public class weirdodd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String b="wierdwierd";
String n="";

char[] hello = b.toCharArray(); 

HashMap<Integer,String> p=new HashMap<Integer,String>();


for(int i=0;i<b.length();i++)
{
	int x=i%2;
	if(x==0)
	
		System.out.println(hello[x]);
	
	int y = i%2;
	 if  (y!=0)
		System.out.println(i);
i++;
	
}	}

}
