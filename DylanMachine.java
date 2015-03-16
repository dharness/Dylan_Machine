import java.util.ArrayList;
import java.util.HashMap;

public class DylanMachine{
	
	HashMap<Integer, State> states;
	HashMap<Integer, Character> tape;
	ArrayList<Integer> finalStates;
	int currentStateID;
	int headLocation;
	
	public DylanMachine(HashMap<Integer, State> states, ArrayList<Integer> finalStates){
		this.states = states;
		this.finalStates = finalStates;
		currentStateID = 0; //start our machine at the 0th state
		headLocation = 0;
	}
	
	public void run(HashMap<Integer, Character> tape){
//		boolean living = true;
		
		for(;;){
			//print some info
			
			//gather some state info
			Character input  = tape.get(headLocation); //get the input
			State currentState = this.states.get(this.currentStateID); //get the current state
			if(input == null) input = 'Z';
			Character shouldWrite = currentState.shouldWrite(input); //get the symbol to write
			char dir = currentState.getMovement(input); //the direction to move
			int nextState = Character.getNumericValue(currentState.getNextState(input));//get the next state
			
			if(nextState == -1){
				printTape(tape);
				System.out.println("\nREJECTED");
				break;
			}
			writeToTape(tape, headLocation, shouldWrite);
			if(this.finalStates.contains(nextState) && dir == 'H'){
				printTape(tape);
				System.out.println("\nACCEPTED");
				break;
			}
			
			if(!moveHead(dir)) break; //Move the head
			this.currentStateID = nextState; //set the new state
			
			
			
//			System.out.println("Found " + input + " Writing " + shouldWrite + " , moving " + dir); //close up shop
//			System.out.println(currentState);
		}
		
		
	}
	
	public boolean writeToTape(HashMap<Integer, Character> tape, int headLocation, char shouldWrite){
		if(shouldWrite == 'Z') return false;
		else{
			tape.put(headLocation, shouldWrite);
			return true;
		}
	}
	
	public boolean moveHead(char dir){	
		boolean success = true;
		
		     if (dir == 'R')  { headLocation++; }
		else if (dir == 'L')  { headLocation--; }
		else                  { success = false;}
		return success;
	}

	public void printTape(HashMap<Integer, Character> tape){
//		System.out.println("----------------------------");
		for (HashMap.Entry<Integer, Character> entry : tape.entrySet()) {
//			String s ="";
//			if(entry.getKey() == headLocation) s = " <";
		    System.out.print(entry.getValue());
//		    s = "";
		}
//		System.out.println("----------------------------");
	}
}





