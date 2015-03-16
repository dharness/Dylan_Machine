import java.util.Collections; 
import java.util.HashMap; 

public class State {

	int id;
	// let k= the input, and v=[nextState, writeSymbol, direction]
	HashMap<Character, char[]> transitions;

	// When creating a state initially, we only add one transition
	public State(int id, char inputSymbol, char nextState, char writeSymbol, char direction){
		transitions = new HashMap<Character, char[]>();
		this.id = id;
		char[] chico = {nextState, writeSymbol, direction};
		transitions.put(inputSymbol, chico);
		// System.out.println(transitions);
	}

	//If a state already exists, we can add a transition as we see fit
	public void addTransition(char inputSymbol, char nextState, char writeSymbol, char direction){
		char[] chico = {nextState, writeSymbol, direction};
		transitions.put(inputSymbol, chico);
	}
	
	public char getNextState(char input){
		char[] transition = this.transitions.get(input);
		if(transition != null){ //if the input is valid, transition
//			System.out.println(transition);
			return transition[0];
		}else return '_';
	}
	
	// returns underscore '_' if you should not transition
	public char shouldWrite(char input){
		char[] transition = this.transitions.get(input);
		if(transition != null){ //if the input is valid, transition
			return transition[1];
		}else return '_';
	}
	
	public char getMovement(char input){
		char[] transition = this.transitions.get(input);
		if(transition != null){ //if the input is valid, transition
			return transition[2];
		}else return '_';
	}

	
	public String toString(){
		
		 String mapContents = "";
		for (Character c : this.transitions.keySet()) {
		    mapContents += "on:" + c + "\n goto: " + this.transitions.get(c)[0] + "\n dir: " + this.transitions.get(c)[2];
		}
		return "{ id: " + (char)this.id + "\n " + mapContents + "}";
	}

}
