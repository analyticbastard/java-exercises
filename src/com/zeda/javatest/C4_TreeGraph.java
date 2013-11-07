package com.zeda.javatest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class C4_TreeGraph {

	private static class Tree<T extends Integer> {
		
		T value;
		
		Tree<T> left;
		Tree<T> right;
		
		public Tree(ArrayList<T> a) {
			
			ArrayList<T> le = new ArrayList<T>();
			ArrayList<T> ri = new ArrayList<T>();
			
			value = a.remove(0);
			
			System.out.println(value);
			if ( ! a.isEmpty() ) {
				for (Iterator<T> it = a.iterator(); it.hasNext(); ) {
					T e = it.next();
					
					if (e < value) {
						le.add(e);
					} else {
						ri.add(e);
					}
					it.remove();
				}
			}
			
			left  = le.isEmpty() ? null : new Tree<T>(le);
			right = ri.isEmpty() ? null : new Tree<T>(ri);
		}
		
		public int maxDepth() {
			int depthleft  = left  == null ? 0 : left.maxDepth();
			int depthright = right == null ? 0 : right.maxDepth();
			
			return 1 + Math.max(depthleft, depthright);
		}
		
		public int minDepth() {
			int depthleft  = left  == null ? 0 : left.minDepth();
			int depthright = right == null ? 0 : right.minDepth();
			
			return 1 + Math.min(depthleft, depthright);
		}
	}
	
	private static int balanced() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(3);
		a.add(2);
		a.add(4);
		a.add(1);
		a.add(0);
		Tree<Integer> t = new Tree<Integer>(a);
		
		System.out.println("MAX - MIN " + (t.maxDepth() - t.minDepth()) );
		return t.maxDepth() - t.minDepth();
	}
	
	
	private static class Graph {
		
		public static class Node {
			int name = Integer.MIN_VALUE;
			
			ArrayList<Node> edges = new ArrayList<Node>();
			
			public Node(int val) {
				name = val;
			}
		}
		
		public ArrayList<Node> nodes = new ArrayList<Node>();
		
		public Graph() {
			Node uno = new Node(1);
			Node dos = new Node(2);
			Node tres = new Node(3);
			Node cuatro = new Node(4);
			
			uno.edges.add(dos);
			uno.edges.add(cuatro);
			dos.edges.add(tres);
			tres.edges.add(cuatro);
			cuatro.edges.add(dos);
			
			nodes.add(uno);
			nodes.add(dos);
			nodes.add(tres);
			nodes.add(cuatro);
		}
		
		public boolean traverse(Node current, Node end, ArrayList<Node> visited) {
			System.out.println("CURRENT: " + current.name);
			if (current.name == end.name) {return true;}
			else {
				visited.add(current);
				
				for (Node n: nodes) {
					if (visited.contains(n)) continue;
					
					if (traverse(n, end, visited)) return true;
				}
				
				return false;
			}
			
		}
	}
	
	public static int traverse() {
		Graph g = new Graph();
		
		ArrayList<Graph.Node> visited = new ArrayList<Graph.Node>();
		
		Graph.Node begin = g.nodes.get(0);
		Graph.Node end = g.nodes.get(3);
		
		return g.traverse(begin, end, visited) ? 1 : 0;
		
	}
	
	
	public static boolean checkSubtree(Tree<Integer> t1, Tree<Integer> t2) {
		if (t1 == null && t2 == null) return true;
		
		if (t1 == null ^ t2 == null) return false;
		
		if (t1.value == t2.value) {
			return checkSubtree(t1.left, t2.left) && checkSubtree(t1.right, t2.right);
		}
		System.out.println("Values don't match: " + t1.value + " " + t2.value);
		
		return false;
	}
	
	public static boolean locateRoot(Tree<Integer> t1, Tree<Integer> t2) {
		if (t1 == null || t2 == null) {
			System.out.println("Could not find root");
			
			return false;
		}
		
		System.out.println("Values: " + t1.value + " " + t2.value);
		if (t1.value == t2.value) {
			if (checkSubtree(t1.left, t2.left) && checkSubtree(t1.right, t2.right)) {
				return true;
			} else {
				return locateRoot(t1.left, t2) || locateRoot(t1.right, t2);
			}
		} else return locateRoot(t1.left, t2) || locateRoot(t1.right, t2);
	}
	
	public static int subtree() {
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		ArrayList<Integer> a2 = new ArrayList<Integer>();
		
		a1.add(3); a1.add(2); a1.add(4); a1.add(1);
		a2.add(6); a2.addAll(a1);
		
		Tree<Integer> t1 = new Tree<Integer>(a1);
		Tree<Integer> t2 = new Tree<Integer>(a2);
		
		boolean issubtree = locateRoot(t2, t1);
		if (issubtree) System.out.println("Is subtree");
		else System.out.println("Is NOT subtree");
		
		return issubtree ? 0:1 ;
	}
	
	
	public static int test() {
		//return balanced();
		//return traverse();
		return subtree();
	}
}
