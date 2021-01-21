package acmicpc.NumberTheory;

import java.io.*;
import java.util.*;

public class Solution_11653 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        loop: for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                if (N % i == 0) {
                    while (N % i == 0) {
                        System.out.println(i);
                        N /= i;
                        if(N == 1) break loop;
                    }
                }
                int j = i + i;
                while (j <= N) {
                    isPrime[j] = false;
                    j += i;
                }
            }
        }
    }
}
