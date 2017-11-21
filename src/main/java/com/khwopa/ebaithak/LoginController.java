package com.khwopa.ebaithak;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khwopa.ebaithak.dao.BaithakDao;
import com.khwopa.ebaithak.dao.BaithakMembersDao;
import com.khwopa.ebaithak.dao.FriendsDao;
import com.khwopa.ebaithak.dao.NotificationDao;
import com.khwopa.ebaithak.dao.UserDao;
import com.khwopa.ebaithak.models.User;

@Controller
public class LoginController {
	
	@Autowired
	private UserDao uDao;
	
	@Autowired
	public FriendsDao fDao;
	
	@Autowired
	public NotificationDao nDao;
	
	@Autowired
	public BaithakDao bDao;
	
	@Autowired
	public BaithakMembersDao bmDao;
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);
	
	

	
	/**
	 * Controller for handling home get method.
	 */
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(HttpSession session, Model model){
		
		if (StringUtils.isEmpty(session.getAttribute("username"))) {

			logger.info("/login - Session value is empty - Trying to login");
			return "login";
		}
		
		model.addAttribute("username", session.getAttribute("username"));
		logger.info("/login - Login from Session successful");

		String name = (String) session.getAttribute("username");
		model.addAttribute("friendsList", fDao.getFriends(name));
		
		Long userId = uDao.getUserId(name);
		model.addAttribute("userDetail", uDao.getDetail(userId));
		User u = uDao.getUser(userId);
		model.addAttribute("name", u.getName());
//		model.addAttribute("notifications",nDao.getNotifications(userId));
		model.addAttribute("groupList", bmDao.getAllGroup(userId));
		model.addAttribute("baithakList", bDao.getAllBaithak(userId));
		model.addAttribute("confirmMember", bmDao.confirmMember(userId));
		model.addAttribute("friendRequest", fDao.friendRequest(userId));
		return "home";
		
	}
	
	/**
	 * Controller for handling login get method.
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(HttpSession session, Model model){
		
		if (StringUtils.isEmpty(session.getAttribute("username"))) {

			logger.info("/login - Session value is empty - Trying to login");
			return "login";
		}
		
		model.addAttribute("username", session.getAttribute("username"));
		logger.info("/login - Login from Session successful");
		
		String name = (String) session.getAttribute("username");

		model.addAttribute("friendsList", fDao.getFriends(name));
		
		Long userId = uDao.getUserId(name);
		User u = uDao.getUser(userId);
		model.addAttribute("name", u.getName());
		
		// change login status
		//Long userId = uDao.getUserId(name);
		uDao.changeStatus(1, userId);
		model.addAttribute("userDetail", uDao.getDetail(userId));
		
		model.addAttribute("notifications",nDao.getNotifications(userId));
		model.addAttribute("groupList", bmDao.getAllGroup(userId));
		model.addAttribute("baithakList", bDao.getAllBaithak(userId));
		
		return "home";
		
	}
	
	/**
	 * Controller for handling login post method.
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String CheckLogin(@ModelAttribute User user, Model model, HttpSession session){
		
		Long uid = uDao.getUserId(user.getUsername());
		
		//String encPass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		String encPass = UUID.nameUUIDFromBytes(user.getPassword().getBytes()).toString();
		user.setPassword(encPass);
		
		if(uDao.isExist(user) ){
			
			model.addAttribute("username",user.getUsername());
			model.addAttribute("id",uid);
			
			session.setAttribute("username", user.getUsername());
			//session.setAttribute("id", uid);
			
			session.setMaxInactiveInterval(50*60);
			
//			if(uDao.isAdmin(user)){
//				logger.info("/login - Admin "+ user.getUsername()+" Logged In");
//				return "admin/index";
//			}
			logger.info("/login - User " +user.getUsername()+" Logged In");

			String name = (String) session.getAttribute("username");
			Long userId = uDao.getUserId(name);
			User u = uDao.getUser(userId);
			model.addAttribute("name", u.getName());
			
			model.addAttribute("friendsList", fDao.getFriends(name));
			
			// change login status
			uDao.changeStatus(1, userId);
			model.addAttribute("userDetail", uDao.getDetail(userId));
			model.addAttribute("baithakList", bDao.getAllBaithak(userId));
			model.addAttribute("groupList", bmDao.getAllGroup(userId));
			model.addAttribute("notifications",nDao.getNotifications(userId));
			model.addAttribute("confirmMember", bmDao.confirmMember(userId));
			model.addAttribute("friendRequest", fDao.friendRequest(userId));
			
			return "home";
		}
		logger.info("/login - User "+user.getUsername()+" not exists");
		model.addAttribute("error","Username not Exist");
		return "login";
		
		
	}
	
	/**
	 * Controller for handling register get method.
	 */
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(HttpSession session, Model model){
		
		if (StringUtils.isEmpty(session.getAttribute("username"))) {
			logger.info("/register - no session variable found");
			return "login";
		}
		logger.info("/register - "+session.getAttribute("username")+ " redirected ot home");
		model.addAttribute("username", session.getAttribute("username"));
		return "home";
	}
	
	/**
	 * Controller for handling logout get method.
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Model model,HttpSession session){
		
		logger.info("user "+session.getAttribute("username")+" logged out");
		//model.addAttribute("logOutMsg", "Logout Successful");
		
		
		// change login status
				String name = (String) session.getAttribute("username");
				Long userId = uDao.getUserId(name);
				uDao.changeStatus(0, userId);
		
				
		session.invalidate();
		
		return "redirect:/login";
	}
}
