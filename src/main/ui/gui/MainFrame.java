package ui.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private InputPanel inputPanel;

    public MainFrame(String title) {
        super(title);

        // Set layout manager
        setLayout(new BorderLayout());

        // create swing components
        JTextArea textArea = new JTextArea();

        inputPanel = new InputPanel();

        inputPanel.addDetailListener(new InputListener() {
            public void detailEventOccurred(InputEvent event) {
                String text = event.getText();
                textArea.append(text);
            }
        });

        // Add swing components to content pane
        Container container = getContentPane();
        container.add(textArea, BorderLayout.CENTER);
//        container.add(button, BorderLayout.SOUTH);                    //
        container.add(inputPanel, BorderLayout.WEST);


        // Add behaviour                                               //
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                textArea.append("TestButton\n");
//            }
//        });
    }
}
