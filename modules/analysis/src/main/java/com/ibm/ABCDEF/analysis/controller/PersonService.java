package com.ibm.ABCDEF.analysis.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.ABCDEF.analysis.domain.User;

@Path("/sample")
public interface PersonService {

	@GET
	@Path("/bean/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public User readPersonInfoWithUsername(@PathParam("username") String username);
}