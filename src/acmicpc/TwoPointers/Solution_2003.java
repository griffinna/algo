package acmicpc.TwoPointers;

import java.io.*;
import java.util.*;

public class Solution_2003 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        long sum = 0;
        int s = 0;
        int e = 0;
        long result = 0;

        while (s < N) {
            if (sum > M || e == N) {
                sum -= arr[s];
                s++;
            } else {
                sum += arr[e];
                e++;
            }
            if (sum == M) {
                result++;
            }
        }
        System.out.println(result);
    }
}
