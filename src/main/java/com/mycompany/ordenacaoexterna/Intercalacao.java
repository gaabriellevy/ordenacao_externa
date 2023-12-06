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

    public int countIntercalacoes;
    ArrayList<MembroDaIntercalacao> arquivos = new ArrayList<MembroDaIntercalacao>();

    public Intercalacao() {
        this.countIntercalacoes = 1;
    }

    public void intercalar() throws IOException {
        int numBlocos = 1;

        while(numBlocos > 1) {
            numBlocos = intercalaNiveis(numBlocos);
            countIntercalacoes++;
        }

        if(numBlocos == 1) {
            File arquivoFinal = new File("intercalacao"+countIntercalacoes+"/temp1.txt");
        }
    }

    public int intercalaNiveis(int numBlocos) throws IOException {
        int numArquivos = numArquivos("temp");
            
        int countArquivos = 1;
        int countArquivosTemporarios = 0;
        
        while(countArquivos <= numArquivos) {     
            
            int limite = countArquivos + 10;
            for (int i = countArquivos; i < limite && i < numArquivos; i++) { // instancia 10 arquivos temporários (ou até o último)
                arquivos.add(new MembroDaIntercalacao("temp/temp"+i+".txt"));
                
                countArquivos++;
            }
                        
            intercalaNivel();

            countArquivosTemporarios++;
        }
    }

    public void intercalaNivel() {
        File path = new File("intercalacao");

        // cria pasta onde os arquivos serão colocados
        if (!path.exists()) {
            path.mkdir();
        }

        // cria o arquivo temporário para armazenar este nível
        File temp = new File("intercalacao"+countIntercalacoes+"/temp"+countArquivosTemporarios+".txt");
        temp.createNewFile();
        FileWriter tempWriter = new FileWriter(temp); // cria o escritor para o arquivo temporário

        while(arquivos.size() != 0) {
            
            MembroDaIntercalacao menor = Collections.min(arquivos);
            
            tempWriter.write(String.valueOf(menor.getNumero()));
            
            if(menor.getNumero() == -1) {
                menor.deleteFile();
                menor.getLeitor().close();
                arquivos.remove(menor);
            }
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
}