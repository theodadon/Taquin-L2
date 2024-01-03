package taquin;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil  extends JDialog {


    public Accueil(JFrame parent,double  W,double  H) {

        super(parent,true);
        int w=524;
        int h=276;

        this.setLayout(new BorderLayout());
        this.setLocation((int)((W-w)/2),(int)((H-h)/2));
        this.setSize(w,h);


        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());

        Image im = new Image(".\\src\\taquin\\img\\taquin.jpg",-1);
        pan.add(im,BorderLayout.CENTER);
        this.add(pan,BorderLayout.CENTER);

    }


}
