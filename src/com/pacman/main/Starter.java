package com.pacman.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.actions.EcouteurMusique;
import com.pacman.aire.ConfigurationPacman;
import com.pacman.constant.Constante;
import com.pacman.message.MessageRessources;

public final class Starter {

	private static final Log LOG = LogFactory.getLog(Starter.class);

	private Starter() {
		LOG.debug(new StringBuilder("Constructeur de la classe ").append(Starter.class).toString());
	}

	public static void main(final String[] args) {

		try {
			String isSoundGame = MessageRessources.getInstance(Constante.BUN_NAME_AIJEU).getMessage(Constante.IS_SOUND_GAME);

			// lancement de la musique
			if ("true".equals(isSoundGame)) {
				final EcouteurMusique ecouteurMusique = new EcouteurMusique();
				Thread threadMusique = new Thread(ecouteurMusique);
				threadMusique.start();
			}

			ConfigurationPacman configurationPacman = new ConfigurationPacman();
			configurationPacman.display();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			System.exit(1);
		}
	}

}
