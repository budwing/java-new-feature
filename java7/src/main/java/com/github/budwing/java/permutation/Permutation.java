package com.github.budwing.java.permutation;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Permutation<E> {
	protected E[] result;
	protected int pos = 0;
	protected PermutationProcessor processor;

	public void setProcessor(PermutationProcessor processor) {
		this.processor = processor;
	}

	public Permutation() {
		processor = new PrintProcessor(false);
	}

	public Permutation(PermutationProcessor p) {
		processor = p;
	}

	public E[] subArrayExcludeElementBeforeLocation(E[] a, int location) {
		if (a == null || a.length == 0) {
			return a;
		}

		E[] newArray = (E[]) new Object[a.length - location - 1];
		for (int i = location + 1; i < a.length; i++) {
			newArray[i - location - 1] = a[i];
		}

		return newArray;
	}

	/**
	 * generate a sub array which doesn't include element on location.
	 * 
	 * @param a
	 * @param location
	 * @return
	 */
	public E[] subArrayExcludeLocation(E[] a, int location) {
		if (a == null || a.length <= 1) {
			return a;
		}
		E[] newArray = (E[]) (new Object[a.length - 1]);
		for (int i = 0; i < a.length; i++) {
			if (i < location) {
				newArray[i] = a[i];
			} else if (i > location) {
				newArray[i - 1] = a[i];
			}
		}

		return newArray;
	}

	/**
	 * permute an array
	 * @param a
	 */
	public void permute(E[] a) {
		if (a != null && a.length != 0) {
			pos = 0;
			result = (E[]) (new Object[a.length]);
			inPermute(a);
		}
	}

	/**
	 * combine a certain number of elements in an array, the result don't contain duplicate elements.
	 *
	 * @param a
	 * @param num
	 */
	public void combine(E[] a, int num) {
		if (a != null && a.length != 0) {
			pos = 0;
			result = (E[]) (new Object[num]);
			inCombine(a, num);
		}
	}

	/**
	 * select a certain number of elements from an array, the result may contain duplicate elements.
	 * meaning an element can appear in the result more than one time.
	 *
	 * @param a
	 * @param num
	 */
	public void simpleCombine(E[] a, int num) {
		if (a != null && a.length != 0) {
			pos = 0;
			result = (E[]) (new Object[num]);
			inSimpleCombine(a, num);
		}
	}

	protected void inSimpleCombine(E[] a, int num) {
		if (a == null || a.length == 0)	return;

		if(num==pos) {
			try {
				processor.process(result);
			} catch (IOException e) {
				log.error("an error occur while processing the result {}:{} ", result, e.getMessage());
			}
			return;
		}
		for (int i = 0; i < a.length; i++) {
			result[pos] = a[i];
			pos++;
			inSimpleCombine(a, num);
			pos--;

		}
	}

	protected void inCombine(E[] a, int num) {
		if (a == null || a.length < 1 || a.length < num || num == 0) {
			return;
		}

		for (int i = 0; i < a.length; i++) {
			result[pos] = a[i];
			if (num != 1) {
				pos++;
				inCombine(subArrayExcludeElementBeforeLocation(a, i), num - 1);
				pos--;
			} else if (num == 1) {
				try {
					processor.process(result);
				} catch (IOException e) {
					log.error("an error occur while processing the result {}:{} ", result, e.getMessage());
				}
			}
		}
	}

	protected void inPermute(E[] a) {
		if (a == null || a.length < 1) {
			return;
		}
		if (a.length == 1) {
			result[pos] = a[0];
			try {
				processor.process(result);
			} catch (IOException e) {
				log.error("an error occur while processing the result {}:{} ", result, e.getMessage());
			}
			return;
		}
		for (int i = 0; i < a.length; i++) {
			result[pos] = a[i];
			pos++;
			inPermute(subArrayExcludeLocation(a, i));
			pos--;
		}
	}

}
