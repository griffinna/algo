package acmicpc.LIS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_11568 {

    static int[] A = new int[313131];
    static int[] RD = new int[313131];
    static int N;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rn = 0;
        for (int i = 0; i < N; i++) {
//            A[i] = Integer.parseInt(br.readLine());
             A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int s = 0;
            int e = rn - 1;
            int k = rn;
//            System.out.printf("s : %s, e : %s, k : %s\n" ,s, e, k);
            while(s <= e){
                int m = (s + e) / 2;
                if (RD[m] >= A[i]) {
                    e = m - 1;
                    k = m;
                }else {
                    s = m + 1;
                }

            }
            RD[k] = A[i];
            if(k == rn) rn++;
        }

        System.out.println(rn);
        br.close();
    }

}
