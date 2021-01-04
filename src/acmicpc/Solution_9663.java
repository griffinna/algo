package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 
 * https://www.acmicpc.net/problem/9663
 * 
 * N-Queen
 * 문제
	N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
	
	N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
	
	입력
	첫째 줄에 N이 주어진다. (1 ≤ N < 15)
	
	출력
	첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
 * 
 * 
 *  입력 : 8
 *  출력 : 92
 * */
public class Solution_9663 {

	static int n;
	static int count;
	static int[] arr;
	static boolean[] a;		// a[x]
	static boolean[] b;		// a[y]
	static boolean[] c;		// c[x + y]
	static boolean[] d;		// d[x - y + n - 1]
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		a = new boolean[n];
		b = new boolean[n];
		c = new boolean[n * 2];
		d = new boolean[n * 2];
		count = 0;

		search(0);
		
		System.out.println(count);
	}
	
	static void search(int y) {
		if(y == n) {
			count++;
			return;
		}
		
		for (int x = 0; x < n ; x++) {
			if(a[x] || b[y] || c[x + y] || d[x - y + n - 1]) {
				continue;
			}
			
			a[x] = true;
			b[y] = true;
			c[x + y] = true;
			d[x - y + n - 1] = true;
			
			search(y + 1);
			
			a[x] = false;
			b[y] = false;
			c[x + y] = false;
			d[x - y + n - 1] = false;
		}
		
		
	}
	
}
