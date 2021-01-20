package acmicpc.Trie;

import java.io.*;
import java.util.*;

public class Solution_14425 {
    static TrieNode root = new TrieNode();

    static void insert(String key) {
        root.insert(key + "\0");
    }

    static boolean find(String key) {
        Boolean result = root.find(key + "\0");
        if (result == null) {
            return false;
        } else {
            // result true: 값이 있음 (완전일치)
            // result false: 값이 있으나 끝이 아님 (포함)
            return result;
        }
    }
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            insert(br.readLine());
        }
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if (find(str)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static class TrieNode {
        private TrieNode[] next = new TrieNode[26]; // 알파벳
        private boolean leaf;

        void insert(String string) {
            char[] s = string.toCharArray();
            char curr = s[0];
            if (curr == '\0') {
                leaf = true;
            } else {
                int idx = char2idx(curr);
                if (next[idx] == null) {
                    next[idx] = new TrieNode();
                }
                if (string.length() > 1) {
                    string = string.substring(1);
                }
                next[idx].insert(string);
            }
        }

        Boolean find(String string) {
            char[] s = string.toCharArray();
            char curr = s[0];
            if (curr == '\0') {
                return leaf;
            } else {
                int idx = char2idx(curr);
                if (next[idx] == null) {
                    return null;
                }
                if (string.length() > 1) {
                    string = string.substring(1);
                }
                return next[idx].find(string);
            }
        }

        private int char2idx(char c) {
            System.out.println(c + " : " + (c - 'a'));
            if (c < 'a') {
                c += ('a' - 'A');
            }
//            System.out.println(c + " : " + (c - 'a'));
            return c - 'a';
        }
    }
}
