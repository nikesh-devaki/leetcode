//2462. Total Cost to Hire K Workers
//https://leetcode.com/problems/total-cost-to-hire-k-workers/description/?envType=study-plan-v2&envId=leetcode-75
class Solution {

    class Worker implements Comparable<Worker>{
        int value;
        int pos;

        public Worker(int value, int pos){
            this.value = value;
            this.pos = pos;
        }

        @Override
        public int compareTo(Worker worker){
           return this.value == worker.value ? this.pos - worker.pos : this.value - worker.value;
        }
    }

    public boolean add(PriorityQueue<Worker> workers, int[] inputWorkers, int startPos, int candidates){
        for(int i = startPos; i < startPos+candidates && i < inputWorkers.length; i++){
            workers.add(new Worker(inputWorkers[i], i));
        }
        return true;
    }

    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Worker> workers = new PriorityQueue<Worker>();
        long totalCost = 0l;
        int startPos = 0;
        // int endPos = costs.length - candidates;
        int startCandidates = candidates;
        add(workers, costs, startPos, startCandidates);
        startPos = candidates - 1;
        int endPos = (costs.length - candidates) <= startPos ? (startPos+1) :    
                                         (costs.length - candidates);
        int endCandidates = costs.length - endPos + 1;
        // System.out.println("end candidates "+endCandidates);
        add(workers, costs, endPos, endCandidates);
        endPos = costs.length - candidates;
        
        for(int i=1; i <= k; i++){
            //hire worker
            Worker worker = workers.poll();
            // System.out.println("Hired worker is "+worker.value + " at "+worker.pos);
            totalCost += (long)worker.value;

            //check which side
            boolean isLeft = worker.pos <= startPos;
            //  System.out.println(" is left is "+ isLeft);
            //add new element to queue
            boolean temp = isLeft ? ((startPos+1<endPos)?add(workers, costs, ++startPos, 1): false):
                                            ((endPos-1>startPos)? add(workers, costs, --endPos, 1): false);
            // System.out.println(" startPos: " + startPos + " end pos: "+ endPos);
        }
        return totalCost;
    }
}
