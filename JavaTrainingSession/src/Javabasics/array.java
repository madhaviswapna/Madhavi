package Javabasics;

public class array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int i[]=new int[3];
i[0]=6;
i[1]=4;
i[2]=5;

String s[]=new String[4];
s[0]="tom";
s[1]="d";
s[2]="9";
System.out.println(i[2]);
System.out.println(i.length);
System.out.println(s[2]);


for(int j=0;j<i.length;j++)
{
System.out.println(i[j]);

	}

}
}