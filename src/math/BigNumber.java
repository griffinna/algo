package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** https://www.acmicpc.net/problem/10757

큰 수 A + B

문제
A+B를 계산하시오.

입력
첫째 줄에 A와 B가 주어진다. (0 < A,B < 1010000)

9223372036854775807 9223372036854775808

출력
첫째 줄에 A+B를 출력한다.

18446744073709551615

 * */

public class BigNumber {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = st.nextToken();
		String B = st.nextToken();
		
		String[] splitA = A.split("");
		String[] splitB = B.split("");
		
		
		int maxLength = Math.max(splitA.length, splitB.length) + 1;
		
		int[] arrA = new int[maxLength];
		int[] arrB = new int[maxLength];
		int[] nextA = new int[maxLength];
		for (int i = 0; i < maxLength; i++) {
			if(i < splitA.length) {
				arrA[i] = Integer.parseInt(splitA[(splitA.length-1) - i]);
			}
			if(i < splitB.length) {
				arrB[i] = Integer.parseInt(splitB[(splitB.length-1) - i]);
			}
		}
		
		int[] sum = new int[maxLength];
		int n = 0;
		for (int i = 0; i < sum.length - 1; i++) {
			n = arrA[i] + arrB[i] + nextA[i];
			if(n >= 10) {
				nextA[i + 1] = n / 10;
				n = n % 10;
			}
			sum[i] = n;
		}
		sum[maxLength - 1] += nextA[maxLength - 1];
		boolean isZero = true;
		for (int i = sum.length - 1; i >= 0; i--) {
			if(isZero) {
				if(sum[i] > 0) isZero = false;
			}
			
			if(!isZero) {
				System.out.print(sum[i]);
			}
		}
		
	}
	
}
