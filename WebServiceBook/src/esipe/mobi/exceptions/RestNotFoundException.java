package esipe.mobi.exceptions;

@SuppressWarnings("serial")
public class RestNotFoundException extends RestException{
	public RestNotFoundException(){
		System.out.println("La ressource demand�e n'a pas �t� trouv�e");
	}

}
