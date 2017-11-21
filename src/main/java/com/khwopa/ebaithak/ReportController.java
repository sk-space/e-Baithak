package com.khwopa.ebaithak;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khwopa.ebaithak.models.Report;

@Controller
@RequestMapping(value="/report", method=RequestMethod.POST)
public class ReportController {

	public String postReport(@ModelAttribute Report report, Model model){
		model.addAttribute("report", report);
		return "report";
	}
	
}
