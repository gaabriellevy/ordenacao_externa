/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ordenacaoexterna;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class OrdenacaoExterna {

    public static void main(String[] args) throws IOException {
        System.out.print("Digite o caminho do arquivo: ");
        
        Scanner in = new Scanner(System.in);
        String caminhoOriginal = in.next();
        
        in.close();
        
        Leitor leitor = new Leitor();
        leitor.lerArquivo(caminhoOriginal);
        
        Intercalador intercalador = new Intercalador();
        String pathArquivoIntercalado = intercalador.intercalar();
        
        int ultimoIndiceBarra = caminhoOriginal.lastIndexOf('/');
        System.out.println(caminhoOriginal);
        System.out.println(ultimoIndiceBarra);
        String diretorioCaminhoOriginal = caminhoOriginal.substring(0, ultimoIndiceBarra + 1);
        
        ultimoIndiceBarra = pathArquivoIntercalado.lastIndexOf('/');
        String caminhoDiretorioTemp = pathArquivoIntercalado.substring(0, ultimoIndiceBarra + 1);
        File diretorioTemp = new File(caminhoDiretorioTemp);
        
        File origem = new File(pathArquivoIntercalado);
        Path pathOrigem = Paths.get(pathArquivoIntercalado);
        Path pathDestino = Paths.get(diretorioCaminhoOriginal+"ordenado.txt");
        System.out.println(diretorioCaminhoOriginal);
        System.out.println(pathOrigem);
        System.out.println(pathDestino);
        Files.move(pathOrigem, pathDestino, StandardCopyOption.REPLACE_EXISTING);
        origem.delete();
        diretorioTemp.delete();
        
        System.out.println("Seu arquivo foi ordenado e colocado na mesma pasta do arquivo de origem com o nome ordenado.txt");;
    }
}
