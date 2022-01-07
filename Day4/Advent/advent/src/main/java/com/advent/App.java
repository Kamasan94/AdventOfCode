package com.advent;
import java.io.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ArrayList<String> Numbers = new ArrayList<String>() ;
        ArrayList<ArrayList<ArrayList<String>>> Boards = new ArrayList<>();
        String riga;
        BufferedReader filein;
        int contaStrato = -1, contaRiga = 0;
        System.out.println( "Hello World!" );
        ArrayList<ArrayList<String>> Board = new ArrayList<>(); 
        ArrayList<String> rigaBoard = new ArrayList<String>();
        try {
            filein = new BufferedReader(new FileReader("dati.txt"));
            riga = filein.readLine();
            Numbers = new ArrayList<String>(Arrays.asList(riga.split(",")));

            while(riga != null){
                riga = filein.readLine();
                
                if(!(riga.isBlank())){
                    contaRiga++;
                    rigaBoard = new ArrayList<String>(Arrays.asList(riga.split(",")));
                    Board.add(contaRiga, rigaBoard);
                    Boards.add(contaStrato, Board);
                }
                else{
                    Board = new ArrayList<>(); 
                    contaRiga = -1;
                    Boards.add(new ArrayList<ArrayList<String>>());
                    contaStrato++;
                }
            }
            

            filein.close();
            
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println(Numbers);
        System.out.print(Boards);

    }
}
