package com.company;

import com.sun.org.apache.regexp.internal.CharacterArrayCharacterIterator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberToTextConvertor {

   private static String places[] = {"", "thousand", "million", "billion"};

   private  static String [][] startingValues = {
            {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"},
            {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"}
    };

    public static List<Integer> integerSets(Integer n){
        List<Integer> sets = new ArrayList<>();

        while(n > 0){
            sets.add(n % 1000);
            n /= 1000;
        }

        Collections.reverse(sets);
        return sets;
    }

    private static String numberToText (int num){

       StringBuilder set = new StringBuilder();

       int hundreds = num / 100;
       set.append((hundreds > 0) ? startingValues[0][hundreds - 1] + " hundred " : "");
       int rest = num % 100;

       if(rest > 0) {
           if (rest < 20)
               set.append(startingValues[0][rest - 1]);
           else
               set.append(startingValues[1][(rest / 10) - 2] + ((rest % 10 > 0) ? ("-" + startingValues[0][(rest % 10) - 1]) : ""));
       }

       return set.toString();

    }

    public static String integerToText(int n){

            List<Integer> sets = integerSets(n);

            StringBuilder s = new StringBuilder();
            for (int i = 0, k = sets.size(); i < k; i++)
                if (sets.get(i) > 0)
                    s.append(numberToText(sets.get(i))).append(" ").append(places[k - i - 1]).append(" ");

            //return IntStream.range(0,sets.size()).mapToObj(i -> (sets.get(i) > 0) ? (numberToText(sets.get(i)) + " " + places[sets.size() - i - 1] + " "): "").collect(Collectors.joining(" "));

            return s.toString();
    }


}
