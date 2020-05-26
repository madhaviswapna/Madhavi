package oops;

public class array1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int[] arr = {5,7,8,9};
int total=0;
  for(int i=0;i<= arr.length-1;i++)
  {
total=total+arr[i];


	}
System.out.println(total);
int avg=total/arr.length;

System.out.println(avg);


}
}