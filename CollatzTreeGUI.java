/*
* CS 3354
* Assignment 2
* Gabriel Carpio
*
* The following Program follows the guidelines given in the Instructions hand out
* except for the sorted list using the Comparator. All other guidelines are met.
*
* To compile - "javac CollatzTreeGUI.java
* To Run     - "java CollatzTreeGUI"
*/

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class CollatzTreeGUI
{
    public CollatzTreeGUI()  throws IOException
    {
        JFrame frame = new JFrame("Collatz Tree GUI");
        
        JPanel JP1 = new JPanel();
        JPanel JP2 = new JPanel();
        JPanel JP3 = new JPanel();
        JPanel JP4 = new JPanel();
        
        JTextField lfield = new JTextField("Enter L", 5);
        JTextField nfield = new JTextField("Enter N", 5);
        
        JButton Run = new JButton("Run");
        JButton Exit = new JButton("Exit");
        
        JRadioButton optionForward = new JRadioButton("Forward");
        JRadioButton optionBackward = new JRadioButton("Backward");

        ButtonGroup group = new ButtonGroup();
        group.add(optionForward);
        group.add(optionBackward);
        
        frame.setLayout(new FlowLayout());
        frame.setSize(200,150);
        
        JP1.add(optionForward);
        JP1.add(optionBackward);
        
        JP2.add(lfield);
        JP2.add(nfield);
        
        JP3.add(Run);
        JP4.add(Exit);
        
        Exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
            
        });

        Run.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
			{
                int n = Integer.parseInt(nfield.getText());
                int l = Integer.parseInt(lfield.getText());
                
                if (optionForward.isSelected())
                {
                    char chainDirection = 'f';
                    OutputSortedSuccessors(n,l,chainDirection);
                }
                else if (optionBackward.isSelected())
                {
                    char chainDirection = 'b';
                    OutputSortedSuccessors(n,l,chainDirection);
                }
            }
        });
        
        frame.add(JP1);
        frame.add(JP2);
        frame.add(JP3);
        frame.add(JP4);
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void OutputSortedSuccessors (int n, int l, char chainDirection)
    {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(n);
        
        try {
            Writer writer = new FileWriter("output.csv");
            while(l > 0)
            {
                int head = Q.remove();
                System.out.println(head); //
                writer.write(Integer.toString(head) + "\n");
                int[] succ = getSuccessor(head,chainDirection);
                for (int j = 0; j < succ.length; j++)
                {
                    Q.add(succ[j]);
                }
                l--;
            }
            writer.close();
            }
            catch(IOException e) {
                System.out.println(e);
                System.out.println("I/O Error ");
            }
    }

    public static int[] getSuccessor(int n, char c)
    {
        if (c == 'f') // Forward Chaining Process
        {
            if (n % 2 == 0 && (n - 1) % 3 == 0)
            {
                int[] j= {2 * n, (n - 1) / 3};
                return j;
            }
            else
            {
                int[] j = {2 * n};
                return j;
            }
        }
        else if (c == 'b') // Backward Chaining Process
        {
            if (n % 2 == 0)
            {
                int[] j = {n / 2};
                return j;
            }
            else
            {
                int[] j = {(n * 3) + 1};
                return j;
            }
        }
        else
        {
            return null;
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        CollatzTreeGUI t = new CollatzTreeGUI();
    }
}
