/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ordenacaoexterna;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class Intercalador {
    
    ArrayList<MembroDaIntercalacao> arquivos = new ArrayList<MembroDaIntercalacao>();
    
    public Intercalador() throws IOException {
   
        //instancia 10 arquivos
        for (int i = 1; i < 11; i++) {
            arquivos.add(new MembroDaIntercalacao("temp/temp"+i+".txt"));
        }
        
        File path = new File("intercalacao1");

        // cria pasta onde os arquivos serão colocados
        if (!path.exists()) {
            path.mkdir();
        }
        
        intercalaBloco(1);
    }
    
    public void intercalaBloco(int numBloco) throws IOException {
        // cria o arquivo temporário para armazenar este nível
        File temp = new File("intercalacao1/temp"+numBloco+".txt");
        temp.createNewFile();
        FileWriter tempWriter = new FileWriter(temp); // cria o escritor para o arquivo temporário
        
        while(!arquivos.isEmpty()) {
            
            MembroDaIntercalacao menor = Collections.min(arquivos);

            tempWriter.write(String.valueOf(menor.getNumero()));
            tempWriter.write("\n");
            
            if(menor.getNumero() == -1) {
                System.out.println("Apagando "+menor.arquivo.getPath());
                menor.deleteFile();
                 menor.getLeitor().close();
                 arquivos.remove(menor);      
            }
        }
    }
    
    public class MembroDaIntercalacao implements Comparable<MembroDaIntercalacao>{
        private File arquivo;
        private Scanner leitor;
        private double numero;

        public double getNumero() {
            double numeroReturn = numero;
            
            if(leitor.hasNextLine()) {
                this.numero = Double.parseDouble(leitor.nextLine());   
            }else {
                this.numero = -1;
            }

            return numeroReturn;
        }

        public void setNumero(double numero) {
            this.numero = numero;
        }

        public Scanner getLeitor() {
            return leitor;
        }
        
        public MembroDaIntercalacao(String path){
            try{
                arquivo = new File(path);
                leitor = new Scanner(arquivo);
                numero = Double.parseDouble(leitor.nextLine());    
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        public void deleteFile(){
            System.out.println(arquivo.getPath());
            arquivo.delete();
        }

        @Override      
        public int compareTo(MembroDaIntercalacao membro) {
            if (this.numero < membro.numero) {
                return -1;
            }
            if (this.numero > membro.numero) {
                return 1;
            }
            return 0;
        }

    }

        public static int calculanumArquivos(String path) {
            int contador = 0;
            File pasta = new File(path);
            File[] lista = pasta.listFiles();

            for (File file : lista) {
                if (file.isFile()) {
                    contador ++;
                }
            }

            return contador;
        }
}
