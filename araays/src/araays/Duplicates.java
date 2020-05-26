package araays;
import java.util.HashSet;
import java.util.Set;
public class Duplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String names[]= {"java", "ton","tom", "tom","c","java"};

for(int i=0;i<names.length;i++)
{
	for(int j=i+1;j<names.length;j++)
	{
		for(int k=j+1;k<names.length;k++)
		if((names[i].equals(names[j]) && (names[j].equals(names[k]))))
		{
			System.out.println(names[i]);
		}
		
	}
		
}
System.out.println("********");
Set<String> store=new HashSet();
for(String name:names)
{
	if(store.add(name)==false)
	{

	System.out.println(name );
}

	
	}
	}}
