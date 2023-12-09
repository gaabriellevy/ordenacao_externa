/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ordenacaoexterna;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class Intercalador {
    
    ArrayList<MembroDaIntercalacao> arquivos = new ArrayList<MembroDaIntercalacao>();
    File origem;
    File destino;
    
    public String intercalar() throws IOException {
                
        int nivel = 0;
                
        origem = new File("temp0");
        destino = new File("temp1");
        
        if (!destino.exists()) {
            destino.mkdir();
        }
        
        do{
            origem = new File("temp"+nivel);
            destino = new File("temp"+(nivel+1));
        
            // cria pasta onde os arquivos serão colocados
            if (!destino.exists()) {
                destino.mkdir();
            }
            
            intercalaNivel();
            
            nivel++;
            
        }while(calculanumArquivos(destino.getPath()) > 1);
        
        return "temp"+nivel+"/temp0.txt";
    }
    
    public void intercalaNivel() throws IOException {
        int countBlocos = 0;
        
        while(calculanumArquivos(origem.getPath()) > 0) {
            intercalaBloco(countBlocos);
            countBlocos++;
        }
        
        origem.delete();
    }
    
    public void intercalaBloco(int numBloco) throws IOException {
        //instancia 10 arquivos
        
        int countTemps = numBloco*10;
        
        for (int i = numBloco*10; i < (numBloco*10)+10; i++) {
            // se o arquivo existir, instancia no programa
            File arquivo = new File(origem.getPath()+"/temp"+countTemps+".txt");
            if(arquivo.isFile()) {
                arquivos.add(new MembroDaIntercalacao(origem.getPath()+"/temp"+countTemps+".txt")); 
            }
            
            countTemps++;
        }
                
        if(!arquivos.isEmpty()) {
            // cria o arquivo temporário para armazenar este nível
            File temp = new File(destino.getPath()+"/temp"+numBloco+".txt");
            temp.createNewFile();
            FileWriter tempWriter = new FileWriter(temp); // cria o escritor para o arquivo temporário
            
            while(!arquivos.isEmpty()) {
                MembroDaIntercalacao menor = Collections.min(arquivos);

                tempWriter.write(String.valueOf(menor.getNumero()));
                tempWriter.write("\n");

                if(menor.getNumero() == -1) {
                    menor.getLeitor().close();
                    menor.deleteFile();
                    arquivos.remove(menor);      
                }
            }
            tempWriter.close();
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
        
        public void deleteFile() {
            try {
                arquivo.delete();
            } catch (SecurityException e) {
                System.out.println("Erro de segurança ao excluir o arquivo: " + e.getMessage());
            }
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
