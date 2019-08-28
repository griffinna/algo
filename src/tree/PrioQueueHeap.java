package tree;

import java.util.Collections;
import java.util.PriorityQueue;

public class PrioQueueHeap {
    static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, Collections.reverseOrder());

    // 하위 수는 maxHeap, 상위 수는 minHeap
    // maxHeap 의 갯수가 minHeap 과 같거나 하나 많도록 유지
    public void addNumber(int num) {
        if (maxHeap.size() == minHeap.size()) {
            if ((minHeap.peek() != null && num > minHeap.peek())) {
                maxHeap.offer(minHeap.poll());  // move the smallest of minHeap to maxHeap
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
            }
        } else {
            if (num < maxHeap.peek()) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
        }
    }

    public double getMedian() {
        if(maxHeap.isEmpty()) return 0d;    // error
        if (maxHeap.size() == minHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2;
        } else if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        return 0;
    }
}
