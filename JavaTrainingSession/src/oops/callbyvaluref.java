package oops;

public class callbyvaluref {
int p;
int q;
int t;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		callbyvaluref obj= new callbyvaluref();
		obj.sum(55,99);
		obj.p=8;
		obj.q=6;
		obj.swap(obj);
	}
	
	public void sum(int a,int b){
	
		//int a=30;
		//int b=80;
		int d=a+b;
System.out.println(d);
	}

	
	public void swap(callbyvaluref t){
	int temp;
	       temp=t.p;
		t.p=t.q;
		t.q=temp;
System.out.println(p);
System.out.println(q);

}
}
