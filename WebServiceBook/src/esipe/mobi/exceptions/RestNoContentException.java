package esipe.mobi.exceptions;

@SuppressWarnings("serial")
public class RestNoContentException extends RestException{
	
	public RestNoContentException(){
		System.out.println("La ressource demand�e ne peut pas �tre cr�ee");
	}

}
