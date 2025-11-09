//216. Combination Sum III
//https://leetcode.com/problems/combination-sum-iii/description/?envType=study-plan-v2&envId=leetcode-75
class Solution {
    Map<Integer,Integer> slotLimitMap;
    public Solution(){
        //Create map of number of slots vs max limit insertable
        slotLimitMap = new HashMap<Integer,Integer>();
        slotLimitMap.put(1,9);
        slotLimitMap.put(2,17);
        slotLimitMap.put(3,24);
        slotLimitMap.put(4,30);
        slotLimitMap.put(5,35);
        slotLimitMap.put(6,39);
        slotLimitMap.put(7,42);
        slotLimitMap.put(8,44);
        slotLimitMap.put(9,45);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        // int[] searchSpace = {1,2,3,4,5,6,7,8,9};
        Set<Integer> searchSpace = new HashSet<>(Set.of(1,2,3,4,5,6,7,8,9));
        Set<List<Integer>> combinations =new HashSet<List<Integer>>();
        return new ArrayList<List<Integer>>((getCombinations(k, n, searchSpace, combinations)));
    }

    public Set<List<Integer>> getCombinations(int k, int n, Set<Integer> searchSpace, Set<List<Integer>> combinations){
        if(k==0 && n==0){
            Set<Integer> superset = new HashSet<>(Set.of(1,2,3,4,5,6,7,8,9));
            superset.removeAll(searchSpace);
            List<Integer> resultList = new ArrayList<Integer>(superset);
            for(Integer key : resultList){
                System.out.print(key);
            }
            System.out.println("");
            combinations.add(resultList);
            return combinations;
        }
        // if no slots left and sum is less than 0 exit
        if(k==0 && n>0){
         return  combinations;
        }
        // if amount to fill slot is more than possible limit then exit
        if(slotLimitMap.get(k)<n){
            return combinations;
        }
        int upperLimitValue = (int)n/k;
        Set<List<Integer>> identifiedCombinations = new HashSet<List<Integer>>();
        for(int i = upperLimitValue; i>=1; i--){
            if(searchSpace.contains(i)){
                // searchSpace.remove(i);
                Set newSet = new HashSet<>(searchSpace);
                newSet.remove(i);
                combinations=getCombinations(k-1, n-i, newSet, combinations);
            }
        }
        return combinations;
    }
}
