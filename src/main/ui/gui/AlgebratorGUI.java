package ui.gui;

import javax.swing.*;
import java.awt.*;

// Algebrator GUI
public class AlgebratorGUI {

    // constructs an AlgebratorGUI
    public AlgebratorGUI() {


        //SwingUtilities.invokeLater(new Runnable() {
        makePanel();
        run();


//            @Override
//            public void run() {
//                JFrame frame = new MainFrame("Algebrator");
//                frame.setSize(200, 220);                         // does not work
//                frame.setSize(new Dimension(1500, 5000));
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setVisible(true);
//
//                frame.add(makePanel());  // add panel to frame
//            }

//            // EFFECTS: creates the menu panel
//            public JPanel makePanel() {
//                JPanel p = new JPanel();
//
//                JButton closedButton = new JButton("Closed?");
//                closedButton.setFocusable(false);
//
//                JButton associateButton = new JButton("Associative?");
//                associateButton.setFocusable(false);
//
//                JButton identityButton = new JButton("Identity?");
//                identityButton.setFocusable(false);
//
//                JButton inverseButton = new JButton("Inverse?");
//                identityButton.setFocusable(false);
//
//                JButton groupButton = new JButton("Group?");
//                groupButton.setFocusable(false);
//
//
//                //closedButton.setSize(500,500);   //  does not work
//
//                p.add(closedButton);
//                p.add(associateButton);
//                p.add(identityButton);
//                p.add(inverseButton);
//                p.add(groupButton);
//
//                p.setBackground(Color.darkGray);
//                //p.setSize(500,500);            // does not work
//                return p;
//
//            }
//        });
    }

    //@Override
    public void run() {
        JFrame frame = new MainFrame("Algebrator");
        frame.setSize(200, 220);                         // does not work
        frame.setSize(new Dimension(1500, 5000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.add(makePanel());  // add panel to frame
    }


    // EFFECTS: creates the menu panel
    public JPanel makePanel() {
        JPanel p = new JPanel();

        JButton closedButton = new JButton("Closed?");
        closedButton.setFocusable(false);

        JButton associateButton = new JButton("Associative?");
        associateButton.setFocusable(false);

        JButton identityButton = new JButton("Identity?");
        identityButton.setFocusable(false);

        JButton inverseButton = new JButton("Inverse?");
        identityButton.setFocusable(false);

        JButton groupButton = new JButton("Group?");
        groupButton.setFocusable(false);


        //closedButton.setSize(500,500);   //  does not work

        p.add(closedButton);
        p.add(associateButton);
        p.add(identityButton);
        p.add(inverseButton);
        p.add(groupButton);

        p.setBackground(Color.darkGray);
        //p.setSize(500,500);            // does not work
        return p;

    }
}
























































//    static JTextField l; // create a text field
//    String s0; // store operator and operands
//    String s1; // store operator and operands
//    String s2; // store operator and operands



//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // set look and feel
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//
//        //AlgebratorGUI algebrator = new AlgebratorGUI();  // create a object of class
//        l = new JTextField(16);  // create a text field
//        l.setEditable(false);  // set the text field to non editable
//
//        // create number buttons
//        JButton b0 = new JButton("0");
//        JButton b1 = new JButton("1");
//        JButton b2 = new JButton("2");
//        JButton b3 = new JButton("3");
//        JButton b4 = new JButton("4");
//        JButton b5 = new JButton("5");
//        JButton b6 = new JButton("6");
//        JButton b7 = new JButton("7");
//        JButton b8 = new JButton("8");
//        JButton b9 = new JButton("9");
//
        //create operator buttons
//        JButton ba = new JButton("+");         //
//        JButton bm = new JButton("*");         //
//        JButton beq = new JButton("C");
//        JButton be = new JButton(".");
//
//        JPanel p = new JPanel();  // create a panel    //
//
          // add action listeners
//        bm.addActionListener(this);                      //
//        //bd.addActionListener(algebrator);
//        //bs.addActionListener(algebrator);
//        ba.addActionListener(this);                      //
//        b9.addActionListener(this);
//        b8.addActionListener(this);
//        b4.addActionListener(this);
//        b3.addActionListener(this);
//        b2.addActionListener(this);
//        b1.addActionListener(this);
//        b0.addActionListener(this);
//        be.addActionListener(this);
//        beq.addActionListener(this);
//
//        // add elements to panel
//        p.add(l);
//        p.add(b0);
//        p.add(b1);
//        p.add(b2);
//        p.add(b3);
//        p.add(b4);
//        p.add(b5);
//        p.add(b6);
//        p.add(b7);
//        p.add(b8);
//        p.add(b9);
//        p.add(ba);    //
//        p.add(bm);    //
//        p.add(be);
//        p.add(beq);
//
//        p.setBackground(Color.darkGray);   // set Background of panel  //
//
//
//
//
//
//    }
//
//
//    public void actionPerformed(ActionEvent e) {
//        String s = e.getActionCommand();
//
//        // if the value is a number
//        if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
//            // if operand is present then add to second no
//            if (!s1.equals("")) {
//                s2 = s2 + s;
//            } else {
//                s0 = s0 + s;
//            }
//            l.setText(s0 + s1 + s2);  // set the value of text
//        } else if (s.charAt(0) == 'C') {
//
//            s0 = s1 = s2 = ""; // clear the one letter
//            l.setText(s0 + s1 + s2);  // set the value of text
//        } else if (s.charAt(0) == '=') {
//
//            double te;
//
//            // store the value in 1st
//            if (s1.equals("+")) {
//                te = (Double.parseDouble(s0) + Double.parseDouble(s2));
//            } else if (s1.equals("-")) {
//                te = (Double.parseDouble(s0) - Double.parseDouble(s2));
//            } else if (s1.equals("/")) {
//                te = (Double.parseDouble(s0) / Double.parseDouble(s2));
//            } else {
//                te = (Double.parseDouble(s0) * Double.parseDouble(s2));
//            }
//
//            l.setText(s0 + s1 + s2 + "=" + te);  // set the value of text
//            s0 = Double.toString(te);  // convert it to string
//            s1 = s2 = "";
//
//        } else {
//            // if there was no operand
//            if (s1.equals("") || s2.equals("")) {
//                s1 = s;
//                // else evaluate
//            } else {
//                double te;
//
//                // store the value in 1st
//                if (s1.equals("+")) {
//                    te = (Double.parseDouble(s0) + Double.parseDouble(s2));
//                } else if (s1.equals("-")) {
//                    te = (Double.parseDouble(s0) - Double.parseDouble(s2));
//                } else if (s1.equals("/")) {
//                    te = (Double.parseDouble(s0) / Double.parseDouble(s2));
//                } else {
//                    te = (Double.parseDouble(s0) * Double.parseDouble(s2));
//                }
//
//                s0 = Double.toString(te);  // convert it to string
//                s1 = s;  // place the operator
//                s2 = "";   // make the operand blank
//            }
//            // set the value of text
//            l.setText(s0 + s1 + s2);
//        }
//    }
// }







