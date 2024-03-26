package edu.iastate.cs228.hw4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MsgTree {
	public char payloadChar;
	public MsgTree left;
	public MsgTree right;
	/*
	 * Can use a static char idx to the tree string for recursive solution, but it
	 * is not strictly necessary
	 */
	protected static int staticCharIDX = 0;
	// separate IDX for printCodes();
	protected static int staticIDX2 = 0;
	// statistics variables
	protected static int numCodes = 0;
	protected static int numChars = 0;
	protected static int codeBits = 0;
	protected static int compressedBits = 0;
	// keeping track of depth in tree when decoding
	// again for statistics purposes
	protected static int currDepth = 0;

// Constructor building the tree from a string
	public MsgTree(String encodingString) {
		// establishing value for root node
		payloadChar = encodingString.charAt(staticCharIDX);
		staticCharIDX++;

		// checking if should have children; serves as base case
		if (payloadChar == '^') {
			left = new MsgTree(encodingString);
			right = new MsgTree(encodingString);
		}
	}

// Constructor for a single node with null children
	public MsgTree(char payloadChar) {
		// honestly not sure why this constructor exists.
		// it was on the PDF, so I guess I'll leave it.
		this.payloadChar = payloadChar;
	}

	/**
	 * Parses through data, decodes based on msg tree. Throws inputMismatchException
	 * if data includes something other than 0 or 1.
	 * 
	 * @param data
	 * @return
	 */
	public void decode(MsgTree root, String encoded_Data) throws InputMismatchException {
		String msg = "";
		MsgTree currNode = root;

		// iterating through characters
		for (int i = 0; i < encoded_Data.length(); i++) {
			char c = encoded_Data.charAt(i);

			// checking that character is 0 or 1; throwing exception otherwise
			if (c == '0' || c == '1') {
				currDepth++;
				if (c == '0') {
					// moving to left child
					currNode = currNode.left;
				} else {
					// moving to right child
					currNode = currNode.right;
				}
				
				// checking payload for character
				if (currNode.payloadChar != '^') {
					msg += currNode.payloadChar;
					numChars++;
					compressedBits += currDepth;
					currNode = root;
					currDepth = 0;
				}

			} else {
				// data contains extraneous information, not in correct format. Throwing
				// InputMismatchException.
				throw new InputMismatchException();
			}
		}

		System.out.println("MESSAGE: ");
		System.out.println(msg);
	}

// method to print characters and their binary codes
	public static void printCodes(MsgTree root, String code) {
		MsgTree currNode = root;

		if (root.payloadChar == '^') {
			printCodes(currNode.left, code + "0");
			printCodes(currNode.right, code + "1");
		} else {
			String character = " " + currNode.payloadChar + " ";
			System.out.println(String.format("%-13s%-5s", character, code));
			numCodes++;
			codeBits += code.length();
		}

	}
}