package com.example.demo.services.explicadoroutput;

import com.example.demo.models.Explicador;

public interface ExplicadorOutput {
    byte[] outputFile(Explicador explicador, String type);
}
