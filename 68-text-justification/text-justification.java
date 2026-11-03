import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        int n = words.length;

        while (i < n) {
            int j = i + 1;
            int lineLength = words[i].length();

            // 1. Pack as many words as possible in the current line
            while (j < n && lineLength + 1 + words[j].length() <= maxWidth) {
                lineLength += 1 + words[j].length();
                j++;
            }

            int numOfWords = j - i;
            StringBuilder sb = new StringBuilder();

            // 2. Case 1: Last line OR Line with only 1 word -> Left Justified
            if (j == n || numOfWords == 1) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        sb.append(" ");
                    }
                }
                // Fill remaining spaces at the end
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            } 
            // 3. Case 2: Middle lines with multiple words -> Fully Justified
            else {
                int totalWordChars = 0;
                for (int k = i; k < j; k++) {
                    totalWordChars += words[k].length();
                }

                int totalSpaces = maxWidth - totalWordChars;
                int gaps = numOfWords - 1;

                int evenSpaces = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    
                    if (k < j - 1) { // Don't add spaces after the last word in the line
                        int spacesToApply = evenSpaces + (k - i < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToApply; s++) {
                            sb.append(" ");
                        }
                    }
                }
            }

            result.add(sb.toString());
            i = j; // Move to the next set of words
        }

        return result;
    }
}