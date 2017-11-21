package com.khwopa.ebaithak;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.khwopa.ebaithak.dao.AttachmentDao;
import com.khwopa.ebaithak.dao.BaithakDao;
import com.khwopa.ebaithak.dao.MessageDao;
import com.khwopa.ebaithak.dao.UserDao;
import com.khwopa.ebaithak.models.Attachment;
import com.khwopa.ebaithak.models.Baithak;
import com.khwopa.ebaithak.models.Message;
import com.khwopa.ebaithak.models.User;

@Controller
public class UploadController {

	@Autowired
	private UserDao uDao;
	
	@Autowired
	private BaithakDao bDao;
	
	@Autowired
	private MessageDao mDao;
	
	@Autowired
	private AttachmentDao aDao;
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);
	private String myPath = "E:\\Workspace\\spring_workspace\\eBaithak\\src\\main\\webapp\\resources\\";
		
	/**
	 * @param file
	 * @param session
	 * @param baithak
	 * @param model
	 * @return
	 * @throws IOException
	 * Controller to handle create group method.
	 */
	@RequestMapping(value="/creategroup", method=RequestMethod.POST)
	public String userRegister(@RequestParam("gPhoto") MultipartFile file,HttpSession session, @ModelAttribute Baithak baithak, Model model) throws IOException{

		byte[] bytes;
		if(!file.isEmpty()){
			bytes = file.getBytes();
			FileOutputStream out = new FileOutputStream(myPath+"baithakImg\\" + file.getOriginalFilename());
			baithak.setImage(file.getOriginalFilename());
			out.write(bytes);
			out.close();
		}
		
		String name = (String) session.getAttribute("username");
		Long userId = uDao.getUserId(name);
		baithak.setCreated_by(userId);
		baithak.setCreated_at(new Date().toString());
		bDao.createBaithak(baithak);

		logger.info("/creategroup - Creation of Baithak "+baithak.getName() +" successful.");
		return "redirect:/home";
	}
	
	/**
	 * @param file
	 * @param user
	 * @param model
	 * @return
	 * @throws IOException
	 * Controller to handle register method of the user.
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String userRegister(@RequestParam("image") MultipartFile file, @ModelAttribute User user, Model model) throws IOException{
		
		String encPass = UUID.nameUUIDFromBytes(user.getPassword().getBytes()).toString();
		user.setPassword(encPass);
		
		if(uDao.isUsernameExist(user.getUsername())){
			logger.info("/register - Username  "+ user.getUsername() +"  already in use");
			model.addAttribute("error", "Username "+ user.getUsername() +" Already in Use");
			return "login";
		}
		else{
			logger.info("/register - Registration of "+user.getUsername() +" successful");

			byte[] bytes;
			if(!file.isEmpty()){
				bytes = file.getBytes();
				FileOutputStream out = new FileOutputStream(myPath+"userImg\\" + file.getOriginalFilename());
				user.setPhoto(file.getOriginalFilename());
				out.write(bytes);
				out.close();
			}
			
			uDao.addUser(user);
			model.addAttribute("messge", "Registration for user : <u>"+user.getUsername()+"</u> Successful...!! You can log in Now.");
			return "login";
		}
		
	}
	
	/**
	 * @param file
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 * Controller to handle uploaded attachment from the chat.
	 */
	@RequestMapping(value="/attachment", method=RequestMethod.POST)
	public String getAttachment(@RequestParam("attachment") MultipartFile file, HttpServletRequest request, HttpSession session) throws IOException{
		
		byte[] bytes;
		if(!file.isEmpty()){
			bytes = file.getBytes();
			FileOutputStream out = new FileOutputStream(myPath+"files\\" + file.getOriginalFilename());
			out.write(bytes);
			out.close();
		}
		
		if(!file.isEmpty()) {
			String attachment = file.getOriginalFilename();
			String gId = request.getParameter("groupId");
			Long groupId = Long.parseLong(gId);
			String name = (String) session.getAttribute("username");
			Long userId = uDao.getUserId(name);
			Message msg = new Message();
			msg.setMessage(attachment);
			msg.setGroupId(groupId);
			msg.setSenderId(userId);
			mDao.createMessage(msg);
			
			Attachment a = new Attachment();
			a.setFile(attachment);
			aDao.createFile(a);
		}
		
		return "redirect:/baithak";
		
	}
	
}
