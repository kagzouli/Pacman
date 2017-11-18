package com.pacman.message;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pacman.constant.Constante;
import com.pacman.exception.TechnicalException;

/**
 * Classes pour recuperer les messages.
 * 
 * @author x587368
 * 
 */
public final class MessageRessources
{
	private static final Log LOG = LogFactory.getLog(MessageRessources.class);
    
    /**
     * Attribut pour recuperer les messages.<br/>
     */
    private static MessageRessources messages = new MessageRessources(Constante.BUN_NAME_AIJEU);

    /**
     * Recupere le bundle du fichier
     */
    private transient ResourceBundle bundle;
    
    
    protected MessageRessources(){
    	LOG.debug(new StringBuilder("Constructeur de la classe ").append(MessageRessources.class).toString());
        
    }

    /**
     * Constructeur par default.<br/>
     */
    protected MessageRessources(final String bundleName)
    {
        bundle = ResourceBundle.getBundle(bundleName);
    }

    /**
     * Methode permettant de recuperer une instance des messages.<br/>
     * @param bundleName Nom du fichier de propriete.<br/>
     * @return Retourne une instance des messages.<br/>
     */
    public static MessageRessources getInstance(final String bundleName)
    {
        return messages;
    }

    /**
     * Methode permettant de recuperer un message sous forme de String.<br/>
     * 
     * @param key Cle.<br/>
     * @return Retourne le message correspondant a la cle.<br/>
     * @throws TechnicalException Erreur technique.<br/>
     */
    public String getMessage(final String key) throws TechnicalException
    {
        String message = null;
        
        try{
            message = bundle.getString(key);
        } catch (Exception e)
        {
            throw new TechnicalException(e);
        }
        
        return message;
    }

    /**
     * Methode permettant de recuperer un message sous forme d'entier.<br/>
     * 
     * @param key Cle.<br/>
     * @return Retourne le message sous forme d'entier.<br/>
     * @throws TechnicalException Erreur technique.<br/>
     */
    public Integer getMessageInteger(final String key) throws TechnicalException
    {
        Integer messageInteger = null;
        try
        {
            final String message = bundle.getString(key);

            if ((message != null) && (!message.trim().equals("")))
            {
                messageInteger = Integer.parseInt(message);
            }
        } catch (Exception e)
        {
            throw new TechnicalException(e);
        }
        return messageInteger;
    }

}
