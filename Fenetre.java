package taquin;

import taquin.PlayWave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;

public class Fenetre  extends JFrame {

    private PanelTaquin leTaquin;
    private Thread playWave;
    private int WIDHT=650;
    private int HEIGHT=730;
    private Instant debut,fin;
    private int nbCoups;

    private boolean PASFINI;
    private PlayWave2 pendant;
    public Fenetre() throws HeadlessException {
        nbCoups=0;
        PASFINI=true;

         debut = Instant.now();


        //pour recuperer la resolution ecran
        double W = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double H = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        // pour centrer la fenetre
        this.setLocation((int)((W-1.0*WIDHT)/2),(int)((H-1.0*HEIGHT)/2));

        //lancement de la fenetre d'accueil et du generique
        Accueil ac = new Accueil(this,W,H);
        Thread generik=new PlayWave("./src/taquin/sons/debut.wav",ac );
        generik.start(); //à la fin de run, cette fenetre accueil sera fermée
        ac.setVisible(true);




    //le code ci dessous s'executera apres fermeture de la fenetre (JDIALOG) d'accueil
        this.setSize(WIDHT,HEIGHT);
        this.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // PANEL POUR AFFICHER LE NOMBRE DE COUPS


        //CREATION DU PANEL QUI SERA AU CENTRE DE LA FENETRE
        leTaquin= new PanelTaquin();
        this.add(leTaquin,BorderLayout.CENTER);

        //lancement de la musique pendant le jeu : la classe PlayWave2 est identique à PlayWave sauf que la méthode run se relance (pour jouer en boucle  le during.wav)
        pendant=new PlayWave2("./src/taquin/sons/during.wav" );
        pendant.start();

        //le barre de menu
        MenuBar mb;
        this.setMenuBar(mb =  new MenuBar());
        Menu m1 = new Menu("ACTIONS");
        Menu m2 = new Menu("DIMENSIONS");
        MenuItem m14 = new MenuItem("QUITTER");
        MenuItem m11 = new MenuItem("voir le TOP 3");
        MenuItem m21 = new MenuItem("3*3");
        m21.setEnabled(false);
        MenuItem m22 = new MenuItem("4*4");
        m1.add(m11);
        m1.add(m14);
        m2.add(m21);
        m2.add(m22);
        mb.add(m1);
        mb.add(m2);
        this.setMenuBar(mb);

        //gestion du menu QUITTER
        m14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        );




        this.setVisible(true);


        //pour ecouter le clavier : c'est la fenetre qui s'en occupe
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (PASFINI == true) {

                    int code = e.getKeyCode();
                    Image temp = null;
                    System.out.println("==>" + code);


                    switch (code) //pour gérer les 4 sens
                    {
                        case 37: //fleche gauche

                            if (leTaquin.getColonneTaquin() > 0) {
                                playWave = new PlayWave("./src/taquin/sons/glisse.wav");
                                playWave.start();

                                temp = leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin() - 1];
                                leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin() - 1] = leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin()];
                                leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin()] = temp;
                                leTaquin.setColonneTaquin(leTaquin.getColonneTaquin() - 1);
                                nbCoups++;


                            }
                            leTaquin.repaint();


                            break;
                        case 38: //fleche haut


                            if (leTaquin.getLigneTaquin() > 0) {
                                playWave = new PlayWave("./src/taquin/sons/glisse.wav");
                                playWave.start();
                                temp = leTaquin.getImages()[leTaquin.getLigneTaquin() - 1][leTaquin.getColonneTaquin()];
                                leTaquin.getImages()[leTaquin.getLigneTaquin() - 1][leTaquin.getColonneTaquin()] = leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin()];
                                leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin()] = temp;
                                leTaquin.setLigneTaquin(leTaquin.getLigneTaquin() - 1);
                                nbCoups++;

                            }
                            leTaquin.repaint();
                            break;

                        case 39: //fleche droite


                            if (leTaquin.getColonneTaquin() < 2) {
                                playWave = new PlayWave("./src/taquin/sons/glisse.wav");
                                playWave.start();
                                temp = leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin() + 1];
                                leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin() + 1] = leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin()];
                                leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin()] = temp;
                                leTaquin.setColonneTaquin(leTaquin.getColonneTaquin() + 1);
                                nbCoups++;


                            }
                            leTaquin.repaint();
                            break;
                        case 40: //fleche bas


                            if (leTaquin.getLigneTaquin() < 2) {
                                playWave = new PlayWave("./src/taquin/sons/glisse.wav");
                                playWave.start();

                                temp = leTaquin.getImages()[leTaquin.getLigneTaquin() + 1][leTaquin.getColonneTaquin()];
                                leTaquin.getImages()[leTaquin.getLigneTaquin() + 1][leTaquin.getColonneTaquin()] = leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin()];
                                leTaquin.getImages()[leTaquin.getLigneTaquin()][leTaquin.getColonneTaquin()] = temp;
                                leTaquin.setLigneTaquin(leTaquin.getLigneTaquin() + 1);
                                nbCoups++;

                            }
                            leTaquin.repaint();
                            break;

                    }
                    //verification du coup joué
                    boolean verdict = detectionVictoire();
                    if (verdict) { //si partie terminee
                        fin = Instant.now();
                        Duration duree = Duration.between(debut, fin);
                        double EnSecondes = duree.toSeconds();
                        pendant.stop();//méthode dépréciée (à changer aussitot que possible par une autre mais laquelle ... il faut chercher un peu)
                        //lancement de la musique de victoire/succès
                        playWave = new PlayWave("./src/taquin/sons/jeufini.wav");
                        playWave.start();
                        JOptionPane.showMessageDialog(null,
                                "GAGNE  TAQUIN résolu en" + EnSecondes + " secondes)");
                        //Score monScore = new Score(nbCoups,duree);
                        // lire le fichier des 3 scores et remplacer l'un de ces scores si NECESSAIRE
                        PASFINI = false;


                    } else System.out.println("NON pas victoire");
                }

            }  });


    }


public boolean detectionVictoire()
{
    for(int i=1;i<=9;i++)
    {
        if (leTaquin.getImages()[(i-1)/3][(i-1)%3].getNumero()!=(i%9)) {

            return false;
        }

    }
    return true;
}

}




