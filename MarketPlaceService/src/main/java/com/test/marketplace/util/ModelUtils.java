package com.test.marketplace.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.test.marketplace.bean.Project;
import com.test.marketplace.bean.ProjectBid;
import com.test.marketplace.dao.ProjectBidDAO;
import com.test.marketplace.dao.ProjectDAO;
import com.test.marketplace.dao.UserDAO;

public class ModelUtils {
	
	private static AtomicInteger projectSeq = new AtomicInteger();
	private static AtomicInteger projectBidSeq = new AtomicInteger();
	private static AtomicInteger userSeq = new AtomicInteger();
	
	/**
	 * Creates a new ProjectDAO Object.
	 * @param project
	 * @return
	 */
	public static ProjectDAO convertProject(Project project) {
		ProjectDAO newProj = new ProjectDAO();
		newProj.setId(projectSeq.incrementAndGet());
		newProj.setTitle(project.getTitle());
		newProj.setDescription(project.getDescription());
		newProj.setMaxBidAmout(new BigDecimal(project.getMaxBidAmout()));
		newProj.setBidLastDate(convertStringToDate(project.getMaxbidDate()));
		newProj.setCreatedBy(new UserDAO(userSeq.incrementAndGet(), project.getUserId(), "SELLER"));
		return newProj;
	}
	
	/**
	 * Creates a new Project Object.
	 * @param project
	 * @return
	 */
	public static Project convertProjectDAO(ProjectDAO project) {
		Project proj = new Project();
		proj.setId(project.getId());
		proj.setTitle(project.getTitle());
		proj.setDescription(project.getDescription());
		proj.setMaxBidAmout(project.getMaxBidAmout().toString());
		proj.setMaxbidDate(project.getBidLastDate().toString());
		proj.setUserId(project.getCreatedBy().getUserId());
		return proj;
	}
	
	/**
	 * Creates a new ProjectBidDAO Object.
	 * @param projectBid
	 * @return
	 */
	public static ProjectBidDAO converProjectBid(ProjectBid projectBid) {
		ProjectBidDAO newProjectBid = new ProjectBidDAO();
		newProjectBid.setId(projectBidSeq.incrementAndGet());
		newProjectBid.setBidAmount(new BigDecimal(projectBid.getBidAmount()));
		newProjectBid.setSubmittedDate(new Date());
		newProjectBid.setSubmittedBy(new UserDAO(userSeq.incrementAndGet(), projectBid.getUserId(), "BUYER"));
		return newProjectBid;
	}
	
	/**
	 * Creates a new ProjectBid Object.
	 * @param bidDao
	 * @return
	 */
	public static ProjectBid convertProjectBidDao(ProjectBidDAO bidDao) {
		ProjectBid bid = new ProjectBid();
		bid.setId(bidDao.getId());
		bid.setBidAmount(bidDao.getBidAmount().toString());
		bid.setSubmittedDate(bidDao.getSubmittedDate().toString());
		bid.setUserId(bidDao.getSubmittedBy().getUserId());
		bid.setProjectId(bidDao.getProjectDao().getId());
		return bid;
	}
	
	/**
	 * Create a List of ProjectBid objects
	 * @param bidsList
	 * @return
	 */
	public static List<ProjectBid> convertProjectBidDaoList(List<ProjectBidDAO> bidsList) {
		List<ProjectBid> projectBidsList = null;
		if(bidsList != null) {
			projectBidsList = new ArrayList<ProjectBid>();
			Iterator<ProjectBidDAO> itr = bidsList.iterator();
			while (itr.hasNext()) {
				ProjectBidDAO bidDao = (ProjectBidDAO) itr.next();
				ProjectBid bid = convertProjectBidDao(bidDao);
				projectBidsList.add(bid);
			}			
		}
		return projectBidsList;
	}
	
	/**
	 * Convert string in date format to java.util.Date/
	 * @param date
	 * @return
	 */
	public static Date convertStringToDate(String date) {
		Date utilDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			utilDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return utilDate;
	}
}
