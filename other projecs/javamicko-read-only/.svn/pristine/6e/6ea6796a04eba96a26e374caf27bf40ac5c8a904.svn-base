/* 
	Author: Aleksandar Milenkovic 56/05
	Rachunarski Fakultet (RAF), Univerzitet Union, 2008
	Prerada mikro c kompajlera u Javu
	
	SVN: http://code.google.com/p/javamicko/
	
   	Glavni program za MiCKo koji se sastoji od 
   	Lexera, Parsera i generisanja koda
   	(generated with JLex, CUP and my own two hands)

*/
//JFlex - The Fast Scanner Generator for Java
//home page: 
//http://www.jflex.de/  
//
//CUP - LALR Parser Generator for Java
//home page: 
//http://www2.in.tum.de/projects/cup
//
//Classgen Class Generator for Java
//home page:
//http://classgen.sf.net

package main;

import java.io.*;

import java_cup.parser;
import JFlex.*;


public class MainSample {


	 /**
	   * Runs the parser on an input file.
	   *
	   *
	   * @param argv   the command line, argv[0] is the filename to run
	   *               the parser on.
	   */
	  public static void main(String argv[]) 
	    throws java.io.IOException, java.lang.Exception
	 {
	    Lexer scanner = null;
	    
	    try {
	      scanner = new Lexer( new java.io.FileReader(argv[0]) );
	    }
	    catch (java.io.FileNotFoundException e) {
	      System.out.println("File not found : \""+argv[0]+"\"");
	      System.exit(1);
	    }
	    catch (java.io.IOException e) {
	      System.out.println("Error opening file \""+argv[0]+"\"");
	      System.exit(1);
	    }
	    catch (ArrayIndexOutOfBoundsException e) {
	      System.out.println("Usage : java Main <inputfile>");
	      System.exit(1);
	    }

	    try {
	      Parser p = new Parser(scanner);
	      Object result = p.parse().value;
	      System.out.println(result.toString());
/*	      IdentificationVisitor identification = new IdentificationVisitor();
	      ((Prog)result).accept( identification );
	      System.out.println("Code:\n");
	      EmitCodeVisitor emitcode = new EmitCodeVisitor();
//	     ((Prog)result).accept( emitcode );
	      ((Prog)result).traverseBottomUp( emitcode );
*/
	    }
	    catch (java.io.IOException e) {
	      System.out.println("An I/O error occured while parsing : \n"+e);
	      System.exit(1);
	    }
	  }
	}