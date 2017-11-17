package fr.amanzegouarene.common.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by amanzego on 06/09/2017.
 */
public class TestEDT extends JFrame implements ActionListener {
    public TestEDT() {
        super("Freeze");
        JButton freezer = new JButton("Freeze");
        TextArea textArea = new TextArea("Text");
        freezer.addActionListener(this);
        add(textArea);
        add(freezer);
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        /*try {
          Thread.sleep(4000);
        } catch (InterruptedException ex) {
        }*/
        new Thread(new Runnable() {
            public void run() {
                String text = "readHugeFile()";
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        // textArea.setText(text);
                    }
                });
                
            }
        }).start();
    }

    public static void main(String... args) {
        TestEDT edt = new TestEDT();
        edt.setVisible(true);
    }
}
