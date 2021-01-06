package ch07;

public class IntSet {
	private int max;
	private int num;
	private int[] set;
	
	public IntSet(int capacity) { // 생성자
		num = 0;
		max = capacity;
		try {
			set = new int[max];
		} catch(OutOfMemoryError e) {
			max = 0;
		}
	}
	
	public int capacity() { // 집합의 최대 개수
		return max;
	}
	
	public int size() { // 집합의 요소 개수
		return num;
	}
	
	public int indexOf(int n) { // 집합에서 n을 검색(index 반환)
		for(int i = 0; i < num; i++) 
			if(set[i] == n)
				return i;
		return -1;		
	}
	
	public boolean contains(int n) { // 집합에 n이 있는지 여부 확인
		return (indexOf(n) != -1) ? true : false;
	}
	
	public boolean add(int n) { // 집합에 n 추가
		if(num >= max || contains(n) == true) // 가득 찼거나 이미 n이 존재
			return false;
		else
			set[num++] = n;  // 가장 마지막 자리에 추가
		return true;
	}
	
	public boolean remove(int n) { // 집합에서 n 삭제
		int idx;
		
		if(num <= 0 || (idx = indexOf(n)) == -1) // 비어있거나 n이 존재하지 않음
			return false;
		else {
			set[idx] = set[--num]; // 마지막 요소를 삭제한 곳으로 옮김
			return true;
		}
	}
	
	public void copyTo(IntSet s) { // 집합 s에 복사
		int n = (s.max < num) ? s.max : num;
		for(int i = 0; i < n; i++)
			s.set[i] = set[i];
		s.num = n;
	}
	
	public void copyFrom(IntSet s) { // 집합 s를 복사
		int n = (max < s.num) ? max : s.num;
		for(int i = 0; i < n; i++)
			set[i] = s.set[i];
		num = n;
	}
	
	public boolean equalTo(IntSet s) { // 집합 s와 같은지 확인
		if(num != s.num) // 요소 개수가 같지 않으면
			return false; // 집합도 같지 않음
		
		for(int i = 0; i < num; i++) {
			int j = 0;
			for(; j < s.num; j++) 
				if(set[i] == s.set[j])
					break;
			if(j == s.num) // set[i]는 s에 포함되지 않음
				return false;
		}
		return true;
	}
	
	public void unionOf(IntSet s1, IntSet s2) { // 집합 s1과 s2의 합집합을 복사
		copyFrom(s1);
		for(int i = 0; i < s2.num; i++)
			add(s2.set[i]);
	}
	
	public String toString() { // 문자열로 바꿈
		StringBuffer temp = new StringBuffer("{");
		for(int i = 0; i < num; i++)
			temp.append(set[i] + " ");
		temp.append("}");
		return temp.toString();
	}
}
