import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private Clip soundtrack;

    public void loadSoundtrack(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            soundtrack = AudioSystem.getClip();
            soundtrack.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("soundtrack konnte nicht geladen werden");
        }
    }

    public void playSoundtrack() {
        if (soundtrack != null) {
            soundtrack.loop(Clip.LOOP_CONTINUOUSLY);
            soundtrack.start();
        }
    }

    public void pauseSoundtrack() {
        if (soundtrack != null && soundtrack.isRunning()) {
            soundtrack.stop();
        }
    }

    public void stopSoundtrack() {
        if (soundtrack != null) {
            soundtrack.stop();
            soundtrack.close();
        }
    }
}