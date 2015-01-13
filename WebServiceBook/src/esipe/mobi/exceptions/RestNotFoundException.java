package esipe.mobi.exceptions;

@SuppressWarnings("serial")
public class RestNotFoundException extends RestException{
	public RestNotFoundException(){
		System.out.println("La ressource demandée n'a pas été trouvée");
	}

}
