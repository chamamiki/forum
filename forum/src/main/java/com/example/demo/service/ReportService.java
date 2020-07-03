package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Report;
import com.example.demo.repository.ReportRepository;

@Service
@Transactional
public class ReportService {

	@Autowired
	ReportRepository reportRepository;

	/**
	 * レコード全件取得
	 *
	 */
	public List<Report> findAllReport() {
		return reportRepository.findAll();
	}

	/**
	 * レコード追加
	 *
	 */
	public void saveReport(Report report) {
		reportRepository.save(report);
	}

	/**
	 * レコード削除
	 *
	 */
	public void deleteReport(Integer id) {
		reportRepository.deleteById(id);
	}
}
