package taquin;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlayWave2 extends Thread {


        private String filename;
        private taquin.PlayWave.Position curPosition;
        private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
        enum Position {LEFT, RIGHT, NORMAL};

        public PlayWave2(String wavfile) {
            filename = wavfile;
            curPosition = taquin.PlayWave.Position.NORMAL;
        }



        public void run() {//
            File soundFile = new File(filename);
            if (!soundFile.exists()) {
                System.err.println("Wave file not found: " + filename);
                return;
            }

            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }

            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine auline = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            if (auline.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
                if (curPosition == taquin.PlayWave.Position.RIGHT) {
                    pan.setValue(1.0f);
                } else if (curPosition == taquin.PlayWave.Position.LEFT) {
                    pan.setValue(-1.0f);
                }
            }

            auline.start();
            int nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

            try {
                while (nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0) {
                        auline.write(abData, 0, nBytesRead);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } finally {
                auline.drain();
                auline.close();
            }

            this.run();
        }//run


    }

