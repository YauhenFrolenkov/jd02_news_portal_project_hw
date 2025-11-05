package edu.training.news_portal.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import edu.training.news_portal.beans.News;

public class NewsValidator {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd")
			.withResolverStyle(ResolverStyle.STRICT);

	private static NewsValidator instance;

	private NewsValidator() {

	}

	public static NewsValidator getInstance() {
		if (instance == null) {
			instance = new NewsValidator();
		}
		return instance;
	}

	public boolean isValid(News news) {
		if (news == null)
			return false;

		return isValidTitle(news.getTitle()) && isValidBrief(news.getBrief())
				&& isValidContentPath(news.getContentPath()) && isValidPublishDate(news.getPublishDate())
				&& isValidStatusId(news.getStatusId());
	}

	public boolean isValidTitle(String title) {
		return title != null && !title.isBlank() && title.length() <= 200;
	}

	public boolean isValidBrief(String brief) {
		return brief != null && !brief.isBlank() && brief.length() <= 500;
	}

	public boolean isValidContentPath(String contentPath) {
		return contentPath != null && !contentPath.isBlank();
	}

	public boolean isValidPublishDate(String publishDate) {
		if (publishDate == null || publishDate.isBlank())
			return false;

		// Проверяем дату через LocalDate.parse с строгим резолвом
		try {
			LocalDate.parse(publishDate, DATE_FORMAT);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isValidStatusId(int statusId) {

		return statusId == 1 || statusId == 2;
	}
}