package tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FindMedian {
    static PriorityQueue<Integer> smaller;
    static PriorityQueue<Integer> bigger;

    public static void main(String[] args) throws Exception{

//        System.setIn(new FileInputStream("/Users/garam/gitRepository/algo/src/text/sample_input_01.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCnt = Integer.parseInt(br.readLine());
        smaller = new PriorityQueue<>(numCnt);
        bigger = new PriorityQueue<>(numCnt, Collections.reverseOrder());
        StringTokenizer st = new StringTokenizer(br.readLine());
        smaller.offer(Integer.parseInt(st.nextToken()));
        int std = 0;
        int num = 0;
        for (int i = 1; i < numCnt; i++) {
            std = smaller.peek();
            num = Integer.parseInt(st.nextToken());
            // number insert
            if (std < num) {
                smaller.offer(num);
            }else{
                bigger.offer(num);
            }
            // resize
            if(smaller.size() < bigger.size()){
                smaller.offer(bigger.poll());
            } else if (smaller.size() > bigger.size() + 1) {
                bigger.offer(smaller.poll());
            }
        }

        System.out.println("median is " + smaller.peek());

    }

}
