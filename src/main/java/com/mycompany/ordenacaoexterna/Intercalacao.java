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
public class Intercalacao {
    
    public void intercalar() throws IOException {
        int numArquivos = numArquivos("temp");
            
        int countArquivos = 1;
        int countArquivosTemporarios = 1;
        
        ArrayList<MembroDaIntercalacao> arquivos = new ArrayList<MembroDaIntercalacao>();
            
        while(countArquivos <= numArquivos) {
            
            int limite = countArquivos + 10;
            for (int i = countArquivos; i < limite && i < numArquivos; i++) { // cria até 10 arquivos temporários
                arquivos.add(new MembroDaIntercalacao("temp/temp"+i+".txt"));
                
                countArquivos++;
            }
            
            // cria o arquivo temporário para armazenar
            File temp = new File("intercalacao/temp"+countArquivosTemporarios+".txt");
            temp.createNewFile();
            FileWriter tempWriter = new FileWriter(temp); // cria o escritor para o arquivo temporário
            
            while(arquivos.size() != 0) {
                
                MembroDaIntercalacao menor = Collections.min(arquivos);
                
                tempWriter.write(String.valueOf(menor.getNumero()));
                
                if(menor.getNumero() == -1) {
                    menor.getLeitor().close();
                    arquivos.remove(menor);
                }
                
                //Collections.min(numeros doubles)
                // pega o menor do vetor de números
                // escreve no novo arquivo
                // next line no objeto de onde vc tirou menor numero, la dentro vc tem verificar se chegar ao final do arquivo, 
                // se tiver chegado ao final do arquivo, retorna -1, remover o objeto de arquivos
            }
            
            countArquivosTemporarios++;
            
        }
    }
        
    public static int numArquivos(String path) {
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
    
    public class MembroDaIntercalacao implements Comparable<MembroDaIntercalacao>{
        private File arquivo;
        private Scanner leitor;
        private double numero;

        public double getNumero() {
            return numero;
            
            if(leitor.hasNextLine()) {
                this.numero = Double.parseDouble(leitor.nextLine());   
            }else {
                this.numero = -1;
            }
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
        
        public void getProximoNumero(){
            
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
}