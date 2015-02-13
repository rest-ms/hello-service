package it.siletto.ms.hello.resources;

import io.dropwizard.jersey.caching.CacheControl;
import it.siletto.ms.auth.RestrictedTo;
import it.siletto.ms.auth.User;
import it.siletto.ms.base.cors.Cors;
import it.siletto.ms.base.cors.CorsPreflight;
import it.siletto.ms.base.resources.BaseResource;
import it.siletto.ms.hello.dto.HelloResponseDTO;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class Hello extends BaseResource {

	@GET
	@Timed
	@CacheControl(noCache = true)
	@Cors
	public HelloResponseDTO whoami(@RestrictedTo("user") User user) {
		
		HelloResponseDTO ret = new HelloResponseDTO();
		ret.setHello(user.getUserName());
		return ret;

	}
	
	@OPTIONS
	@CorsPreflight(headers="Authentication")
	public void preflight(){}



}
