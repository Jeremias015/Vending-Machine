package com.techelevator;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {


    public Sound(String location){
        playSound(location);
    }
    public static void playSound(String location){

        try
        {
            File audioFile= new File(location);
            if(audioFile.exists()){
                AudioInputStream audioInput= AudioSystem.getAudioInputStream(audioFile);
                Clip clip= AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
            else {
                System.out.println("Try Again.");
            }

        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
