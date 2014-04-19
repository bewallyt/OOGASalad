package util;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

<<<<<<< HEAD
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
=======
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Music {
	private File soundFile;
	private AudioInputStream myAudioInputStream;
	private AudioFormat myAudioFormat;
	private SourceDataLine mySourceDataLine;
	private Thread myThread;
	
	private int BUFFER_SIZE = 12800000;
	
	/**
	 * 
	 * @param filename String of the file path to the music file
	 */

	public Music(String filename) {
		setSound(filename);
	}
	
	/**
	 * 
	 * @param url URL of the file path to the music file
	 */
	
	public Music(URL url) {
		String fileURL = url.toString().substring(5);
		setSound(fileURL.replaceAll("%20", " "));
	}
	
	/**
	 * 
	 * @param filename Re set file path of the music file
	 */

	public void setSound(String filename) {
		try {
			myThread = getLoopingThread();
			soundFile = new File(filename);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Starts sound
	 */
	
	public void start() {
		myThread.start();
	}

	/**
	 * Stops sound
	 */
	
	@SuppressWarnings("deprecation")
	public void stop() {
		myThread.stop();
	}

	private Thread getLoopingThread() {
		return new Thread() {
			@Override
			public void run() {
				while(true)
					playSound();
			}
		};
	}

	private void playSound () {
		try {
			myAudioInputStream = AudioSystem.getAudioInputStream(soundFile);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		myAudioFormat = myAudioInputStream.getFormat();

		DataLine.Info info = new DataLine.Info(SourceDataLine.class, myAudioFormat);
		try {
			mySourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
			mySourceDataLine.open(myAudioFormat);
		}
		catch (LineUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		mySourceDataLine.start();

		int nBytesRead = 0;
		byte[] abData = new byte[BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {
				nBytesRead = myAudioInputStream.read(abData, 0, abData.length);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				@SuppressWarnings("unused")
				int nBytesWritten = mySourceDataLine.write(abData, 0, nBytesRead);
			}
		}

		mySourceDataLine.drain();
		mySourceDataLine.close();
	}
>>>>>>> master

}
