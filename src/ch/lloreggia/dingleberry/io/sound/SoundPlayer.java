package ch.lloreggia.dingleberry.io.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private boolean _isPlaying;

    public void playSound(String fileName) {
        if (_isPlaying) {
            return;
        }

        try {
            _isPlaying = true;
            File file = new File(fileName);
            final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                    _isPlaying = false;
                }
            });

            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();

            while (_isPlaying) {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } finally {
            _isPlaying = false;
        }
    }
}
