package acmicpc.TwoPointers;

import java.io.*;
import java.util.*;

public class Solution_1806 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 1;
        int e = 1;
        int sum = arr[e];
        int length = N;
        boolean possible = false;
        while (s <= e || e <= N) {
            if (sum < S) {
                e++;
                sum += arr[e];
            } else if (sum >= S) {
                possible = true;
                length = Math.min(length, e - s + 1);
                sum -= arr[s];
                s++;
            }
            if(e == N && sum < S) break;
        }
        if (possible) {
            System.out.println(length);
        } else {
            System.out.println(0);
        }
    }
}