package stringsssss;

public class strings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str2="the string sunrises";
		String str="the string sunriseS";
		String date="10-10-1990";
		String h="hello";
		String o="world";
		int hi = 80;
		int j = 70;
		StringBuffer buff=new StringBuffer(str);
buff.reverse();
//System.out.println(str.indexOf('s'));
//System.out.println(str.indexOf('s',str.indexOf('s')+1));
//
//System.out.println(str.indexOf('s',str.indexOf('s',str.indexOf('s')+1)+1));


//System.out.println(str.indexOf('s',str.indexOf('s',str.indexOf('s',str.indexOf('s')+1)+1)+1));
System.out.println(str.indexOf('s',str.indexOf('s',str.indexOf('s',str.indexOf('s',str.indexOf('s',str.indexOf('s')+1)+1)+1)+1)));

//System.out.println(str.charAt(1));
//System.out.println(str.length());

System.out.println(str.equals(str2));
System.out.println(str.equalsIgnoreCase(str2));
System.out.println(str.substring(0,2));
System.out.println(str.trim());
System.out.println(str.replace("s","fer"));
System.out.println(date.replace("-","/"));
System.out.println("$$$$");
   String val[]= str.split(" ");
   for(int i=0;i<val.length;i++)
   {
	 System.out.println(val[i]);  
	   
   }
   
   System.out.println(buff);
   System.out.println(str2.concat(str));
   System.out.println(h+(hi+j));
   
	}

}
                                                                