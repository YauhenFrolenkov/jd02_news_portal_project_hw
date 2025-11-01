package edu.training.news_portal.beans;

public class NewsBuilder {

	private String title;
    private String brief;
    private String contentPath;
    private String publishDate;
    private int statusId;

    public NewsBuilder() {}

    public NewsBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public NewsBuilder setBrief(String brief) {
        this.brief = brief;
        return this;
    }

    public NewsBuilder setContentPath(String contentPath) {
        this.contentPath = contentPath;
        return this;
    }

    public NewsBuilder setPublishDate(String publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public NewsBuilder setStatusId(int statusId) {
        this.statusId = statusId;
        return this;
    }

    public News build() {
       return new News(title, brief, contentPath, publishDate, statusId);  // Возвращаем сразу объект News без id, id будет присвоен БД
    }
}