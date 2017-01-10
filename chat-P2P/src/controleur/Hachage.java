package controleur;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Hachage {
	 public static String bytesToHex(byte[] b) {
	      char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				  'B', 'C', 'D', 'E', 'F' };
	      StringBuffer buf = new StringBuffer();
	      for (int j = 0; j < b.length; j++) {
		  buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
		  buf.append(hexDigit[b[j] & 0x0f]);
	      }
	      return buf.toString();
	    }




	    public static   byte[] gethashkey(String  monMessage)
	    {
	    
		byte[] hash = null;
		try {
		    // cree un objet de type hash, vide, le paramètre est l'algo  ici l'algo c'est MD5
		    MessageDigest ma_sequence = MessageDigest.getInstance("MD5");
		    //Ajoute les bytes de monMessage à la sequence à hacher
		    ma_sequence.update(monMessage.getBytes());
		    //calculre le hash 
		    hash = ma_sequence.digest();

		    return hash;
		}
		catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();

		}
		return hash;
		
	    }
	    public static   byte[] gethashkey(List<String>  monMessage)
	    {
	    	byte[] hash = null;
		try {
		    // cree un objet de type hash, vide, le paramètre est l'algo  ici l'algo c'est MD5
		    MessageDigest ma_sequence = MessageDigest.getInstance("MD5");
		    //Ajoute les bytes de monMessage à la sequence à hacher
		    for (String l :  monMessage)
			ma_sequence.update( (l+"\n").getBytes());
		    //calculre le hash 
		    hash = ma_sequence.digest();
		    return hash;
		}
		catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();

		}
		return hash;
		
	    }

}
