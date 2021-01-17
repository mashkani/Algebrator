package ui;

import model.CongruenceClass;
import model.FiniteGroup;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


// Algebrator console application
public class Algebrator {
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/finiteGroup.json";
    private FiniteGroup finiteGroup;

    // EFFECTS: runs the Algebrator application
    public Algebrator() throws FileNotFoundException {
        init();
        runAlgebrator();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runAlgebrator() {
        displayMenu();
        String command = input.next().toLowerCase();
        processCommand(command);

        System.out.println("goodbye");
    }

    // MODIFIES: this
    // EFFECTS: initializes set
    private void init() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        //set = new HashSet<>();
        finiteGroup = new FiniteGroup(new HashSet<>(),1);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tg -> Finite Group Calculator");
        System.out.println("\tr -> Finite Ring Calculator");
        System.out.println("\tf -> Finite Field Calculator");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "g":
                setUpFiniteGroup();
                break;
            case "r":
                setUpFiniteRing();
                break;
            case "f":
                setUpFiniteField();
                break;
            case "q":
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up set and operation
    private void setUpFiniteGroup() {
        loadFiniteGroup();
        setUpSet();
        setUpOperation();
        resetFiniteGroup();
        saveFiniteGroup();
        loadFiniteGroup();
        printCongruenceClasses();
        printOperation();
    }

    // MODIFIES: this
    // EFFECTS: enables user to input integer elements to define their set of congruence classes
    private void setUpSet() {
        System.out.print("\t Enter the integer representations of the congruence classes in your set");
        System.out.println("\n\t or type \"c\" to continue");
        System.out.println("\n\t Instructions: ");
        System.out.println("\t Type each integer followed by the enter key");
        System.out.println("\t Type . followed by the enter key to terminate");

        Set<Integer> integerSet = new HashSet<>();
        String s = input.next();

        if (!input.equals("c")) {
            integerSet.add(Integer.parseInt(s));
            while (this.input.hasNext()) {
                try {
                    integerSet.add(Integer.parseInt(this.input.next()));
                } catch (NumberFormatException exception) {
                    break;
                }
            }

            System.out.println("your set is " + integerSet);
            for (int i : integerSet) {
                CongruenceClass cc = new CongruenceClass(i);
                finiteGroup.addElement(cc);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the operation to addition or multiplication
    private void setUpOperation() {
        System.out.println("\t Enter 1 for addition or 2 for multiplication");
        System.out.println("\n\t or type \"c\" to continue");

        String s = input.next();

        if (!s.equals("c")) {
            if (Integer.parseInt(s) == 1) {
                finiteGroup.setOperation(1);
                System.out.println("you choose addition");
            } else if (Integer.parseInt(s) == 2) {
                finiteGroup.setOperation(2);
                System.out.println("you choose multiplication");
            } else {
                System.out.println("invalid selection");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: brings up setUpGroup() if user types "reset"
    private void resetFiniteGroup() {
        System.out.println("\t type \"reset\" to redefine your set and operation");
        System.out.println("\t otherwise type \"done\"");

        if (input.next().equals("reset")) {
            finiteGroup = new FiniteGroup(new HashSet<>(),1);
            setUpFiniteGroup();
        }
    }

    // EFFECTS: saves the finiteGroup to file
    private void saveFiniteGroup() {
        System.out.println("\t type \"s\" to save defined set and operation to file");
        System.out.println("\t or type \"c\" to continue");

        if (input.next().equals("s")) {
            try {
                jsonWriter.open();
                jsonWriter.write(finiteGroup);
                jsonWriter.close();
                System.out.println("Saved set and operation to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads finiteGroup from file
    private void loadFiniteGroup() {
        System.out.println("\t type \"l\" to load defined set and operation from file");
        System.out.println("\t or type \"c\" to continue");

        if (input.next().equals("l")) {
            try {
                finiteGroup = jsonReader.read();
                System.out.println("Loaded finiteGroup from " + JSON_STORE);
                printCongruenceClasses();
                printOperation();
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // EFFECTS: prints all the congruence classes in the set to the console
    private void printCongruenceClasses() {
        Set<CongruenceClass> set = finiteGroup.getSet();
        Set<Integer> integerSet = new HashSet<>();
        for (CongruenceClass c : set) {
            integerSet.add(c.getCongruenceClass());
        }
        System.out.println("set : " + integerSet);
    }

    // EFFECTS: prints the operation to the console
    private void printOperation() {
        System.out.println("operation : " + finiteGroup.getOperation());
    }

    // MODIFIES: this
    // EFFECTS: sets up set and the two operations
    private void setUpFiniteRing() {
        System.out.print("Functionality not yet available, ");
    }

    // MODIFIES: this
    // EFFECTS: sets up set and the two operations
    private void setUpFiniteField() {
        System.out.println("Functionality not yet available, ");
    }
}