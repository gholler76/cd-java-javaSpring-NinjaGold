package com.holler.ninjagold;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Scope ("session")
public class GoldController {
	
	Random r = new Random();
	
	public String logDate () {
		LocalDateTime currU = LocalDateTime.now();
		DateTimeFormatter currF = DateTimeFormatter.ofPattern("E, 'the' d 'of' MMMM");
		String d = currU.format(currF);
		return d; 
	}
	public String logTime () {
		LocalDateTime currU = LocalDateTime.now(); 
		DateTimeFormatter currF = DateTimeFormatter.ofPattern("hh:mm:a");
		String t = currU.format(currF);
		return t; 
	}
	
	String farm = "farm";
	String cave = "cave";
	String house = "house";
	String casino = "casino";
	
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpSession session, Model model) {
//		set session attributes on first GET request
		session.setAttribute("gold", 0);
		@SuppressWarnings("unchecked")
		List<String> roundLog = (List<String>) session.getAttribute("log");
		if (roundLog == null) {
			roundLog = new ArrayList<>();
		}
		model.addAttribute("roundLog",roundLog);
		return	"index.jsp"; 			
	}
	
	
	@RequestMapping(value="/process", method=RequestMethod.POST)
	public String process (HttpSession session, HttpServletRequest request, @RequestParam(value="activity")String activity) {
//		set variables to record the activity and gold for this round
		int thisGold = 0;
		@SuppressWarnings("unchecked")
		List<String> roundLog = (List<String>) request.getSession().getAttribute("log");
		if (roundLog == null) {
			roundLog = new ArrayList<>();
			request.getSession().setAttribute("log",roundLog);
		}

//		assign gold to this activity
		if (activity.equals(farm)) {
			thisGold = r.nextInt(10)+10;
		}
		else if (activity.equals(cave)) {
			thisGold = r.nextInt(5)+5;
		}
		if (activity.equals(house)) {
			thisGold = r.nextInt(2)+3;
		}
		if (activity.equals(casino)) {
			thisGold = r.nextInt(50+50)-50;
		}
		System.out.println("****thisGold>>>"+thisGold);
		
		
//		set gold and activity log for the round
		int sessionGold = (int)session.getAttribute("gold");
		session.setAttribute("gold", sessionGold + thisGold);
		String thisLog = "Visited the "+activity+" and earned "+thisGold+" gold.  -- "+logDate()+" - "+logTime()+""+System.lineSeparator(); 
		roundLog.add(thisLog); 
		session.setAttribute("log", roundLog);
		System.out.println("******sessionGold>>>"+session.getAttribute("gold"));
		System.out.println(roundLog);
		
		
		return "index.jsp";
	}
	
	
	
}
