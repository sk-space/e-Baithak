package com.khwopa.ebaithak;

import javax.servlet.http.HttpServletRequest;
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
import com.khwopa.ebaithak.dao.FriendsDao;
import com.khwopa.ebaithak.dao.NotificationDao;
import com.khwopa.ebaithak.dao.UserDao;
import com.khwopa.ebaithak.models.User;

@Controller
public class FriendsController {

	@Autowired
	private UserDao uDao;
	
	@Autowired
	private FriendsDao fDao;
	
	@Autowired
	public NotificationDao nDao;
	
	@Autowired
	public BaithakDao bDao;
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);
	
	
	/**
	 * 	Controller for handling the friend search.
	 */
	@RequestMapping(value="/searchfriend", method=RequestMethod.POST)
	public String login(@ModelAttribute User user, HttpSession session, Model model){
		
		String friendName = user.getUsername();
			
		model.addAttribute("searchList", fDao.searchFriend(friendName));
		model.addAttribute("myUsername", session.getAttribute("username"));
		
		logger.info("/searchFriend - Result for "+friendName+" Found");

		String name = (String) session.getAttribute("username");
		model.addAttribute("friendsList", fDao.getFriends(name));

		Long userId = uDao.getUserId(name);
		User u = uDao.getUser(userId);
		model.addAttribute("name", u.getName());
		model.addAttribute("userDetail", uDao.getDetail(userId));
		model.addAttribute("notifications",nDao.getNotifications(userId));
		model.addAttribute("baithakList", bDao.getAllBaithak(userId));
		return "home";
		
	}
	
	
	@RequestMapping(value="/searchfriend", method=RequestMethod.GET)
	public String login(HttpSession session, Model model){
		
		return "redirect:/home";
		
	}

	/**
	 * 	Controller for handling add friend.
	 */
	@RequestMapping(value = "/addFriend", method = RequestMethod.POST)
	public String sendRequest(HttpServletRequest request, Model model, HttpSession session) {
		
		if (StringUtils.isEmpty(session.getAttribute("username"))) {

			return "login";
		}
		
		String username = (String) session.getAttribute("username");
		Long userId = uDao.getUserId(username);

		String str = request.getParameter("friendId");
		Long id = Long.parseLong(str);
		
		if(fDao.addFriend(id, userId )){
			
			String name = (String) session.getAttribute("username");
			model.addAttribute("friendsList", fDao.getFriends(name));
			model.addAttribute("added", "Friend request sent.");
		
		}else{
			model.addAttribute("added", "Error Occured while sending friend request");
		}		
		
		User u = uDao.getUser(userId);
		model.addAttribute("name", u.getName());
		model.addAttribute("userDetail", uDao.getDetail(userId));
		model.addAttribute("notifications",nDao.getNotifications(userId));
		model.addAttribute("baithakList", bDao.getAllBaithak(userId));
		return "redirect:/home";
	}
	
	
	/**
	 * 	Controller for the view of  confirming the friend request.
	 */
	@RequestMapping(value="/confirmFriend", method=RequestMethod.POST)
	public String confirmFriend(HttpServletRequest request){		
		
		String id = request.getParameter("friendsTableId");
		Long ftId = Long.parseLong(id);
		fDao.confirmFriend(ftId);
		return "redirect:/home";
		
	}
	
	/**
	 * 	Controller for the view of canceling the friend request.
	 */
	@RequestMapping(value="/cancelFriend", method=RequestMethod.POST)
	public String removeConfirmMember(HttpServletRequest request){		
		
		String id = request.getParameter("friendsTableId");
		Long ftId = Long.parseLong(id);
		fDao.cancelFriend(ftId);
		return "redirect:/home";
		
	}
	
}
