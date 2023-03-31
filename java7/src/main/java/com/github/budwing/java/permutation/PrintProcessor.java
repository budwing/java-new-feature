package com.github.budwing.java.permutation;

public class PrintProcessor implements PermutationProcessor {

	public Object process(Object[] a) {
		for (int i = 0; i < a.length; i++) {
			if (i == a.length - 1) {
				System.out.print(a[i]);
			} else {
				System.out.print(a[i] + ", ");
			}
		}
		System.out.println();
		
		return null;
	}

}
