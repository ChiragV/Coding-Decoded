
// @saorav21994
// TC : O(max(magazine,ransomNote))
// SC : O(26) = O(1)

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int [] hash = new int[26];
        for (char ch : magazine.toCharArray()) {
            hash[ch-'a'] += 1;
        }
        for (char ch : ransomNote.toCharArray()) {
            hash[ch-'a'] -= 1;
            if (hash[ch-'a'] < 0)
                return false;
        }
        return true;
        
    }
}
