package com.pacman.aire;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * TextField that can be limited in size (max number of characters typed in)
 *
 * @author Karim.
 */

public class JLimitedTextField extends JTextField {

	/**
	 * Serial version UID.<br/>
	 */
	private static final long serialVersionUID = 8408621960295252139L;

	/**
	 * Constructor.<br/>
	 * @param maxLength Text maximum length (Number characters type in).<br/>
	 * @param size Component size.<br/>
	 */
	public JLimitedTextField(int maxLength,int size) {
		super(size);
		AbstractDocument doc = (AbstractDocument) getDocument();
		doc.setDocumentFilter(new TextLimiter(maxLength));
	}

	/**
	 * Text limiter class.<br/>
	 * @author Karim
	 *
	 */
	private class TextLimiter extends DocumentFilter {

		/** Text Maximum size **/
		private int max;

		/**
		 * Constructor.<br/>
		 * @param max Maximum size.<br/>
		 */
		public TextLimiter(int max) {

			this.max = max;

		}

		/**
		 * Method inserting string.<br/>
		 */
		public void insertString(DocumentFilter.FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {

			replace(fb, offset, 0, str, attr);

		}

		/**
		 * 
		 */
		public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String str, AttributeSet attrs) throws BadLocationException {
			int newLength = fb.getDocument().getLength() - length + str.length();
			if (newLength <= max) {
				fb.replace(offset, length, str, attrs);
			} else {
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}

}

