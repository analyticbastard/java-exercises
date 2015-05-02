package com.zeda.javatest;

import java.util.Hashtable;

public class C2_LinkedList {
	private static class MyLinkedList<T> {
		public MyLinkedList<T> next = null;
		private T data = null;
		
		public MyLinkedList(T d) {
			data = d;
		}
		
		public T getData() {
			return data;
		}
		
		public void setData(T data) {
			this.data = data;
		}
		
		public MyLinkedList<T> put(T data) {
			MyLinkedList<T> l = new MyLinkedList<T>(data);
			l.next = this;
			return l;
		}
		
		public String toString() {
			String str = this.data.toString() + ",";
			MyLinkedList<T> e = this.next;
			while(e != null && e.data != null) {
				str += e.data.toString();
				str += ",";
				e = e.next;
			}
			
			return str;
		}
	}
	
	private static <T> void removeDups(MyLinkedList<T> ll) {
		Hashtable<T, T> ht = new Hashtable<T, T>();
		
		if (ll == null) {
			return;
		}
		
		MyLinkedList<T> l = ll;
		while(l.next != null) {
			MyLinkedList<T> e = (MyLinkedList<T>) l.next;
			if (ht.contains(e.data)) {
				System.out.println("Rep: " + e.data);
				e = (MyLinkedList<T>) e.next;
				l.next = e;
			} else {
				ht.put(e.getData(), e.getData());
			}
			l = e;
			if (l == null) {
				break;
			}
		}
	}
	
	// Exercise 1
	public static int ex1() {
		double val = 10*Math.random();
		MyLinkedList<Integer> ll = new MyLinkedList<Integer>((int) val);
		for (int i=0; i<9; i++) {
			val = 10*Math.random();
			ll = ll.put((int) val);
		}
		
		System.out.println("Original list: " + ll.toString());
		
		removeDups(ll);
		
		System.out.println("Deduped list: " + ll.toString());
		
		return 0;
	}
	
	public static<T> MyLinkedList<T> nthFromLast(MyLinkedList<T> ll, int n) {
		if (ll == null) {
			return null;
		}
		
		MyLinkedList<T> p1 = ll;
		MyLinkedList<T> p2 = ll;
		
		while (p2 != null && n>0) {
			p2 = p2.next;
			n--;
		}
		
		if (p2 == null) {
			return ll;
		}
		
		while (p2 != null) {
			p2 = p2.next;
			p1 = p1.next;
		}
		
		return p1;
	}
	
	private static int ex2() {
		double val = 100*Math.random();
		MyLinkedList<Integer> ll = new MyLinkedList<Integer>((int) val);
		for (int i=0; i<9; i++) {
			val = 100*Math.random();
			ll = ll.put((int) val);
		}
		
		System.out.println("Original list: " + ll.toString());
		
		int N = 4;
		MyLinkedList<Integer> e = nthFromLast(ll, N);
		
		System.out.println("" + N + "-th element from last: " 
				+ e.data.toString());
		
		return 0;
	}
	
	
	
	private static MyLinkedList<Integer> sum(MyLinkedList<Integer> n1,
			MyLinkedList<Integer> n2, int acc) {
		int val1 = 0;
		int val2 = 0;

		MyLinkedList<Integer> n1n = null;
		MyLinkedList<Integer> n2n = null;
		
		if (n1 != null) {
			val1 = n1.getData().intValue();
			n1n  = n1.next;
		}
		
		if (n2 != null) {
			val2 = n2.getData().intValue();
			n2n  = n2.next;
		}
		
		int val = val1 + val2 + acc;
		
		if (val == 0) {
			return null;
		}
		
		acc = 0;
		if (val > 9) {
			val = val - 10;
			acc = 1;
		}
		
		MyLinkedList<Integer> total = new MyLinkedList<Integer>(val);
		MyLinkedList<Integer> next = sum(n1n, n2n, acc);
		
		total.next = next;
		
		return total;
	}
	
	private static int ex3() {
		MyLinkedList<Integer> n1 = new MyLinkedList<Integer>(5);
		n1 = n1.put(1);
		n1 = n1.put(3);
		MyLinkedList<Integer> n2 = new MyLinkedList<Integer>(4);
		n2 = n2.put(9);
		n2 = n2.put(5);
		
		MyLinkedList<Integer> total = sum(n1, n2, 0);
		
		System.out.println("Original list 1: " + n1.toString());
		System.out.println("Original list 2: " + n2.toString());
		
		System.out.println("Total: " + total);
		
		return 0;
	}
	
	
	private static MyLinkedList<Integer> checkForLoops(MyLinkedList<Integer> ll) {
		MyLinkedList<Integer> p1 = ll;
		MyLinkedList<Integer> p2 = ll;
		
		int n1 = 0, n2 = 0;
		
		while (p2.next != null) {
			while (p1 != p2) {
				p1 = p1.next;
				n1++;
			}
			
			if (n1 != n2) {
				return p1;
			}
			
			p2 = p2.next;
			n2++;
			n1 = 0;
			p1 = ll;
		}
		
		return null;
	}
	
	private static int ex4() {
		// 5-4-3-2-1-4
		MyLinkedList<Integer> ll = new MyLinkedList<Integer>(1);
		MyLinkedList<Integer> l = ll;
		ll = ll.put(2);
		ll = ll.put(3);
		ll = ll.put(4);
		ll = ll.put(5);
		l.next = ll.next;
		
		MyLinkedList<Integer> dup = checkForLoops(ll);
		
		System.out.println(dup.data.toString());
		
		
		return 0;
	}
	
	
	/**
	 * TEST FUNCTION
	 * @return
	 */
	public static int test() {
		return ex4();
	}
}
