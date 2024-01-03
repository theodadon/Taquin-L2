package taquin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends JPanel {

    private BufferedImage vue;
    private int numero;

    public Image(String fic,int n) {
        super();
        numero=n;
        try {
            vue = ImageIO.read(new File(fic));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("SOUCI AU CHARGEMENT D'UNE IMAGE (" +fic   +")");
        }




    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(vue, 0, 0, null);
    }

    public int getNumero() {
        return numero;
    }
}

