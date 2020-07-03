package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report")
public class Report {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String content; // 投稿内容

	// 投稿IDを取得
	public int getId() {
		return id;
	}

	// 投稿IDを格納
	public void setId(int id) {
		this.id = id;
	}

	// 投稿内容を取得
	public String getContent() {
		return content;
	}

	// 投稿内容を格納
	public void setContent(String content) {
		this.content = content;
	}
}
