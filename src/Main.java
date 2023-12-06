import javax.swing.*;

import static javax.swing.SwingUtilities.*;

public class Main {
    public static void main(String[] args) {
        playSoundtrack();
        new GameFrame();
    }
    private static void playSoundtrack() {
        SoundManager soundManager = new SoundManager();
        soundManager.loadSoundtrack("src/sounds/8bitAdventureSoundtrack.wav");
        soundManager.playSoundtrack();
    }
}
