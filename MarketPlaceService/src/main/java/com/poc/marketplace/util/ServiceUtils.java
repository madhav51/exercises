package com.test.marketplace.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.test.marketplace.bean.Project;
import com.test.marketplace.bean.ProjectBid;
import com.test.marketplace.dao.ProjectBidDAO;
import com.test.marketplace.dao.ProjectDAO;

public class ServiceUtils {
	private static Map<Integer, ProjectDAO> projects = new HashMap<Integer, ProjectDAO>();
    /**
     * Creates Project entity.
     * @param project
     */
	public static void createProject(Project project) {
		if (project != null) {
			//Convert to Project DAO
			ProjectDAO newProj = ModelUtils.convertProject(project);
			//Add to cache.
			if (!projects.containsKey(newProj.getId())) {
				projects.put(newProj.getId(), newProj);
			}
		}
	}
	
	/**
	 * Creates Project Bid Entity.
	 * @param projectBid
	 * @throws Exception
	 */
	public static void createProjectBid(ProjectBid projectBid) throws Exception {
		if (projectBid != null) {
			int projId = projectBid.getProjectId();
			if (projects.containsKey(projId)) {
				ProjectDAO project = projects.get(projId);
				// Convert to ProjectBid DAO.
				ProjectBidDAO newProjectBid = ModelUtils.converProjectBid(projectBid);
				newProjectBid.setProjectDao(project);

				// Add bid to projectList
				List<ProjectBidDAO> bidsList = project.getBidsList();
				if (bidsList == null) {
					bidsList = new ArrayList<>();
				}
				bidsList.add(newProjectBid);
				//Sort list based on the bid amount.
				Collections.sort(bidsList);
				project.setBidsList(bidsList);
				// Update cache.
				projects.put(projId, project);
			} else {
				throw new Exception("Project not found.");
			}
		}
	}
	
	/**
	 * Get all Projects whose last date for bid 
	 * is after current date.
	 * @return
	 */
	public static List<Project> getAllOpenProjects() {
		List<Project> openProjects = new ArrayList<Project>();
		Iterator<Entry<Integer, ProjectDAO>> itr = projects.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<Integer, ProjectDAO> entry = itr.next();
			ProjectDAO project = entry.getValue();
			Date bidsLastDate = project.getBidLastDate();
			Date currentDate = new Date();

			if (bidsLastDate.after(currentDate)) {
				Project proj = ModelUtils.convertProjectDAO(project);
				proj.setBidsList(ModelUtils.convertProjectBidDaoList(project.getBidsList()));
				openProjects.add(proj);
			}

		}
		return openProjects;
	}
	
	/**
	 * Get Project by ProjectId
	 * @param id
	 * @return
	 */
	public static Project getProject(int id) {
		Project returnProj = null;
		ProjectDAO project = projects.get(id);
		if (project != null) {
			returnProj = ModelUtils.convertProjectDAO(project);
			if(project.getBidsList() != null) {
				ProjectBidDAO lowestBid = project.getBidsList().get(0);
				ProjectBid projectBid = ModelUtils.convertProjectBidDao(lowestBid);
				List<ProjectBid> bids = new ArrayList<>();
				bids.add(projectBid);
				returnProj.setBidsList(bids);
			}
		}
		return returnProj;
	}
}
