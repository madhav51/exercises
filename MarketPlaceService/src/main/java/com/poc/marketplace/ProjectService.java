package com.test.marketplace;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.test.marketplace.bean.Project;
import com.test.marketplace.bean.ProjectBid;
import com.test.marketplace.util.ServiceUtils;

@Path("/project")
public class ProjectService {

	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProject(Project project) {
		ServiceUtils.createProject(project);
		return Response.status(200).entity("Project Created Successfully.").build();
	}

	@POST
	@Path("/bid")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response bidProject(ProjectBid projectBid) {
		String msg = null;
		try {
			ServiceUtils.createProjectBid(projectBid);
			msg = "Bid created for the project";
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return Response.status(200).entity(msg).build();
	}

	@GET
	@Path("/openProjects")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOpenProjects() {
		List<Project> openProjects = ServiceUtils.getAllOpenProjects();
		return Response.status(200).entity(openProjects).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProject(@PathParam("id") String id) {
		if(id!= null && id.length()>0) {
			Project project = ServiceUtils.getProject(Integer.parseInt(id));
			if(project != null) {
				return Response.status(200).entity(project).build();	
			}else {
				return Response.status(200).entity("Cannot Find Project").build();	
			}
		}else {
			return Response.status(500).build();
		}
	}
}
