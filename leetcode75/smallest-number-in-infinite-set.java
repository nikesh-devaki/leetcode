//2336. Smallest Number in Infinite Set
//https://leetcode.com/problems/smallest-number-in-infinite-set/description/?envType=study-plan-v2&envId=leetcode-75
class SmallestInfiniteSet {

    int[] heap = new int[2001];
    Set<Integer> set = new HashSet<Integer>();
    int lastElementIndex = 1000;

    public SmallestInfiniteSet() {
        for(int i=1;i<=1000;i++){
            heap[i] = i;
            set.add(i);
        }
    }
    
    public int popSmallest() {
        if(lastElementIndex<=0){
            return -1;
        }
        int min =  heap[1];
        set.remove(min);
        heap[1] = heap[lastElementIndex--];
        heapfiyDown(1);
        return min;
    }
    
    public void addBack(int num) {
        if(!set.contains(num)){
            heap[++lastElementIndex] = num;
            heapfiyUp(lastElementIndex);
            set.add(num);
        }
    }

    private void swap(int index1, int index2){
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private void heapfiyUp(int heapifyAt){
        int parent = heapifyAt/2;
        int left = parent*2;
        int right = parent*2+1;
        if(parent<1){
            return;
        }
        int min = 0;
        if(right<=lastElementIndex){
            if(heap[left]<heap[right]){
                min = left;
            }else{
                min = right;
            }
            if(heap[min]<heap[parent]){
                swap(min, parent); 
            }
        }else if(left<=lastElementIndex){
            if(heap[left]<heap[parent]){
                min = left;
                swap(min, parent);
            }
        }
        if(min!=0){
            heapfiyUp(parent);
        }
    }

    private void heapfiyDown(int heapifyAt){
        int parent = heapifyAt;
        int left = parent*2;
        int right = parent*2+1;
        int min = 0;
        if(right<=lastElementIndex){
            if(heap[right]<heap[left]){
                min = right;
            }else{
                min = left;
            }
            if(heap[min]<heap[parent]){
                swap(min, parent);
            }
            
        }else if(left<=lastElementIndex){
            if(heap[left] < heap[parent]){
                min = left;
                swap(min, parent);
            }
        }
        if(min!=0){
            heapfiyDown(min);
        }  
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
