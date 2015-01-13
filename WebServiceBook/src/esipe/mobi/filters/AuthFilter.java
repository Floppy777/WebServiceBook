package esipe.mobi.filters;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import esipe.mobi.Util.BasicAuth;

public class AuthFilter implements ContainerRequestFilter {

	@Override
	public ContainerRequest filter(ContainerRequest containerRequest) {
		// GET, POST, PUT, DELETE, ...
		String method = containerRequest.getMethod();
		// myresource/get/56bCA for example
		String path = containerRequest.getPath(true);

		// We do allow wadl to be retrieve
		if (method.equals("GET")
				&& (path.equals("application.wadl") || path
						.equals("application.wadl/xsd0.xsd"))) {
			return containerRequest;
		}
		System.out.println(path);

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

		return containerRequest;
	}
}
