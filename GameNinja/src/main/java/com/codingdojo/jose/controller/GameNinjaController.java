package com.codingdojo.jose.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class GameNinjaController {

	
	@GetMapping("/")
	public String index(Model model, HttpSession session ) {
		if (session.getAttribute("gold") == null) {
            session.setAttribute("gold", 0);
            session.setAttribute("activities", new ArrayList<String>());
		}
		model.addAttribute("gold", session.getAttribute("gold"));
		model.addAttribute("activities", session.getAttribute("activities"));
		return "index.jsp";
	}
		
		@PostMapping("/farm")
		public String farm(HttpSession session) {
			int earnings = generateRandom(10, 20);
			return updateGoldAndActivities(session, earnings, "farm");
		}
		
		@PostMapping("/cave")
		public String cave(HttpSession session) {
			int earnings = generateRandom(5, 10);
			return updateGoldAndActivities(session, earnings, "cave");
		}
		
		@PostMapping("/house")
		public String house(HttpSession session) {
			int earnings = generateRandom(2, 5);
			return updateGoldAndActivities(session, earnings, "house");
		}
		
		@PostMapping("/casino")
		public String casino(HttpSession session) {
			int earnings = generateRandom(-50, 50);
			updateGoldAndActivities(session, earnings, "casino");
			return "redirect:/";
		}
		
		@PostMapping("/spa")
		public String spa(HttpSession session) {
			int earnings = generateRandom(-20, 5);
			return updateGoldAndActivities(session, earnings, "spa");
		}
		
		@PostMapping("/reset")
		public String reset(HttpSession session) {
			initializeGame(session);
			return "redirect:/";
		}
		
		private void initializeGame(HttpSession session) {
			session.setAttribute("gold", 0);
			session.setAttribute("activities", new ArrayList<String>());
		}
		
	private String updateGoldAndActivities(HttpSession session, int earnings, String activity) {
		int gold = (int) session.getAttribute("gold");
		int totalGold = gold + earnings;
		if (totalGold < 0) {
			return "redirect:/debtorsPrison.jsp";
		}
		session.setAttribute("gold", totalGold);
		
		List<String> activities = (List<String>) session.getAttribute("activities");
		activities.add("You entered a " + activity + " and earned " + earnings + " gold " + LocalDateTime.now());
		session.setAttribute("activities", activities);
		return "redirect:/";
	}
	private int generateRandom(int min, int max) {
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}
}
