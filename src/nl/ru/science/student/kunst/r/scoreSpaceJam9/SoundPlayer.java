package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

	private File file;
	private boolean loop;
	
	private Clip clip;
	
	public SoundPlayer(String filename, boolean loop) {
		file = new File("res" + File.separator + "audio" + File.separator + filename + ".wav");
		this.loop = loop;
	}
	
	public void play() {
		try {
			AudioInputStream in = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(in);
			if (loop) {
				clip.loop(Integer.MAX_VALUE);
			}
			else {
				clip.start();
			}
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		clip.stop();
	}

}
