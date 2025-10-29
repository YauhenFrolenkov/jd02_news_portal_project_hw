package edu.training.news_portal.beans;

import java.util.List;

public final class Page<T> {
	private final List<T> content; 
    private final int page;        
    private final int size;        
    private final long totalItems; 

    public Page(List<T> content, int page, int size, long totalItems) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalItems = totalItems;
    }

	public List<T> getContent() {
		return content;
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public long getTotalItems() {
		return totalItems;
	}
    
    public int getTotalPages() {
    	if (size <= 0) return 1;
        long pages = (totalItems + size - 1) / size; 
        return (int) Math.max(1, pages);
    }

}
