package acmicpc.AdHoc;

import java.io.*;

public class Solution_4999 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String jaehwan = br.readLine();
        String doctor = br.readLine();
        if (jaehwan.length() >= doctor.length()) {
            System.out.println("go");
        } else {
            System.out.println("no");
        }
    }
}
