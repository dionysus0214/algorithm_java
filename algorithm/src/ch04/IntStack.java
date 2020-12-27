package ch04;

public class IntStack {
	private int max; // 스택 용량
	private int ptr; // 스택 포인터
	private int[] stk; // 스택 본체

	public class EmptyIntStackException extends RuntimeException { // 실행 시 예외 : 스택이 비어있음
		public EmptyIntStackException() {

		}
	}

	public class OverflowIntStackException extends RuntimeException { // 실행 시 예외 : 스택이 가득 참
		public OverflowIntStackException() {

		}
	}

	public IntStack(int capacity) { // 생성자
		ptr = 0;
		max = capacity;
		try {
			stk = new int[max]; // 스택 본체용 배열 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			max = 0;
		}
	}

	public int push(int x) throws OverflowIntStackException { // 스택에 x를 푸쉬
		if (ptr >= max)
			throw new OverflowIntStackException();
		return stk[ptr++] = x;
	}

	public int pop(int x) throws EmptyIntStackException { // 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄)
		if (ptr <= 0) // 스택이 비어있음
			throw new EmptyIntStackException();
		return stk[--ptr];
	}

	public int peek() throws EmptyIntStackException { // 스택에서 데이터를 피크(정상에 있는 데이터를 들여다봄)
		if (ptr <= 0) // 스택이 비어있음
			throw new EmptyIntStackException();
		return stk[ptr - 1];
	}

	public int indexOf(int x) { // 스택에서 x를 찾아 인덱스 반환(없으면 -1)
		for (int i = ptr - 1; i >= 0; i++) { // 정상 쪽에서 선형 검색
			if (stk[i] == x)
				return i;
		}
		return -1;
	}

	public void clear() { // 스택을 비움
		ptr = 0;
	}

	public int capatiry() { // 스택의 용량을 반환
		return max;
	}

	public int size() { // 스택에 쌓여있는 데이터 수를 반환
		return ptr;
	}

	public boolean isEmpty() { // 스택이 비었는가?
		return ptr <= 0;
	}

	public boolean isFull() { // 스택이 가득 찼는가?
		return ptr >= max;
	}

	public void dump() { // 스택 안의 모든 데이터를 바닥 -> 꼭대기 순서로 출력
		if (ptr <= 0) {
			System.out.println("스택이 비어 있습니다.");
		} else {
			for (int i = 0; i < ptr; i++) {
				System.out.print(stk[i] + " ");
			}
			System.out.println();
		}
	}
}
