package esipe.mobi.filters;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import esipe.mobi.Util.BasicAuth;

public class AuthFilter implements ContainerRequestFilter {

	@Override
	public ContainerRequest filter(ContainerRequest containerRequest) {
		
		/*String method = containerRequest.getMethod();
		// myresource/get/56bCA for example
		String path = containerRequest.getPath(true);
		
		System.out.println("****** Filter Informations ******");
		System.out.println(containerRequest.getPath());
		System.out.println(containerRequest.getHeaderValue("authorization"));	
		System.out.println(containerRequest.getHeaderValue("Host"));
		System.out.println(containerRequest.getPath());
		System.out.println(containerRequest.getAbsolutePath());
		System.out.println(containerRequest.getBaseUri());

		// We do allow wadl to be retrieve
		if (method.equals("GET")) {
			return containerRequest;
		}
	

		// Get the authentification passed in HTTP headers parameters
		String auth = containerRequest.getHeaderValue("authorization");

		// If the user does not have the right (does not provide any HTTP Basic
		// Auth)
		if (auth == null) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		// lap : loginAndPassword
		String[] lap = BasicAuth.decode(auth);
		if (lap == null || lap.length != 2) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		String login = lap[0];
		String passwd = lap[1];
		
		System.out.println("Login = " +login);
		System.out.println("Passwd = " + passwd);
		
		if(passwd.equals("Pizza")){
			System.out.println("Ca marche");
		}
		else {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
	*/
		return containerRequest;
	}
}
