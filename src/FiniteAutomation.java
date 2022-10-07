import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
public class FiniteAutomation {

    public FiniteAutomation(String filename) {
        try(var myReader = new Scanner(new File(filename))){
            int alphabetCount = myReader.nextInt();
            int statesCount = myReader.nextInt();
            states = new ArrayList<>(statesCount);
            for(int i = 0; i < statesCount; i++)
                states.add(new State());
            startState = myReader.nextInt();
            int finCount = myReader.nextInt();
            while(finCount-- > 0)
                states.get(myReader.nextInt()).isFinal = true;
            int alphCount = 0;
            while(myReader.hasNext()){
                int stState = myReader.nextInt();
                String word = myReader.next();
                int endState = myReader.nextInt();
                if(!alphabet.contains(word)){
                    ++alphCount;
                    alphabet.add(word);
                }
                if(alphCount > alphabetCount)
                    throw new StackOverflowError("Too many words");
                states.get(stState).transitions.put(word, endState);
            }
            if(alphCount < alphabetCount)
                foundWrong = true;

        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            System.exit(-1);
        }

    }


    public boolean CheckLen(int len){
        rec(startState, len);
        return !foundWrong;
    }

    private void rec(int currState, int len) {
        if(foundWrong || len == 0 && states.get(currState).isFinal)
            return;
        if( len == 0 && !states.get(currState).isFinal){
            foundWrong = true;
            return;
        }
        for(var w : alphabet){
            if(foundWrong)
                return;
            if(!states.get(currState).transitions.containsKey(w)){
                foundWrong = true;
                return;
            }
            rec(states.get(currState).transitions.get(w), len - 1);
        }
    }
    class State{
        public boolean isFinal = false;
        public HashMap<String, Integer> transitions = new HashMap<>();
    }
    private boolean foundWrong = false;

    private int startState;
    private ArrayList<String> alphabet = new ArrayList<>();
    private ArrayList<State> states;
}