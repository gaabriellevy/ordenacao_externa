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
import java.util.Collections;
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
            File path = new File("temp");

            // cria pasta onde os arquivos serão colocados
            if (!path.exists()) {
                path.mkdir();
            }
            
            File file = new File(arquivo);
            Scanner in = new Scanner(file);
            
            int numArquivo = 1; // conta o indice dos arquivos
            
            while(in.hasNextLine()) { // lê as linha do arquivo
                ArrayList<Double> linhas = new ArrayList();

                for (int i = 0; i < 100 && in.hasNextLine(); i++) { // Insere 100 elementos na memória
                    String linha = in.nextLine();
                    linhas.add(Double.valueOf(linha));
                }
                
                Collections.sort(linhas); // ordena os 100 elementos
                
                File temp = new File("temp/temp"+numArquivo+".txt"); // cria um arquivo temporário
                FileWriter tempWriter = new FileWriter(temp); // cria o escritor para o arquivo temporário

                for (int j = 0; j < linhas.size(); j++) { // escreve as linhas no arquivo temporário
                    tempWriter.write(String.valueOf(linhas.get(j)));
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