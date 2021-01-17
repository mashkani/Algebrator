package ui.gui;

import model.CongruenceClass;
import model.FiniteGroup;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import java.io.File;
import java.io.FileNotFoundException;

public class InputPanel extends JPanel {

    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);
    private static final String JSON_STORE = "./data/finiteGroup.json";



    private final JTextField setField = new JTextField(10);
    private final JTextField operationField = new JTextField(10);

    private final EventListenerList listenerList = new EventListenerList();

    JButton addButton = new JButton("Add");
    JButton saveButton = new JButton("Save");
    JButton loadButton = new JButton("Load");
    JButton resetButton = new JButton("Reset");


//    String set = setField.getText();
//    String operation = operationField.getText();
    Set<Integer> integerSet = new HashSet<>();
    FiniteGroup finiteGroup = new FiniteGroup(new HashSet<>(),1);

    public InputPanel() {
        Dimension size = getPreferredSize();
        size.width = 250;
        setPreferredSize(size);
        setBorder(BorderFactory.createTitledBorder("Input Panel"));

//        JLabel set = new JLabel("Set : ");
//        JLabel operation = new JLabel("Operation : ");
//
//        JTextField setField = new JTextField(10);
//        JTextField operationField = new JTextField(10);

//        JButton addButton = new JButton("Add");
        addButton.setFocusable(false);
        addButton.addActionListener(createAddButton());

//        JButton saveButton = new JButton("Save");
        saveButton.setFocusable(false);
        saveButton.addActionListener(createSaveButton());

//        JButton loadButton = new JButton("Load");
        loadButton.setFocusable(false);
        loadButton.addActionListener(createLoadButton());

//        JButton resetButton = new JButton("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(createResetButton());

        makeLayout();
    }

    // EFFECTS: action listener for the add button
    private ActionListener createAddButton() {
        ActionListener add = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String set = setField.getText();

                if (!set.equals("")) {
                    for (int i = 0; i < set.split(String.valueOf(',')).length; i++) {
                        integerSet.add(Integer.parseInt(set.split(String.valueOf(','))[i]));
                    }
                }

                // Store set in finiteGroup
                for (int i : integerSet) {
                    CongruenceClass c = new CongruenceClass(i);
                    finiteGroup.addElement(c);
                }

                // Set operation in finiteGroup
                if (operationField.getText().equals("*")) {
                    finiteGroup.setOperation(2);
                }
                finiteGroup.setOperation(1);

                String text = "(" + integerSet + ", " + operationField.getText() + ")" + "\n";
                fireDetailEvent(new InputEvent(this, "Added : " + text));

                playSound("data/button-3.wav");
            }
        };
        return add;
    }

    // EFFECTS: action listener for the save button
    private ActionListener createSaveButton() {
        ActionListener save = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(finiteGroup);
                    jsonWriter.close();
                    System.out.println("Saved set and operation to " + JSON_STORE);
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }

                String text = "( set : " + integerSet + " , operation: " + operationField.getText() + ")" + "\n";
                fireDetailEvent(new InputEvent(this, "saved : " + text));

                playSound("data/button-3.wav");
            }
        };
        return save;
    }

    // EFFECTS: action listener for the load button
    private ActionListener createLoadButton() {
        ActionListener load = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // loads logic
                try {
                    finiteGroup = jsonReader.read();
                    System.out.println("Loaded finiteGroup from " + JSON_STORE);
                    //printCongruenceClasses();
                    //printOperation();
                } catch (IOException exception) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }

                // prints finite group
                Set<CongruenceClass> set = finiteGroup.getSet();
                Set<Integer> integerSet = new HashSet<>();
                for (CongruenceClass c : set) {
                    integerSet.add(c.getCongruenceClass());
                }
                String text = "( set : " + integerSet + ", operation: " + operationField.getText() + ")" + "\n";

                fireDetailEvent(new InputEvent(this, "loaded : " + text));

                playSound("data/button-3.wav");
            }
        };
        return load;
    }

    // EFFECTS: action listener for the reset button
    private ActionListener createResetButton() {
        ActionListener reset = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Set<CongruenceClass> removalSet = new HashSet<>();
                for (CongruenceClass c : finiteGroup.getSet()) {
                    removalSet.add(c);
                    //finiteGroup.removeElement(c);
                }

                for (CongruenceClass c : removalSet) {
                    finiteGroup.removeElement(c);
                }

                finiteGroup.setOperation(1);
                integerSet = new HashSet<>();

                String text = "(" + integerSet + ", " + operationField.getText() + ")" + "\n";
                fireDetailEvent(new InputEvent(this, "reset : " + text));

                playSound("data/button-3.wav");
            }
        };
        return reset;
    }

    public void fireDetailEvent(InputEvent event) {
        Object[] listeners = listenerList.getListenerList();

        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == InputListener.class) {
                ((InputListener)listeners[i + 1]).detailEventOccurred(event);
            }
        }
    }

    // EFFECTS: adds detail listener
    public void addDetailListener(InputListener listener) {
        listenerList.add(InputListener.class, listener);
    }

    // EFFECTS: removes detail listener
    public void removeDetailListener(InputListener listener) {
        listenerList.remove(InputListener.class, listener);
    }

    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    //////////////

    // EFFECTS: creates layout of the input panel
    public void makeLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        layoutFirstColumn();
        layoutSecondColumn();
        layoutAddButton();
        layoutSaveButton();
        layoutLoadButton();
        layoutResetButton();
    }

    // EFFECTS: creates layout of first column
    public void layoutFirstColumn() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.LINE_END;

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        JLabel set = new JLabel("Set : ");
        add(set, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        JLabel operation = new JLabel("Operation : ");
        add(operation, gc);
    }

    // EFFECTS: creates layout of second column
    public void layoutSecondColumn() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.LINE_START;

        gc.gridx = 1;
        gc.gridy = 0;
        add(setField, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(operationField, gc);
    }

    // EFFECTS: creates layout of final row
    public void layoutAddButton() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.weighty = 10;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;

        gc.gridx = 0;
        gc.gridy = 5;
        add(addButton, gc);
    }

    public void layoutResetButton() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.weighty = 10;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;

        gc.gridx = 1;
        gc.gridy = 5;
        add(resetButton, gc);

    }

    // EFFECTS: creates layout of the save button
    public void layoutSaveButton() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.weighty = 10;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;

        gc.gridx = 0;
        gc.gridy = 6;
        add(saveButton, gc);
    }

    // EFFECTS: creates layout of the load button
    public void layoutLoadButton() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.weighty = 10;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;

        gc.gridx = 1;
        gc.gridy = 6;
        add(loadButton, gc);
    }
}

