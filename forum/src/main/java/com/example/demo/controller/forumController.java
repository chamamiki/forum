package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Report;
import com.example.demo.service.ReportService;

@Controller
public class forumController {
	// テスト
	@Autowired
	ReportService reportService;

	// 投稿内容表示画面
	@GetMapping
	public ModelAndView top() {
		ModelAndView mav = new ModelAndView();
		List<Report> contentData = reportService.findAllReport();
		mav.setViewName("/top");
		mav.addObject("contents", contentData);
		return mav;
	}

	// 新規投稿画面
	@GetMapping("/new")
	public ModelAndView newContent() {
		ModelAndView mav = new ModelAndView();
		Report report = new Report();
		mav.addObject("formModel", report);
		mav.setViewName("/new");
		return mav;
	}

	// 投稿処理
	@PostMapping("/add")
	public ModelAndView addContent(@ModelAttribute("formModel") Report report) {
		reportService.saveReport(report);
		return new ModelAndView("redirect:/");
	}

	// 削除処理
	@PostMapping("/delete/{id}")
	public ModelAndView deleteContent(@PathVariable Integer id) {
		reportService.deleteReport(id);
		return new ModelAndView("redirect:/");
	}
}
