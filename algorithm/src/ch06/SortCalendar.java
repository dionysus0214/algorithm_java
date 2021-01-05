package ch06;

import java.util.Arrays;
import java.util.GregorianCalendar;
import static java.util.GregorianCalendar.*;

public class SortCalendar {
	public static void main(String[] args) {
		GregorianCalendar[] x = {
				new GregorianCalendar(2017, NOVEMBER, 1),
				new GregorianCalendar(1990, FEBRUARY, 14),
				new GregorianCalendar(1992, JANUARY, 4),
		};
		
		Arrays.parallelSort(x);
		
		for(int i = 0; i < x.length; i++) 
			System.out.printf("%04d년 %02d월 %02d일\n", x[i].get(YEAR), x[i].get(MONTH) + 1, x[i].get(DATE));
	}
}
