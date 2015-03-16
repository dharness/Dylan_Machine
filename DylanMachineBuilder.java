import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.*;

public class DylanMachineBuilder{

	//Attributes
	ArrayList<String> transitions;
	ArrayList<Integer> finalStates;
	ArrayList<String> inputs;

	public DylanMachineBuilder(){

		transitions = new ArrayList<String>();
		finalStates = new ArrayList<Integer>();
		inputs = new ArrayList<String>();
	}
	
	public ArrayList<Integer> getFinalStates(){
		return this.finalStates;
	}
	
	// Listen function responsible for getting the filename and validating its existence
	public ArrayList<String> readInstructions(){

		boolean noFile = true;
		ArrayList<String> bluePrint = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		while(noFile){

			System.out.println("Enter a file name for testing:");
			String fileName = scanner.nextLine();

			try{
				bluePrint = (ArrayList<String>)Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
				noFile = false;
			}catch(java.io.IOException e){
				System.out.println(e);
			}
		}
		//scanner.close();
		return bluePrint;
	}

	// Build and configure all our states
	public HashMap<Integer, State> buildMachine(ArrayList<String> instructions){

		// Sort all the data
		for(String line : instructions){
			//sort the data based on type
			if(line.charAt(0) == 't'){
				this.transitions.add(line);
			}else if(line.charAt(0) == 'f'){
				line = line.replaceAll("f ", "");
				
				String[] spaceSplit = line.split("\\s+");
				for(String s : spaceSplit){
//					if(s != "f"){
						this.finalStates.add(Integer.parseInt(s));
//					}
				}
				
			}else if(line.charAt(0) == 'i'){
				this.inputs.add(line);
			}
		}

		return constructStates(this.transitions);
	}

	public HashMap<Integer, State> constructStates(ArrayList<String> stateData){

		HashMap<Integer, State> states = new HashMap<Integer, State>();

		// set up a collection of states with the appropriate transitions
		for(String datum : stateData){
			int testID = Character.getNumericValue(datum.charAt(2));
			State s = states.get(testID);//pull out state by id
			if(s != null){
				s.addTransition(datum.charAt(4), datum.charAt(6), datum.charAt(8), datum.charAt(10));
			}else{ // if the state doesn't exist, create it
				s = new State((int)datum.charAt(2), datum.charAt(4), datum.charAt(6), datum.charAt(8), datum.charAt(10));
				int id = Character.getNumericValue(datum.charAt(2));
//				System.out.println(id);
				states.put(id, s);
			}
		}

		return states;

	}

	public ArrayList<HashMap<Integer, Character>> selectTape(){
//		System.out.println(inputs.size() + " tapes detected. Which one would you like to run?");
//		Scanner scanner = new Scanner(System.in);
//		int tapeNumber = scanner.nextInt();
//		System.out.println("Selecting tape " + tapeNumber);
		ArrayList<HashMap<Integer, Character>> tapes = new ArrayList<HashMap<Integer, Character>>();
		
		
		for(int i =0; i < inputs.size(); i++){
			tapes.add(buildTape(i));	
		}
		return tapes;
	}
	
	// A tape will just be a HashMap with the inputs put into it - it is defined to start @0
	private HashMap<Integer, Character> buildTape(int tapeNumber){
		
		String tapeString = this.inputs.get(tapeNumber);
		HashMap<Integer, Character> tape = new HashMap<Integer, Character>();
		
		// ignore the 'i ' first 2 chars of the string
		for(int i=2; i < tapeString.length(); i++){
			tape.put(i-2, tapeString.charAt(i));
		}
		return tape;
	}

}