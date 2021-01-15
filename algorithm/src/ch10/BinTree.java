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
}
