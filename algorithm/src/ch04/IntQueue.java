package ch04;

public class IntQueue {
	private int max;
	private int front;
	private int rear;
	private int num;
	private int[] que;
	
	public class EmptyIntQueueException extends RuntimeException { // 실행 시 예외 : 큐가 비어 있음
		public EmptyIntQueueException() {
			
		}
	}
	
	public class OverflowIntQueueException extends RuntimeException { // 실행 시 예외 : 큐가 가득 참
		public OverflowIntQueueException() {
			
		}
	}
	
	public IntQueue(int capacity) { // 생성자
		num = front = rear = 0;
		max = capacity;
		try {
			que = new int[max];
		} catch (OutOfMemoryError e) {
			max = 0;
		}
	}
	
	public int enque(int x) throws OverflowIntQueueException { // 큐에 데이터를 인큐
		if (num >= max)
			throw new OverflowIntQueueException(); // 큐가 가득 참
		que[rear++] = x;
		num++;
		if (rear == max)
			rear = 0;
		return x;
	}
	
	public int deque() throws EmptyIntQueueException { // 큐에서 데이터를 디큐
		if (num <= 0) 
			throw new EmptyIntQueueException(); // 큐가 비어있음
		int x = que[front++];
		num--;
		if (front == max) 
			front = 0;
		return x;
	}
	
	public int peek() throws EmptyIntQueueException { // 큐에서 데이터를 피크
		if (num <= 0)
			throw new EmptyIntQueueException();
		return que[front];
	}
	
	public int indexOf(int x) { // 큐에서 x를 검색하면 인덱스를 반환(찾지 못하면 -1)
		for (int i = 0; i < num; i++) {
			int idx = (i + front) % max;
			if (que[idx] == x)
				return idx;
		}
		return -1;
	}
	
	public void clear() { // 큐를 비움
		num = front = rear = 0;
	}
	
	public int capacity() {  // 큐의 용량을 반환
		return max;
	}
	
	public int size() { // 큐에 쌓여있는 데이터 수를 반환
		return num;
	}
	
	public boolean isEmpty() {
		return num <= 0;
	}
	
	public boolean isFull() {
		return num >= max;
	}
	
	public void dump() { // 큐 안의 모든 데이터를 프런트 -> 리어 순으로 출력
		if (num <= 0)
			System.out.println("큐가 비어있습니다.");
		else {
			for (int i = 0; i < num; i++) 
				System.out.print(que[(i + front) % max] + " ");
			System.out.println();
		}
	}
}
