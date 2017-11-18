package com.pacman.actions;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import javazoom.jl.player.Player;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe permettant de lancer la musique de fond.
 * 
 */
public final class EcouteurMusique implements Runnable {
	private static final Log LOG = LogFactory.getLog(EcouteurMusique.class);
	
	private static final EcouteurMusique ECOUTEUR = new EcouteurMusique();

	private static final int BUFFER_SIZE = 176400; // 44100 x 16 x 2 / 8
	
	private Player player;

	
	public static EcouteurMusique getInstance(){
		return ECOUTEUR;
	}
	
	/**
	 * Methode permettant d'executer le thread.<br/>
	 * 
	 */
	public void run() {
		try {
			
			this.player = new Player(EcouteurMusique.class
					.getResourceAsStream("/sound/musique.mp3"));

			while (true){
				player.play(BUFFER_SIZE);				
			}

		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
		}
	}	
}
