package tree;

import java.util.Arrays;

public class MinHeap <T extends Comparable<T>>{
    static final int DEFAULT_CAPACITY = 10;
    int capacity;
    int size;
    T[] items;

    public MinHeap(){
        capacity = DEFAULT_CAPACITY;
        items = (T[]) new Comparable[capacity];
        size = 0;
    }

    public MinHeap(T[] arr) {
        size = arr.length;
        capacity = size;
        items = Arrays.copyOf(arr, size);
        buildMinHeap();
    }

    // (size >>> 1) - 1 은 size / 2 - 1 과 같은 의미
    // 1-bit right shift 는 나누기 2 한 것과 같고, 1-bit left shift 는 곱하기 2
    // size / 2 - 1 은 마지막 노드의 부모 노드를 의미
    private void buildMinHeap(){
        for(int i = (size >>> 2) - 1; i >= 0; i--){
            heapifyDown(i);
        }
    }
    /*
    *   새로운 element 가 add() 호출로 추가되면 heap 에 맞게 재배치 하는 메소드이다.
        - 파라미터 index 의 값으로는 마지막 노드의 index 가 온다.
        - 재배치 하려는 노드의 부모노드가 존재하고, 재배치 하려는 노드가 부모노드 보다 작은 동안 반복
        - 부모노드의 index 가져와서
        - 부모노드와 자식노드의 위치 swap
        - 반복
    * */
    private void heapifyUp(int idx){
        while (hasParent(idx) && less(items[idx], parent(idx))) {
            int parentIdx = getParentIndex(idx);
            swap(idx, parentIdx);
            idx = parentIdx;
        }
    }

    /*
    poll() 호출로 root 노드가 반환되면 마지막 노드를 root 에 위치시키고 heap 에 맞게 재배치 시키는 메소드.

        buildMinHeap() 에서 호출 될 때 이외에는 항상 index 0 이라는 값을 넘겨준다.
        left child 가 없으면 right child 도 없다. 즉, child 가 있는 동안 반복.
        두 children 중 더 작은 노드를 찾는다. 일단은 left child 가 더 작다고 가정하고 시작
        right child 가 있고, right child 가 left child 보다 작으면
        더 작은 child 는 right child 로 지정
        재배치 하려는 노드가 자녀 노드보다 작으면 멈춤
        자녀 노드가 더 크거나 같으면
        swap
        반복
    * */
    private void heapifyDown(int idx) {
        while (hasLeftChild(idx)) {
            int smallerChildIdx = getLeftChildIndex(idx);
            if (hasRightChild(idx) && less(rightChild(idx), leftChild(idx))) {
                smallerChildIdx = getRightChildIndex(idx);
            }
            if (less(items[idx], items[smallerChildIdx])) {
                break;
            } else {
                swap(idx, smallerChildIdx);
            }
        }
    }

    // Root 노드의 값이 무엇인지 확인만 하고 끝
    private T peek() {
        if(size == 0) throw new IllegalStateException();
        return items[0];
    }

    /*
        heap 이 비었으면 예외 던짐
        Root 노드 저장
        마지막 노드를 root 위치로 보냄
        마지막 노드 삭제
        heapifyDown()
    * */
    private T poll() {
        if(size == 0) throw new IllegalStateException();
        T item = items[0];
        items[0] = items[--size];
        items[size] = null;
        heapifyDown(0);
        return item;
    }

    private void add(T item) {
        ensureExtraCapacity();
        items[size++] = item;
        heapifyUp(size - 1);
    }

    private int getLeftChildIndex(int parentIdx) {
        return (parentIdx << 1) + 1;
    }

    private int getRightChildIndex(int parentIdx) {
        return (parentIdx << 1) + 2;
    }

    private int getParentIndex(int childIdx) {
        return (childIdx - 1) >> 1;
    }

    private boolean hasLeftChild(int idx) {
        return getLeftChildIndex(idx) < size;
    }

    private boolean hasRightChild(int idx) {
        return getRightChildIndex(idx) < size;
    }

    private boolean hasParent(int idx){
        return getParentIndex(idx) >= 0;
    }

    private T leftChild(int idx) {
        return items[getLeftChildIndex(idx)];
    }

    private T rightChild(int idx) {
        return items[getRightChildIndex(idx)];
    }

    private T parent(int idx) {
        return items[getParentIndex(idx)];
    }

    private void swap(int idxA, int idxB) {
        T temp = items[idxA];
        items[idxA] = items[idxB];
        items[idxB] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity << 1);
            capacity = capacity << 1;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
