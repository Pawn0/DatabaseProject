/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forever;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class Forever {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] scores = {4,1,1,4,5};
        Forever forever = new Forever();
        System.out.println(forever.sequentialSearch(scores, 2));
    }
    
    public boolean sequentialSearch(int[] scores, int key){
        for(int i = 0; i < scores.length; i++){
            if (scores[i] == key){
                return true;
            }
        }
        return false;
    }
}
            