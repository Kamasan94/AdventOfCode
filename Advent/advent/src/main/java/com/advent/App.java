package com.advent;
import java.io.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Lista dei numeri iniziali
        ArrayList<String> Numbers = new ArrayList<String>() ;
        //Lista di tutte le matrici 2x2
        ArrayList<ArrayList<ArrayList<String>>> Boards = new ArrayList<>();
        //Riga di testo per leggere file e buffer
        String riga;
        BufferedReader filein;
        //Inizializzo indici di strato e riga
        int contaStrato = -1, contaRiga = -1;
        //Lista 2x2 di una matrice
        ArrayList<ArrayList<String>> Board = new ArrayList<>(); 
        //Riga di una matrice
        ArrayList<String> rigaBoard = new ArrayList<String>();
        int numeriUsciti = 0, contaNull = 0, winnerBoard = Integer.MAX_VALUE, ultimoNumero = 0, result = 0;
        int trovato = 0;
        try {
            //Lettura della prima riga e creazione lista dei numeri usciti
            filein = new BufferedReader(new FileReader("dati.txt"));
            riga = filein.readLine();
            Numbers = new ArrayList<String>(Arrays.asList(riga.split(",")));
            riga = filein.readLine();
            while(riga != null){
                riga = filein.readLine();
                if(riga != null){
                    if(!(riga.isBlank())){
                        contaRiga++;
                        rigaBoard = new ArrayList<String>(Arrays.asList(riga.split("\\s+")));
                        rigaBoard.remove("");
                        Board.add(contaRiga, rigaBoard); 
                    }
                    else{
                        contaStrato++;
                        Boards.add(contaStrato, Board);
                        Board = new ArrayList<>(); 
                        contaRiga = -1;                                
                    }
                }
            }

            contaStrato++;
            Boards.add(contaStrato, Board);
            Board = new ArrayList<>(); 
            contaRiga = -1;
            filein.close();
        


            //Guardo tutti i numeri usciti 
            for(int n=0; n<Numbers.size(); n++){
                numeriUsciti++;
                for(int i=0; i<Boards.size() && trovato<Boards.size(); i++){
                    for(int j=0; j<Boards.get(i).size() && trovato<Boards.size(); j++){
                        for(int x=0; x<Boards.get(i).get(j).size() && trovato<Boards.size(); x++){
                            if(Integer.parseInt(Boards.get(i).get(j).get(x)) == Integer.parseInt(Numbers.get(n).trim())){
                                Boards.get(i).get(j).set(x,"-1");
                            }
                        }
                    }
                }
                
                //Ogni volta che esce un numero e setto a null i numeri usciti, controllo se ci sono vincitori ma solo se almeno 5 numeri 
                //sono usciti
                
                if (numeriUsciti >= 5){
                    //Controllo per righe
                    for(int i=0; i<Boards.size(); i++){
                        for(int j=0; j<Boards.get(i).size(); j++){
                            contaNull = 0;
                            for(int x=0; x<Boards.get(i).get(j).size(); x++){
                                if(Integer.parseInt(Boards.get(i).get(j).get(x)) == -1 ){
                                    contaNull++;
                                }
                            }
                            if(contaNull == 5 && trovato<Boards.size()){
                                trovato++;
                                winnerBoard = i;
                                ultimoNumero = Integer.parseInt(Numbers.get(n));
                                if(trovato != Boards.size()){
                                    for(int z=0; z<Boards.get(winnerBoard).size(); z++){
                                        for(int x=0; x<Boards.get(winnerBoard).get(z).size(); x++){
                                            Boards.get(winnerBoard).get(z).set(x, "-2");
                                        }  
                                    }
                                }
                                
                            }
                        }
                    }
                    //Controllo per colonne
                    for(int i=0; i<Boards.size(); i++){
                        for(int j=0; j<Boards.get(i).size(); j++){
                            contaNull = 0;
                            for(int x=0; x<Boards.get(i).get(j).size(); x++){
                                if(Integer.parseInt(Boards.get(i).get(x).get(j)) == -1){
                                    contaNull++;
                                }
                            }
                            if(contaNull == 5 && trovato<Boards.size()){
                                trovato++;
                                winnerBoard = i;
                                ultimoNumero = Integer.parseInt(Numbers.get(n));
                                if(trovato != Boards.size()){
                                    for(int z=0; z<Boards.get(winnerBoard).size(); z++){
                                        for(int x=0; x<Boards.get(winnerBoard).get(z).size(); x++){
                                            Boards.get(winnerBoard).get(z).set(x, "-2");
                                        }  
                                    }
                                }
                                
                                
                            }
                        }
                    }
                }
            }
            
            for(int j=0; j<Boards.get(winnerBoard).size(); j++){
                for(int x=0; x<Boards.get(winnerBoard).get(j).size(); x++){
                    if(Integer.parseInt(Boards.get(winnerBoard).get(x).get(j)) != -1){
                        result = result + Integer.parseInt(Boards.get(winnerBoard).get(x).get(j));
                    }
                }  
            }
            System.out.println(winnerBoard);
            System.out.println(ultimoNumero);
            System.out.println(result*ultimoNumero);           
            
        } catch (Exception e) {
            System.out.println(e);
        }



    }
}
