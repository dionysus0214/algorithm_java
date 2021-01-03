package ch06;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("옷 개수 : ");
		int value = sc.nextInt();
		int q = value / 5;
		int r = value % 5;

		boolean isValue = false;

		// 5로 나누었을 때 나머지가 0이 아닌 경우
		if (r != 0) {
			for (int v_3 = value; v_3 > 0; v_3 -= 3) {
				int q_5 = v_3 / 5;
				int r_5 = v_3 % 5;
				if (r_5 % 5 != 0) {
					continue;
				} else {
					int q_3 = (value - (q_5 * 5)) / 3;
					System.out.println("3box = " + q_3 + ", 5box = " + q_5);
					isValue = true;
					break;
				}
			}

			// 3으로만 구성된 박스
			if (isValue == false) {
				int q_3 = value / 3;
				int r_3 = value % 3;
				if (r_3 == 0) {
					isValue = true;
					System.out.println("3box = " + q_3 + ", 5box = " + 0);
				}
			}
		} else { // 모든 박스가 5개씩 담을 수 있음
			System.out.println("5box = " + q);
			isValue = true;
		}

		if (isValue == false) {
			System.out.println("박스에 담을 수 없는 게 있네??");
		}
		sc.close();
	}
}
