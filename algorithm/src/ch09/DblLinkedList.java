package ch09;

import java.util.Comparator;

public class DblLinkedList<E> {
	class Node<E> {
		private E data; // 데이터
		private Node<E> prev; // 앞쪽 포인터(앞쪽 노드에 대한 참조)
		private Node<E> next; // 뒤쪽 포인터(다음 노드에 대한 참조)

		Node() {
			data = null;
			prev = next = this;
		}

		Node(E obj, Node<E> prev, Node<E> next) {
			data = obj;
			this.prev = prev;
			this.next = next;
		}
	}

	private Node<E> head; // 머리 노드
	private Node<E> crnt; // 선택 노드

	public DblLinkedList() {
		head = crnt = new Node<E>(); // 더미 노드를 생성
	}

	public boolean isEmpty() {
		return head.next == head;
	}

	public E search(E obj, Comparator<? super E> c) {
		Node<E> ptr = head.next; // 현재 스캔 중인 노드

		while (ptr != head) {
			if (c.compare(obj, ptr.data) == 0) {
				crnt = ptr;
				return ptr.data; // 검색 성공
			}
			ptr = ptr.next; // 다음 노드로 선택
		}
		return null; // 검색 실패
	}

	public void printCurrentNode() {
		if (isEmpty())
			System.out.println("선택 노드가 없습니다.");
		else
			System.out.println(crnt.data);
	}

	public void dump() {
		Node<E> ptr = head.next; // 더미 노드의 다음 노드

		while (ptr != head) {
			System.out.println(ptr.data);
			ptr = ptr.next;
		}
	}

	public void dumpReverse() {
		Node<E> ptr = head.prev; // 더미 노드의 앞쪽 노드

		while (ptr != head) {
			System.out.println(ptr.data);
			ptr = ptr.prev;
		}
	}

	public boolean next() {
		if (isEmpty() || crnt.next == head)
			return false; // 이동할 수 없음
		crnt = crnt.next;
		return true;
	}

	public boolean prev() {
		if (isEmpty() || crnt.prev == head)
			return false; 
		crnt = crnt.prev;
		return true;
	}

	public void add(E obj) {
		Node<E> node = new Node<E>(obj, crnt, crnt.next);
		crnt.next = crnt.next.prev = node;
		crnt = node;
	}

	public void addFirst(E obj) {
		crnt = head; // 더미 노드 head의 바로 뒤에 삽입
		add(obj);
	}

	public void addLast(E obj) {
		crnt = head.prev; // 꼬리 노드 head.prev의 바로 뒤에 삽입
		add(obj);
	}

	public void removeCurrentNode() {
		if (!isEmpty()) {
			crnt.prev.next = crnt.next;
			crnt.next.prev = crnt.prev;
			crnt = crnt.prev;
			if (crnt == head)
				crnt = head.next;
		}
	}

	public void remove(Node p) {
		Node<E> ptr = head.next;

		while (ptr != head) {
			if (ptr == p) { // p를 찾음
				crnt = p;
				removeCurrentNode();
				break;
			}
			ptr = ptr.next;
		}
	}

	public void removeFirst() {
		crnt = head.next; 
		removeCurrentNode();
	}

	public void removeLast() {
		crnt = head.prev; 
		removeCurrentNode();
	}

	public void clear() {
		while (!isEmpty()) // 텅 빌 때까지
			removeFirst(); // 머리 노드를 삭제
	}
}