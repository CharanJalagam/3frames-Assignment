package wordCount;

import java.util.*;

public class FuzzySearch {

    public  String fuzzySearch(String query, List<String>wordList) {
        // Converting the query to lowercase for case-insensitive matching
        query = query.toLowerCase();

        // Binary search for the fuzzy match
        int left=0;
        int right=wordList.size()-1;
        while(left<=right){
            int mid=(left+right)/2;
            String currentWord=wordList.get(mid);
            // Check the similarity between the query and the current word using similarity function
            int similarityScore=similarity(query,currentWord);
            if(similarityScore == 0){
                // Exact match found
                return wordList.get(mid);
            }
            else if(similarityScore< 3){  //Taking the threshold as 3 for similar words
                // Fuzzy match found
                return wordList.get(mid);
            }
            //triming the right half
            if(query.compareTo(currentWord)< 0) {
                right= mid-1;
            }
            //trimming the left half
            else{
                left=mid + 1;
            }
        }
        // if No match is found then we return null
        return null;
    }
    // function to calculate the similarity between two strings
    public static int similarity(String str1,String str2) {
        int[][]dp= new int[str1.length()+1][str2.length()+1];
        for(int i=0;i<=str1.length();i++) {
            for(int j=0; j<=str2.length();j++) {
                if(i==0){
                    dp[i][j]=j;
                }
                else if(j==0){
                    dp[i][j]=i;
                }
                else{
                    dp[i][j]=Math.min(
                            dp[i-1][j-1]+(str1.charAt(i-1)==str2.charAt(j-1)?0:1),
                            Math.min(dp[i][j-1]+1, dp[i-1][j]+1)
                    );
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }
}

