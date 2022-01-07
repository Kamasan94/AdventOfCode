package com.advent;
import java.io.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
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
            riga = filein.readLine();
            contaRiga = -1; 
            while(riga != null){
                riga = filein.readLine();
                
                if(!(riga.isBlank())){
                    contaRiga++;
                    rigaBoard = new ArrayList<String>(Arrays.asList(riga.split(",")));
                    Board.add(contaRiga, rigaBoard); 
                }
                else{
                    contaStrato++;
                    Boards.add(contaStrato, Board);
                    Board = new ArrayList<>(); 
                    contaRiga = -1;                                
                }
            }
            System.out.print(Numbers);
            System.out.println(Boards.size());
            System.out.println(Boards);
            filein.close();
            
          
            for(int i=0; i<Boards.size(); i++){
                for(int j=0; j<Boards.get(i).size(); j++){
                    for(int x=0; x<Boards.get(i).get(j).size(); x++){
                        System.out.print(Boards.get(i).get(j).get(x));
                    }        
                }        
            }
            
        } catch (Exception e) {
            //TODO: handle exception
        }

    }
}
