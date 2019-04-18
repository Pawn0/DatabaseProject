package pagereplacement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Optimal {
        public void runOptimal(int size, int[] pages){
        cache = new int[size];
        Arrays.fill(cache, -1);
        generateOptimalPages(pages);
    }
    
    private void generateOptimalPages(int[] pages){
       
        int cacheCounter = 0;
        int counter = 0;
        for(int page : pages){
            //cycle through the cache to find out if it was a hit or miss
            boolean miss = true;
            for (int cachePage : cache) {
                if (page == cachePage) {//hit
                    miss = false;
                }
            }
            
            if (miss) {
                pageFaults++;
                //if were larger or equal to the length of the cache loop around
                if (cacheCounter >= cache.length) {
                    cacheCounter = 0;
                }
                
                //for the inevitable cold start
                if (cache[cacheCounter] == -1) {
                    cache[cacheCounter] = page;
                    cacheCounter++;
                } else { 
                    //find the oldest page, via its lowest number
                    int pageToReplace = findTheOldestPages(pages, counter, pages.length, cache);
                    
                    //find the index of the pageToReplace in cache
                    for(int i = 0; i < cache.length; i++){
                        if(cache[i] == pageToReplace){
                            cache[i] = page;
                            break;
                        }
                    }
                }

            }
                   
            //generate the display
            String cacheAtTheMoment = "[" + page + "]:[";
            for (int pageIndex = 0; pageIndex < cache.length - 1; pageIndex++) {
                if (cache[pageIndex] == -1){
                    cacheAtTheMoment +=  "  |";
                }
                else{
                    cacheAtTheMoment += cache[pageIndex] + "|";
                }
            }
            
            if(cache[cache.length - 1] == -1){
                if (miss){
                    cacheAtTheMoment += "  ]                      YES\n";
                }else{
                    cacheAtTheMoment += "  ]                          \n";
                }
            }
            else{
                if(miss){
                    cacheAtTheMoment += cache[cache.length - 1] +"]                       YES\n";
                }
                else{
                    cacheAtTheMoment += cache[cache.length - 1] +"]\n";
                }
            }
            displayData.add(cacheAtTheMoment);
            counter++;
            
        }
    }
    
    public String getOptimalDataForDisplay(){
        String result = "=======================\n" +
        "PAGE-FRAME PROGRESSION for Optimal\n" +
        "=======================\n" +
        "PAGE-FRAME STATE      PAGE FAULT\n" ;
        for(String s : displayData){
            result += s;
        }
        
       result += "=======================\n";
        return result;
    }
    
    private int findTheOldestPages(int[] pages, int start, int end, int[] currentCache){
        int pageToReplace = -1;
        //ironically the smallest index is the same as the oldest one.
        ArrayList<Integer> pageAges = new ArrayList<>();
        ArrayList<Integer> cache = new ArrayList<>();
        ArrayList<Integer> appearances = new ArrayList<>();
        Set<Integer> duplicates = new HashSet<>();
        
        for(int i : currentCache){
            cache.add(i);
            pageAges.add(-1);
            appearances.add(0);
        }
        
        
        //now go through the list and find their ages
        //the larger the index, the younger
        for(int i = start; i < end; i++){
            if(cache.indexOf(pages[i]) != -1){
                int indexOfUniquePage = cache.indexOf(pages[i]);
                pageAges.set(indexOfUniquePage, i);
                int appearanceCount = appearances.get(indexOfUniquePage);
                appearanceCount++;
                appearances.set(indexOfUniquePage, appearanceCount);
            }
        }
        
        if (end - start > cache.size()) {
            int duplicateCount = 0;
            //if two pages have the same number of appearances
            for (int i : appearances) {
                if (!duplicates.add(i)) {
                    duplicateCount++;
                }
            }
            if (duplicateCount == 0) {
                int lowestApperancePage = Collections.min(appearances);
                pageToReplace = cache.get(appearances.indexOf(lowestApperancePage));
            } else {
                int oldestPage = Collections.max(pageAges);
                pageToReplace = cache.get(pageAges.indexOf(oldestPage));
            }
        }else{
            //find the page that is not in the cache list
            ArrayList<Integer> pagesRemaining = new ArrayList<>();
            for(int objects = start; objects < end; objects++){
                pagesRemaining.add(pages[objects]);
            }
            
            for(int pagesInCache : cache){
                if(pagesRemaining.indexOf(pagesInCache) == -1){
                    pageToReplace = pagesInCache;
                    break;
                }
            }
        }
        
        return pageToReplace;
    }
    
    public String getPageFaultCount()
    {
        return Integer.toString(pageFaults);
    }
    
    String CacheForDisplay = "";
    String cacheMissesAndHits[]; 
    ArrayList<String> displayData = new ArrayList<>(); 
    int cache[];
    String optimalResult[];
    int pageFaults = 0;

    
}