package Javabasics;

public class swapwithout3rd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String a="hello1";
String b="world";
a=a+b;
//System.out.println("a is "+a.substring(0,5));
//System.out.println("b is "+a.substring(5,10));
//System.out.println("*******");
b=(a.replace(b,""));
a=(a.replace(b,""));


System.out.println(a);
System.out.println(b);



int f=405;
int g=56;
f=f+g;
g=f-g;
System.out.println("g is "+g);
f=f-g;
System.out.println("f is "+f);

	}

}