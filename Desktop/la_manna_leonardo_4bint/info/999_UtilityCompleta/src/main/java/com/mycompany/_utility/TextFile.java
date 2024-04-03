/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._utility;

import java.io.*;

/**
 * rappresenta un file di testo .
 * consente di scrivere una stringa sul file di testo o di leggere una stringa dal file di testo 
 * Quando viene istanziato il file di testo puo essere aperto:
 * - in lettura in append
 * - in scrittura in append 
 * -in scrittura non in append
 * - in lettura 
 * consente di chiudere il file.
 * @author Studente
 */
public class TextFile
{
    private char mode; //R=reader/lettura - W=writer/scrittura
    private BufferedReader reader;
    private BufferedWriter writer;
    
    /**
     * Costruttore (non in append)
     * @param nomeFile pathnmae del file fisico da leggere/scrivere
     * @param mode modalità apertura del file, può assumere i seguenti valori 
     * - W o w per aprire il file in scrittura 
     * - qualsiasi altra lettera per aprire il file in lettura 
     * @throws FileNotFoundException Viene sollevata se il file da leggere non viene trovato.
     * se aperto in scrittura e non viene trovato esso viene creato 
     * 
     * @throws IOException Viene sollevata se nn è possbile accedere al file 
     */
    public TextFile(String nomeFile, char mode) throws FileNotFoundException, IOException
    {
	this.mode='R'; //modalità di default
	if(mode=='W' || mode=='w')
	    this.mode='W';
	if(this.mode=='R')
	    reader=new BufferedReader(new FileReader(nomeFile));
	else
	    writer=new BufferedWriter(new FileWriter(nomeFile));
    }
    
    /**
     * Costruttore (con append)
     * @param append se vale true il file aperto in scrittura è aperto 
     * in append , altrimenti viene aperto non in append
     * @param nomeFile pathnmae del file fisico da leggere/scrivere
     * @param mode modalità apertura del file, può assumere i seguenti valori 
     * - W o w per aprire il file in scrittura 
     * - qualsiasi altra lettera per aprire il file in lettura 
     * @throws FileNotFoundException Viene sollevata se il file da leggere non viene trovato.
     * se aperto in scrittura e non viene trovato esso viene creato 
     * 
     * @throws IOException Viene sollevata se nn è possbile accedere al file 
     */
    public TextFile(String nomeFile, char mode, boolean append) throws FileNotFoundException, IOException
    {
	this.mode='R'; //modalità di default
	if(mode=='W' || mode=='w')
	    this.mode='W';
	if(this.mode=='R')
	    reader=new BufferedReader(new FileReader(nomeFile));
	else
	    writer=new BufferedWriter(new FileWriter(nomeFile,append));
    }
    
    /**
     * Scrive una stringa su file 
     * @param line la stringa da scrivere
     * @throws FileException viene sollevata se il file è aperto in lettura
     * @throws IOException  Viene sollevata se nn è possbile accedere al file
     */
    public void toFile(String line) throws FileException, IOException
    {
	if(mode=='R')
	    throw new FileException("Errore: file aperto in lettura");
	writer.write(line);
	writer.newLine();
    }
    
    /**
     * Legge dal File e restutuisce la stringa letta 
     * la prima  volta che viene invocato questo metodo viene 
     * letta la prima riga del file in seguito la successiva all'interno del file 
     * @return la stringa letta 
     * @throws FileException viene sollevata se il file è aperto in scrittura e anche
     * quando viene raggiutna la fine del file 
     * @throws IOException  Viene sollevata se nn è possbile accedere al file
     */
    public String fromFile() throws FileException, IOException
    {
	if(mode=='W')
	    throw new FileException("Errore: file aperto in scrittura");
	String s=reader.readLine();
	if(s==null)
	    throw new FileException(" fine del file");
	return s;
    }
    
    /**
     * Chiude il file
     * @throws IOException  Viene sollevata se nn è possbile accedere al file
     */
    public void closeFile() throws IOException
    {
	if(mode=='R')
	    reader.close();
	else
	    writer.close();
    }
}