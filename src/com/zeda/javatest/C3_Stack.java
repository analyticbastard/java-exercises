package com.zeda.javatest;

import java.util.Stack;

public class C3_Stack {
	
	private static class Tower {
		public static final int R = 3;
		public static final int N = 3;
		
		private int index = -1;
		private Stack<Integer> stack = new Stack<Integer>();
		
		public Tower(int index) {
			this.index = index;
		}
		
		public void add(int plateNum) {
			stack.push(plateNum);
		}
		
		public void moveTop(Tower t) {
			Integer i = stack.pop();
			t.add(i);
			
			System.out.println("Moving plate " + i + " from tower " 
					+ this.index + " to tower " + t.index);
		}
		
		public void moveN(int n, Tower dest, Tower buffer) {
			if (n>0) {
				moveN(n-1, buffer, dest);
				moveTop(dest);
				buffer.moveN(n-1, dest, this);
			}
		}
	}

	private static int hanoi() {
		Tower [] tower = new Tower[Tower.R];
		
		for (int i=0; i<Tower.R; i++) {
			tower[i] = new Tower(i);
		}
		
		// Place all plates on first rod
		for (int i=0; i<Tower.N; i++) {
			tower[0].add(i);
		}
		
		tower[0].moveN(Tower.N, tower[2], tower[1]);
		
		return 0;
	}
	
	
	
	private static int sortStacks() {
		Stack<Integer> tosort = new Stack<Integer>();
		Stack<Integer> sorted = new Stack<Integer>();
		
		for (int i=0; i<5; i++) {
			tosort.push((int) (Math.random()*100) );
		}
		
		while (!tosort.isEmpty()) {
			int elem = tosort.pop();
			while (!sorted.isEmpty() && elem < sorted.peek()) {
				tosort.push(sorted.pop());
			}
			sorted.push(elem);
		}
		
		System.out.println(sorted.toString());
		sorted.pop();
		System.out.println(sorted.toString());
		
		return 0;
	}
	
	
	
	public static int test() {
		return hanoi();
		//return sortStacks();
	}
}
