

package snake.game.objects.sound;


import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;
import snake.game.enums.ActionResult;
import snake.game.listeners.MoveResultListener;


public class WavPlayer implements MoveResultListener{

    private Clip backGroundClip; 
    private Clip moveClip; 

    public WavPlayer() {
        try {
            backGroundClip = AudioSystem.getClip();
            moveClip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(WavPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startBackgroundMusic() {
        String soundName = "background.wav";

        playSound(soundName, true, backGroundClip, false);
    }
    
    public void stopBackgoundMusic(){
        backGroundClip.stop();
        backGroundClip.close();
    }
    
    public void playSound(String name, final boolean loop){
        try {
            playSound(name, loop, AudioSystem.getClip(), false);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(WavPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void playSound(String name, final boolean loop, final Clip clip, final boolean stopPrev) {

        try {
            
            if (name==null){
                return;
            }

            URL sound = this.getClass().getResource("/snake/sounds/"+name); 
            
            final AudioInputStream ais = AudioSystem.getAudioInputStream(sound);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (stopPrev && clip != null) {
                            clip.stop();
                            clip.close();
                        }

                        clip.open(ais);

                        if (loop) {
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        } else { 
                            clip.start();
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(WavPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(WavPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        if (ais != null) {
                            try {
                                ais.close();
                            } catch (IOException ex) {
                                Logger.getLogger(WavPlayer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                }
            });


        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(WavPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WavPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playWinSound(){
        playSound("win.wav", false, moveClip, true);
    }
    
    public void playLoseSound(){
        playSound("die.wav", false, moveClip, true);
    }
    
    public void playStartSound(){
        playSound("treasure.wav", false, moveClip, true);
    }
    
    @Override
    public void doAction(ActionResult res) {
        switch(res) {
            case BAD:

                break;
            case TARGET:
                playSound("treasure.wav", false, moveClip, true);
                break;
            default:
                break;
        }
    }
}
