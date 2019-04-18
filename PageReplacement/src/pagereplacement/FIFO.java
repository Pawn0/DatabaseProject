package pagereplacement;

import java.util.ArrayList;
import java.util.Arrays;

public class FIFO {
        public void runFIFO(int size, int[] pages){
        cache = new int[size];
        Arrays.fill(cache, -1);
        generateFIFOPages(pages);
    }
    
    private void generateFIFOPages(int[] pages){
        int counter = 0;
        int index = 0;
        int cacheCounter = 0;
        
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
                if (cacheCounter >= cache.length) {
                    cacheCounter = 0;
                }

                if (cache[cacheCounter] == -1) {
                    cache[cacheCounter] = page;
                    cacheCounter++;
                } else {
                    if (index >= cache.length) {
                        index = 0;
                    }

                    cache[index] = page;
                    index++;
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
    
    public String getFIFODataForDisplay(){
        String result = "=======================\n" +
        "PAGE-FRAME PROGRESSION for FIFO\n" +
        "=======================\n" +
        "PAGE-FRAME STATE      PAGE FAULT\n" ;
        for(String s : displayData){
            result += s;
        }
        
       result += "=======================\n";
        return result;
    }
    
    public String getPageFaultCount()
    {
        return Integer.toString(pageFaults);
    }
    
    String CacheForDisplay = "";
    String cacheMissesAndHits[]; 
    ArrayList<String> displayData = new ArrayList<>(); 
    int cache[];
    String FIFOResult[];
    int pageFaults = 0;

    
}
