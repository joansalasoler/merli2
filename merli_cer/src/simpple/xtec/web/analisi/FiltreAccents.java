package simpple.xtec.web.analisi;

import java.io.IOException;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

/**
 * 
 * Projecte: CercaCAT365
 * Classe: simpple.sise.analisi.FiltreAccents
 * 
 * Filtra
 * - accents/dieresis
 * - Majscules
 * - Apstrofs 
 * 
 * @author xguardiola - Simpple
 * 2004  Simpple. Tots els drets reservats
 *
 */

public class FiltreAccents extends TokenFilter {

	public FiltreAccents(TokenStream in) {
		super(in);
	}

	/** Returns the next input Token, removing accents and going lowercase */
	public final Token next() throws IOException {

		Token token = input.next();

		if (token == null)
			return null;

		else {

			String s = token.termText();

			StringBuffer sbuff = new StringBuffer();

			// Eliminem les Majscules
			s = s.toLowerCase();

			// Eliminem els accents
			for (int i = 0; i < s.length(); i++) {

				char c = s.charAt(i);

				switch (c) {

					case '�' :
						sbuff.append('e');
						break;
					case '�' :
						sbuff.append('e');
						break;
					case '�' :
						sbuff.append('e');
						break;						
					case '�' :
						sbuff.append('a');
						break;
					case '�' :
						sbuff.append('a');
						break;
					case '�' :
						sbuff.append('a');
						break;						
					case '�' :
						sbuff.append('i');
						break;
					case '�' :
						sbuff.append('i');
						break;
					case '�' :
						sbuff.append('o');
						break;
					case '�' :
						sbuff.append('o');
						break;
					case '�' :
						sbuff.append('o');
						break;						
					case '�' :
						sbuff.append('u');
						break;
					case '�' :
						sbuff.append('u');
						break;
//					case '�' :
//						sbuff.append('?');
//						break;						
					case '�' :
						break;						
				case '.':
					break;
				case ',':
					break;
				case ';':
					break;
				case ':':
					break;
					default :
						sbuff.append(c);
				}
			}

			s = sbuff.toString().toLowerCase();

			// Eliminem els apstrofs
			if (s.indexOf("'") == 1) {
				s = s.substring(2, s.length());
			}

			// retornem el token
			if (!s.equals(token.termText())) {
				return new Token(s, token.startOffset(), token.endOffset(), token.type());
			}
			return token;

		}
	}

}