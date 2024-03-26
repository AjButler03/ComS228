package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ParseDecode_Main {


	public static void main(String args[]) {
		// initializing variables
		Scanner scnr = new Scanner(System.in);
		Scanner scnr2;
		String encode_Key = "";
		String encoded_Data = "";
		MsgTree key_Tree;

		// getting fileName input
		System.out.println("Please enter filename to decode: ");
		String fileName = scnr.next();

		// creating loop for input validation
		while (true) {
			try {
				// attempting to grab file
				File f = new File(fileName);
				// leaving filename here for personal testing
				// src\cadbard.arch
				scnr2 = new Scanner(f);

				// iterating through lines in file; primarily to catch newline characters in the
				// encoding scheme by checking new lines that start with '^' char.
				String temp;
				encode_Key = scnr2.nextLine();
				while (scnr2.hasNextLine()) {
					// grabbing next line
					temp = scnr2.nextLine();
					if (temp.charAt(0) == '^') {
						// next line is also part of the 
						encode_Key = encode_Key + "\n" + temp;
					} else {
						encoded_Data += temp;
					}
				}

				key_Tree = new MsgTree(encode_Key);
				
				// printing results
				System.out.println("character     code");
				System.out.println("-------------------------");
				MsgTree.printCodes(key_Tree, "");
				System.out.println();
				key_Tree.decode(key_Tree, encoded_Data);
				
				
				// gathering statistics information in strings::
				DecimalFormat numberFormat = new DecimalFormat("#.0");
				String bitsCharAvg = numberFormat.format(MsgTree.codeBits / (MsgTree.numCodes * 1.0));
				String numChars = "" + MsgTree.numChars;
				double temp2 = (1 - MsgTree.compressedBits/(MsgTree.numChars * 16.0)) * 100;
				String savedSpace = "" + numberFormat.format(temp2) + "%";
				
				
				// printing formatted statistics:
				System.out.println();
				System.out.println("STATISTICS: ");
				System.out.println(String.format("%-20s%-5s", "Avg bits/char: ", bitsCharAvg));
				System.out.println(String.format("%-20s%-5s", "TotalCharacters: ", numChars));
				System.out.println(String.format("%-20s%-5s", "Space Savings: ", savedSpace));
				

				// decode successful; exiting loop (didn't crash at least).
				// love that this one line is preventing an infinite loop. Good design.
				break;

			} catch (FileNotFoundException e) {
				// issue finding file; asking user to try again or to quit.
				System.out.println("Error finding file; Check filepath");
				System.out.println("\nPlease try again or enter 'q' to quit: ");
				fileName = scnr.next();

			} catch (InputMismatchException e) {
				// issue reading file; asking user to try again or to quit.
				System.out.println("Error reading file; File may not be correct format");
				System.out.println("\nPlease try again or enter 'q' to quit: ");
				fileName = scnr.next();

			} catch (Exception e) {
				// some other issue occurred; just printing stack trace and getting new input
				System.out.println("Misc. error occurred; printing stacktrace \n");
				e.printStackTrace();
				System.out.println("\n\n\nPlease try again or enter 'q' to quit: ");
				fileName = scnr.next();

			} finally {
				// reseting variables
				encode_Key = "";
				encoded_Data = "";
				key_Tree = null;
				MsgTree.compressedBits = 0;
				MsgTree.staticCharIDX = 0;
				MsgTree.staticIDX2 = 0;
				MsgTree.currDepth = 0;
				MsgTree.numCodes = 0;
				MsgTree.numChars = 0;
				MsgTree.codeBits = 0;
				// checking if user entered 'q' to quit
				if (fileName.equals("q")) {
					// Exiting program
					System.out.println("\n\n\nQuitting . . . ");
					System.exit(1);
				}
			}
		}

		// closing scanners to prevent resource leak
		scnr.close();
		scnr2.close();
	}
}