package taquin;

import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;


public class Main {


    public static void main(String[] args) {

        Instant debut = Instant.now();
        Fenetre f = new Fenetre();
        //long stopTime = System.currentTimeMillis();
        Instant fin = Instant.now();
        Duration duree = Duration.between(fin , debut );
        double EnSecondes = duree.toSeconds();





       /*
        Duration d = Duration.ofHours(10);//10 heures
        Score s1 = new Score(10000,d);
        //creation du fichier de 3 scores

        ObjectOutputStream oos = null;
        FileOutputStream fichier=null;

        try
        {
            fichier = new FileOutputStream( "top3"  );
            oos = new ObjectOutputStream(fichier);
            //transfert des Formes du fichier vers la collection

                oos.writeObject(s1);
                oos.writeObject(s1);
                oos.writeObject(s1);
                oos.close();
                fichier.close();
        }
    catch(Exception e) {
        System.out.println("probleme pour creer le fichier top3");
    }


        */



    }
}
