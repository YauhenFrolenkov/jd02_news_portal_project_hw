package edu.training.news_portal.beans;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String title;
    private String brief;
    private String contentPath;
    private String publishDate;
    private int statusId;
    private String content;
	
	public News() {
	}
	
	public News(String title, String brief, String contentPath, String publishDate, int statusId) {
        this.title = title;
        this.brief = brief;
        this.contentPath = contentPath;
        this.publishDate = publishDate;
        this.statusId = statusId;
    }
	
	public News(int id, String title, String brief, String contentPath, String publishDate, int statusId) {
		super();
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.contentPath = contentPath;
		this.publishDate = publishDate;
		this.statusId = statusId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}	

	@Override
	public int hashCode() {
		return Objects.hash(brief, contentPath, id, publishDate, statusId, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(brief, other.brief) && Objects.equals(contentPath, other.contentPath) && id == other.id
				&& Objects.equals(publishDate, other.publishDate) && statusId == other.statusId
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", brief=" + brief + ", contentPath=" + contentPath
				+ ", publishDate=" + publishDate + ", statusId=" + statusId + "]";
	}

		
}
