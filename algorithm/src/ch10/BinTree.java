package ch10;

import java.util.Comparator;

public class BinTree<K, V> {
	static class Node<K, V> {
		private K key;
		private V data;
		private Node<K, V> left;
		private Node<K, V> right;
		
		Node(K key, V data, Node<K, V> left, Node<K, V> right) {
			this.key = key;
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		K getKey() { // 키값을 반환
			return key;
		}
		
		V getValue() { // 데이터를 반환
			return data;
		}
		
		void print() {
			System.out.println(data);
		}
	}
	
	private Node<K, V> root; // 루트
	private Comparator<? super K> comparator = null; // 비교자
	
	public BinTree() {
		root = null;
	}
	
	public BinTree(Comparator<? super K> c) {
		this();
		comparator = c;
	}
	
	private int comp(K key1, K key2) { // 두 키 값을 비교
		return (comparator == null) ? ((Comparable<K>)key1).compareTo(key2) : comparator.compare(key1, key2);
	}
	
	public V search(K key) {
		Node<K, V> p = root;
		
		while(true) {
			if(p == null)
				return null;
			int cond = comp(key, p.getKey());
			if(cond == 0)
				return p.getValue();
			else if(cond < 0)
				p = p.left;
			else
				p = p.right;
		}
	}
	
	public void addNode(Node<K, V> node, K key, V data) {
		int cond = comp(key, node.getKey());
		if(cond == 0)
			return;
		else if(cond < 0) {
			if(node.left == null) 
				node.left = new Node<K, V>(key, data, null, null);
			else
				addNode(node.left, key, data);
		} else {
			if(node.right == null)
				node.right = new Node<K, V>(key, data, null, null);
			else
				addNode(node.right, key, data);
		}
	}
	
	public void add(K key, V data) {
		if(root == null)
			root = new Node<K, V>(key, data, null, null);
		else
			addNode(root, key, data);
	}
	
	public boolean remove(K key) {
		Node<K, V> p = root; // 스캔 중인 노드
		Node<K, V> parent = null; // 스캔 중인 노드의 부모 노드
		boolean isLeftChild = true; // p는 부모이 왼쪽 자식 노드인가
		
		while(true) {
			if(p == null)
				return false;
			int cond = comp(key, p.getKey()); // key와 노드 p의 키값을 비교
			if(cond == 0)
				break;
			else {
				parent = p;
				if(cond < 0) {
					isLeftChild = true;
					p = p.left;
				} else {
					isLeftChild = false;
					p = p.right;
				}
			}
		}
		
		if(p.left == null) { // p에는 왼쪽 자식이 없음
			if(p == root) 
				root = p.right;
			else if(isLeftChild)
				parent.left = p.right; // 부모의 왼쪽 포인터가 오른쪽 자식을 가리킴
			else
				parent.right = p.right; // 부모의 오른쪽 포인터가 오른쪽 자식을 가리킴
		} else if(p.right == null) {
			if(p == root)
				root = p.left;
			else if(isLeftChild) 
				parent.left = p.left;
			else
				parent.right = p.right;
		} else {
			parent = p;
			Node<K, V> left = p.left;
			isLeftChild = true;
			while(left.right != null) {
				parent = left;
				left = left.right;
				isLeftChild =false;
			}
			p.key = left.key;
			p.data = left.data;
			if(isLeftChild)
				parent.left = left.left;
			else
				parent.right = left.left;
		}
		return true;
	}
	
	private void printSubTree(Node node) {
		if(node != null) {
			printSubTree(node.left);		
			System.out.println(node.key + " " + node.data);	
			printSubTree(node.right);					
		}
	}

	public void print() {
		printSubTree(root);
	}
}
