package util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import engine.main.RPGEngine;
import GameView.GameFrame;

public class Music {
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    private Thread myThread;
    private GameFrame myGame;
    
    public static void main(String[] args) {
    	GameFrame game = new GameFrame();
    	Music music = new Music("\\Users\\Peter\\Documents\\Duke\\2013-2014 Junior Year\\Spring\\COMPSCI 308\\workspace\\oogasalad_Team2\\src\\music\\pokeTest.wav", game);
    	game.setMusicOn();
    	    	
    	music.start();
    }
    
    public Music(String filename, GameFrame game) {
    	myGame = game;
    	setSound(filename);
    }
    
    public void setSound(String filename) {
    	try {
    		soundFile = new File(filename);
    		System.out.println(soundFile.getAbsolutePath());
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		System.exit(1);
    	}
    }
    
    public void start() {
    	if (!myGame.musicOn())
    		return;
    	myThread = getALoopingThread();
    	myThread.start();
    }
    
    public void stop() {
        if (myThread != null) {
            myThread.stop();
        }
    }
    
    private Thread getALoopingThread() {
    	return new Thread() {
    		@Override
    		public void run() {
    			loopForever();
    		}
    	};
    }
    
    private void loopForever() {
    	while (true) {
    		playSound();
    	}
    }
    
    private void playSound () {
        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[Constants.BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }

}
