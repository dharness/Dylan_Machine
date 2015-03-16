import java.util.*;

class Application {

   public static void main(String args[]) {
	   
   	DylanMachineBuilder dylanMachineBuilder = new DylanMachineBuilder();
   	ArrayList<String> instructions = dylanMachineBuilder.readInstructions();
   	HashMap<Integer, State> states = dylanMachineBuilder.buildMachine(instructions);
   	ArrayList<Integer> finalStates = dylanMachineBuilder.getFinalStates();
   	ArrayList<HashMap<Integer, Character>> tapes = dylanMachineBuilder.selectTape();
   	
   	// At this point, we have our state machine set up
   	// let's run some input through it
   	DylanMachine dylanMachine = new DylanMachine(states, finalStates);
   	for(HashMap<Integer, Character> tape : tapes){
   		dylanMachine.run(tape);
   	}
   	
   	
   }


}
