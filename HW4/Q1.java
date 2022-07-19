public class Q1 {
    /**
     * 
     * @param big Big string 
     * @param little Little string
     * @param count Number of occurences
     * @return Returns count'th recurrence of little string in big string
     */
    public static int Question1(String big, String little, int count){
        //calling helper recursive function
        return Question1(big, little, count, 0); 
    }
    
    /**
     * 
     * @param big Big string
     * @param little Little string
     * @param count Number of occurrences
     * @param index Parameter to hold last occurrence of little string
     * @return
     */
    public static int Question1(String big, String little,int count, int index){
        
        //base case if strings are null or 0 sized
        if(big == null || little == null || big.length()==0 || little.length()==0||big.length()<little.length()) 
            return -1;
        
        int result;
        result = big.indexOf(little, 0); //finding first occurrence of little string
        
        if(result!=-1 && count == 1) return index + result; //if count is 1, that means last occurrence is found
        else if(result!=-1 && count > 0) return Question1( big.substring(result+1, big.length()) , little ,count-1, index+1); //else continue searching recursively
        
        return -1;
    }
    
    public void test(){
        System.out.printf("\n!-!-!-Second string will be searched in first string-!-!-!\n");
        System.out.printf("\n|index of 1st occurrence -> %d |||| %s |||| %s |",Question1("erselcelaleren","celal",1),"erselcelaleren", "celal");
        System.out.printf("\n|index of 2nd occurrence -> %d |||| %s |||| %s |",Question1("erselersel","ersel",2),"erselersel", "ersel");
        System.out.printf("\n|index of 3rd occurrence -> %d |||| %s |||| %s |",Question1("erselersel","ersel",3),"erselersel", "ersel");
        System.out.printf("\n|index of 3rd occurrence -> %d |||| %s |||| %s |",Question1("ersel","erselcelaleren",1),"ersel", "erselcelaleren");
        System.out.printf("\n|index of 1st occurrence -> %d |||| %s |||| %s |",Question1("bcdbcd","bcd",1),"bcdbcd", "bcd");
        System.out.printf("\n|index of 2nd occurrence -> %d |||| %s |||| %s |",Question1("bcdbcd","bcd",2),"bcdbcd", "bcd");
        System.out.printf("\n|index of 3rd occurrence -> %d |||| %s |||| %s |",Question1("bcdbcd","bcd",3),"bcdbcd", "bcd");
        System.out.printf("\n|index of 1st occurrence -> %d |||| %s |||| %s |",Question1("aaaaa","a",1),"aaaaa", "a");
        System.out.printf("\n|index of 5th occurrence -> %d |||| %s |||| %s |",Question1("aaaaa","a",5),"aaaaa", "a");
        System.out.printf("\n|index of 1st occurrence -> %d |||| %s |||| %s |",Question1("abc","abcdef",1),"abc", "abcdef");
        System.out.printf("\n|index of 1st occurrence -> %d |||| %s |||| %s |",Question1("aaaaa","aaaaa",1),"aaaaa", "aaaaa");
        System.out.printf("\n|index of 2nd occurrence -> %d |||| %s |||| %s |",Question1("aaaaaa","aaaaa",2),"aaaaaa", "aaaaa");
        System.out.printf("\n|index of 1st occurrence -> %d |||| %s |||| %s |(searching space char)",Question1(" "," ",1)," ", " ");
        System.out.printf("\n|index of 1st occurrence -> %d |||| %s |||| %s |\n\n",Question1("","",1),"", "");
    }
    
}
