//17. Letter Combinations of a Phone Number
//https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=leetcode-75
class Solution {
    Map<Character,String> buttonMap;// = new HashMap<Character,String>();
    public Solution(){
        buttonMap = new HashMap<Character,String>();
        buttonMap.put('2',"abc");
        buttonMap.put('3',"def");
        buttonMap.put('4',"ghi");
        buttonMap.put('5',"jkl");
        buttonMap.put('6',"mno");
        buttonMap.put('7',"pqrs");
        buttonMap.put('8',"tuv");
        buttonMap.put('9',"wxyz");
    }
    public List<String> letterCombinations(String digits) {
        char[] digitStrings = digits.toCharArray();
        return allCombinations(new ArrayList<>(List.of("")), digitStrings);
    }

    public List<String> allCombinations(List<String> prefixes, char[] digits){
        if(digits == null || digits.length == 0){
            return prefixes;
        }
        // char substitute = digits[0];
        char[] keys = buttonMap.get(digits[0]).toCharArray();
        List<String> newPrefixes = new ArrayList<String>();
        for(char c : keys){
            for(String prefix : prefixes ){
                newPrefixes.add(prefix+c);
            }
        }
        return allCombinations(newPrefixes, Arrays.copyOfRange(digits, 1, digits.length));
    }
}
