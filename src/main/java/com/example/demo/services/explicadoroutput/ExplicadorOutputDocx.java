package com.example.demo.services.explicadoroutput;

import com.example.demo.models.Explicador;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class ExplicadorOutputDocx implements ExplicadorOutput {

    @Override
    public byte[] outputFile(Explicador explicador, String type){return null;}

    /*private WordprocessingMLPackage  wordMLPackage;
    {
        try {
            wordMLPackage = WordprocessingMLPackage.createPackage();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    */

}
