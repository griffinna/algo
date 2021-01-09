package acmicpc.LIS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LIS_solution {

    static int[] A = new int[10];
    static int[] RD = new int[10];
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
            System.out.println(Arrays.toString(RD));
            System.out.println("==================== " + A[i] + "======================");
            System.out.printf("s : %s, e : %s, k : %s\n" ,s, e, k);
            while(s <= e){
                int m = (s + e) / 2;
                System.out.printf("::: m : %s / RD[m] : %s , A[i] : %s\n", m, RD[m], A[i]);

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

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("rn : " + rn);
        bw.flush();
        bw.close();
        br.close();
    }

}
