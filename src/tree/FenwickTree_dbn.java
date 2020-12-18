package tree;

import java.io.*;
import java.util.*;

public class FenwickTree_dbn {
	static int NUMBER = 6;
	static int[] tree = new int[NUMBER * 2];
	
	public static void main(String[] args) throws Exception {
		update(1, 1);
		update(2, 2);
		update(3, 3);
		update(4, 4);
		update(5, 5);
		
		System.out.println(getSection(2, 5));
		update(3, -2);
		System.out.println(getSection(2, 5));
		update(5, 2);
		System.out.println(getSection(2, 5));
		System.out.println(getSection(1, 5));
	}
	// 값 변경
	static void update(int i, int dif) {	// 3의 값을 변경하면 (3 > 4 > 8 > 16) 순서로 값 변경 (0011 > 0100 > 1000 > 10000)
		while(i <= NUMBER) {
			tree[i] += dif;
			// 마지막 비트만큼 더해가면서 이동
			i += (i & -i);
		}
	}
	
	// 1 ~ N까지 합 구하기
	// 값을 변경할때와 반대로 '마지막비트'만큼 구간을 앞으로 가며 구함
	// ex. 1 ~ 11 까지 합 : 11 > 10 > 8  (1011 > 1010 > 1000 > 0000)
	static int sum(int i) {
		int res = 0;
		while(i > 0) { 
			res += tree[i];
			i -= (i & -i);
		}
		return res;
	}
	
	// 특정구간의 합 구하기
	static int getSection(int start, int end) {
		return sum(end) - sum(start - 1);
	}
}
