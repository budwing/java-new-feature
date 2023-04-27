package com.github.budwing.java7.permutation;

public class PrintProcessor implements PermutationProcessor {
	private boolean silent;

	public PrintProcessor(boolean silent) {
		this.silent = silent;
	}

	public Object process(Object[] a) {
		if (silent) return null;

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
