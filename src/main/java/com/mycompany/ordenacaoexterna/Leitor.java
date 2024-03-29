/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ordenacaoexterna;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class Leitor {
  
    public void lerArquivo(String arquivo) throws IOException {
        try {
            File path = new File("temp0");

            // cria pasta onde os arquivos serão colocados
            if (!path.exists()) {
                path.mkdir();
            }
            
            File file = new File(arquivo);
            Scanner in = new Scanner(file);
            
            int numArquivo = 0; // conta o indice dos arquivos
            
            while(in.hasNextLine()) { // lê as linha do arquivo
                double[] linhas = new double[1000];

                for (int i = 0; i < 1000 && in.hasNextLine(); i++) { // Insere 100 elementos na memória
                    String linha = in.nextLine();
                    linhas[i] = Double.valueOf(linha);
                }
                
                HeapSort.heapSort(linhas); // ordena os 100 elementos
                
                File temp = new File("temp0/temp"+numArquivo+".txt"); // cria um arquivo temporário
                FileWriter tempWriter = new FileWriter(temp); // cria o escritor para o arquivo temporário

                for (int j = 0; j < linhas.length; j++) { // escreve as linhas no arquivo temporário
                    tempWriter.write(String.valueOf(linhas[j]));
                    tempWriter.write("\n");
                }
                tempWriter.close();
                
                numArquivo++;
            }
            in.close();
            
        }catch(FileNotFoundException e) {
            Logger.getLogger(OrdenacaoExterna.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}