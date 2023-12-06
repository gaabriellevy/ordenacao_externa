/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ordenacaoexterna;

import java.io.IOException;

/**
 *
 * @author aluno
 */
public class OrdenacaoExterna {

    public static void main(String[] args) throws IOException {
        Leitor leitor = new Leitor();
        leitor.lerArquivo("ordExt_teste.txt");
        
        Intercalacao intercalacao = new Intercalacao();
        intercalacao.intercalar();
    }
}
