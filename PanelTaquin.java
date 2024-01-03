package taquin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PanelTaquin extends JPanel {

    private Image images[][] ;
    private  int ColonneTaquin=-1;
    private int LigneTaquin=-1;
    private List<Integer> list;

    public PanelTaquin() {
        images = new Image [3][3];
        this.setLayout(new GridLayout(3,3));

        list = Arrays.asList(1,2,3,4,5,6,7,0,8);
        //Collections.shuffle(list);
        int L=0;
        int C=0;

        for(Integer i : list )
        {

            images[L][C]=new Image(".\\src\\taquin\\img\\nature\\33\\"+i.intValue()+".bmp",i.intValue());
            if (i.intValue()==0) {LigneTaquin=L;ColonneTaquin=C;}
            this.add(images[L][C++]);

            if (C==3) {L++;C=0;}
        }
        System.out.println("LigneTaquin="+LigneTaquin+" - ColonneTaquin="+ColonneTaquin);





    }

    @Override
    protected void paintComponent(Graphics g) {
        int L=0;
        int C=0;

        this.removeAll();

    do {
            this.add(images[L][C++]);
            if (C == 3) {
                L++;
                C = 0;
            }
        }while (L!=3 && C!=3);

        this.revalidate();

    }


    public Image[][] getImages() {
        return images;
    }

    public int getColonneTaquin() {
        return ColonneTaquin;
    }

    public int getLigneTaquin() {
        return LigneTaquin;
    }

    public void setColonneTaquin(int colonneTaquin) {
        ColonneTaquin = colonneTaquin;
    }

    public void setLigneTaquin(int ligneTaquin) {
        LigneTaquin = ligneTaquin;
    }
}
