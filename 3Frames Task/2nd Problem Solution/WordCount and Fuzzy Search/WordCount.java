package wordCount;

import wordCount.FuzzySearch;

import java.io.*;
import java.util.*;

public class WordCount {

    public static void main(String[] args) {
        //Creating object for class Fuzzy search to use fuzzy search on the query
        FuzzySearch fs=new FuzzySearch();
        //Giving the path of the file
        String filePath="C:\\Users\\jalag\\Desktop\\courses\\new.txt";

        try {
            // Initializing a TreeMap for automatic sorting by keys
            TreeMap<String, Integer>wordCountMap=new TreeMap<>();

            // Initializing a BufferedReader to efficiently read the file as buffer reader reads line by line so 10 Mb Ram provided will be sufficiant
            try (BufferedReader reader=new BufferedReader(new FileReader(filePath))) {
                String line;
                while((line=reader.readLine()) !=null) {
                    // Split the line into words for storing in the TreeMap
                    String[] words=line.split("\\s+");

                    // Processing each word
                    for(String word: words) {
                        //Converting words to lower case to avoid case senstivity
                        wordCountMap.put(word.toLowerCase(),wordCountMap.getOrDefault(word,0)+1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Printing the sorted word counts
            wordCountMap.forEach((word, count) ->System.out.println(word+": "+count));
            //ArrayList to store the sorted words to use in fuzzy Search
            ArrayList<String>l=new ArrayList<>();
            //adding words to list using for each
            wordCountMap.forEach((word, count) -> l.add(word));
            //the query that is need to be searched
            String query = "offer";
            //calling the fuzzySearch method()
            String result = fs.fuzzySearch(query, l);
            //printing the o/p
            System.out.println("The fuzzy or the exact match found is : "+result);
        } catch (OutOfMemoryError e) {
            System.out.println("Memory limit exceeded.");
        }
    }
}
