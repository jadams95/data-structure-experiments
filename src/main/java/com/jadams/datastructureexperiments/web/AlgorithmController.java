package com.jadams.datastructureexperiments.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class AlgorithmController {



    @GetMapping("/bubble")
    public Integer[] bubbleSortController(@RequestBody String intStrCharacters){

        String[] listOfItems = intStrCharacters.replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "").split(",");

        Integer[] listOfIntgers = new Integer[listOfItems.length];
        Arrays.stream(listOfItems).forEach(x -> System.out.println(x));
        int x = 0;
            for(int i = 0; i < listOfItems.length; i++){

                listOfIntgers[i] = Integer.valueOf(listOfItems[i]);
//                if(Character.isDigit(Integer.parseInt(listOfItems[i]))){
//
//                    x++;
//                }
            }

        return listOfIntgers;
    }




}
