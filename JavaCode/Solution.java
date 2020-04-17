import java.lang.StringBuilder.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] newString = s.toCharArray();
        String currentSubstring = "";
        String longestSubstring = "";
        currentSubstring.append(s.charAt(0));
        longestSubstring.append(s.charAt(0));

        for(int i = 0; i < s.length(); i++){
            while(s.charAt(i) != s.charAt(i -1)){
                if (currentString.contains(s.charAt(i))){
                    break;
                }
                else{
                    currentSubstring.append(s.charAt(i));
                }
            }
            if (currentSubstring.length() > longestSubstring.length()){
                longestSubstring = currentSubstring;
            }

        }
        return longestSubstring.length();
    }
}
