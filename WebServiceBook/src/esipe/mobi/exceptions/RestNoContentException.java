package esipe.mobi.exceptions;

@SuppressWarnings("serial")
public class RestNoContentException extends RestException{
	
	public RestNoContentException(){
		System.out.println("La ressource demandée ne peut pas être créee");
	}

}
