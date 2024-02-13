package testOraclt;

import java.util.Iterator;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}
	
	public void tree(int arry[]) {
		int temp;
		for (int i = 0; i < arry.length; i++) {
			 for (int j = 1; j < arry.length; j++) {
				if (arry[i]>=arry[j]) {
					temp=arry[i];
					arry[i]= arry[j];
					arry[j]=temp;
				}
			}
		}
	}
	public void testOracl(int a) {
		int arry[]=null;
		Random ra= new Random();
		for (int i = 0; i < a; i++) {
			arry[i]=ra.nextInt();
		}	
		tree(arry);
	}

}
