package ch09;

import java.util.Comparator;

public class LinkedList<E> {
	class Node<E> { // 노드
		private E data; // 데이터
		private Node<E> next; // 뒤쪽 포인터(다음 노드 참조)
		
		Node(E data, Node<E> next) { // 생성자
			this.data = data;
			this.next = next;
		}
	}
	
	private Node<E> head; // 머리 노드
	private Node<E> crnt; // 선택 노드
	
	public LinkedList() { // 생성자
		head = crnt = null;
	}
	
	public E search(E obj, Comparator<? super E> c) { // 노드 검색
		Node<E> ptr = head; // 현재 스캔 중인 노드
		
		while(ptr != null) {
			if(c.compare(obj, ptr.data) == 0) { // 검색 성공
				crnt = ptr;
				return ptr.data;
			}
			ptr = ptr.next; // 다음 노드를 선택
		}
		return null; // 검색 실패
	}
	
	public void addFirst(E obj) { // 머리에 노드 삽입
		Node<E> ptr = head; // 삽입 전의 머리 노드
		head = crnt = new Node<E>(obj, ptr);
	}
	
	public void addLast(E obj) { // 꼬리에 노드 삽입
		if(head == null) { // 리스트가 비어있으면
			addFirst(obj); // 머리에 삽입
		} else {
			Node<E> ptr = head;
			while(ptr.next != null) {
				ptr = ptr.next;
			}
			ptr.next = crnt = new Node<E>(obj, null);
		}
	}
	
	public void removeFirst() { // 머리 노드를 삭제
		if(head != null) {
			head = crnt = head.next;
		}
	}
	
	public void removeLast() { // 꼬리 노드를 삭제
		if(head != null) {
			if(head.next == null) { // 노드가 하나만 있으면
				removeFirst(); // 머리 노드를 삭제
			} else {
				Node<E> ptr = head; // 스캔 중인 노드
				Node<E> pre = head; // 스캔 중인 노드의 앞쪽 노드
				
				while(ptr.next != null) {
					pre = ptr;
					ptr = ptr.next;
				}
				pre.next = null;
				crnt = pre;
			}
		}
	}
	
	public void remove(Node p) {
		if(head != null) {
			if(p == head) {
				removeFirst();
			} else {
				Node<E> ptr = head;
				
				while(ptr.next != p) {
					ptr = ptr.next;
					if(ptr == null) {
						return;
					}
				}
				ptr.next = p.next;
				crnt = ptr;
			}
		}
	}
	
	public void removeCurrntNode() { // 선택 노드를 삭제
		remove(crnt);
	}
	
	public void clear() { // 모든 노드를 삭제
		while(head != null) { // 노드에 아무 것도 없을 때까지
			removeFirst(); // 머리 노드를 삭제
		}
		crnt = null;
	}
	
	public boolean next() { // 선택 노드를 하나 뒤로 이동
		if(crnt == null || crnt.next == null) {
			return false;
		}
		crnt = crnt.next;
		return true;
	}
}
