package cr.ac.una.clinicauna.util;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Luvara
 */
public class SoundUtil {

    public static void mouseHoverSound() {
        try {
            File soundFile = new File("src/main/resources/cr/ac/una/clinicauna/resources/media/sounds/hoverMouse.wav");

            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(soundFile));
            sound.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }

    public static void mouseEnterSound() {
        try {
            File soundFile = new File("src/main/resources/cr/ac/una/clinicauna/resources/media/sounds/enterSound.wav");

            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(soundFile));
            sound.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }

    public static void pressButtonSound() {
        try {
            File soundFile = new File("src/main/resources/cr/ac/una/clinicauna/resources/media/sounds/pressButton.wav");

            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(soundFile));
            sound.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }
    
    public static void errorSound() {
        try {
            File soundFile = new File("src/main/resources/cr/ac/una/clinicauna/resources/media/sounds/error.wav");

            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(soundFile));
            sound.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }
    
    public static void keyTyping() {
        try {
            File soundFile = new File("src/main/resources/cr/ac/una/clinicauna/resources/media/sounds/typing.wav");

            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(soundFile));
            sound.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }
}
