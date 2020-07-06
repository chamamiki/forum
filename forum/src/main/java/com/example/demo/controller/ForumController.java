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
public class ForumController {
	@Autowired
	ReportService reportService;

	// 投稿内容表示画面
	@GetMapping
	public ModelAndView top() {
		ModelAndView mav = new ModelAndView();
		List<Report> contentData = reportService.findAllReport(); // 投稿を全件取得
		mav.setViewName("/top"); // 画面遷移先を指定
		mav.addObject("contents", contentData); // 投稿データオブジェクトを保管
		return mav;
	}

	// 新規投稿画面
	@GetMapping("/new")
	public ModelAndView newContent() {
		ModelAndView mav = new ModelAndView();
		Report report = new Report(); // form用の空のentityを準備
		mav.setViewName("/new"); // 画面遷移先を指定
		mav.addObject("formModel", report); // 準備した空のentityを保管
		return mav;
	}

	// 投稿処理
	@PostMapping("/add")
	public ModelAndView addContent(@ModelAttribute("formModel") Report report) {
		reportService.saveReport(report); // 投稿をテーブルに格納
		return new ModelAndView("redirect:/"); // rootへリダイレクト
	}

	// 削除処理
	@PostMapping("/delete/{id}")
	public ModelAndView deleteContent(@PathVariable Integer id) {
		reportService.deleteReport(id); // UrlParameterのidを基に投稿を削除
		return new ModelAndView("redirect:/"); // rootへリダイレクト
	}

	// 編集画面
	@GetMapping("/edit/{id}")
	public ModelAndView editContent(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView();
		Report report = reportService.editReport(id); // 編集する投稿を取得
		mav.addObject("editContent", report); // TODO idをセットする為に使っているので修正した方がいい
		mav.addObject("formModel", report); // 編集する投稿をセット
		mav.setViewName("/edit"); // 画面遷移先を指定
		return mav;
	}

	// 編集処理
	@PostMapping("/update/{id}")
	public ModelAndView updateContent(@PathVariable Integer id, @ModelAttribute("formModel") Report report) {
		report.setId(id); // UrlParameterのidを更新するentityにセット
		reportService.saveReport(report); // 編集した投稿を更新
		return new ModelAndView("redirect:/"); // rootへリダイレクト
	}
}
