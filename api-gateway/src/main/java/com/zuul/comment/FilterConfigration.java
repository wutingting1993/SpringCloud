package com.zuul.comment;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by NCP-605 on 2017/7/13.
 */
@ConfigurationProperties("zuul.filter")
public class FilterConfigration {
	private String root;
	private Integer interval;

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}
}
