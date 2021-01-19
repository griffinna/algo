package acmicpc.AdHoc;

import java.io.*;
import java.util.*;

public class Solution_10798 { /* 세로읽기 */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[][] arr = new String[5][15];
        int max = 0;
        for (int i = 0; i < 5; i++) {
            String[] word = br.readLine().split("");
            max = Math.max(word.length, max);
            for (int j = 0; j < word.length; j++) {
                arr[i][j] = word[j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < 5; j++) {
                if(arr[j][i] != null) sb.append(arr[j][i]);
            }
        }
        System.out.println(sb.toString());
    }
}
