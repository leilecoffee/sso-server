package cn.mcsj.sso.dto.base;

public class PageBean {
	// 当前页码
	private int pageNum;
	// 每页记录数
	private int pageSize = 10;
	// 总记录数
	private int total;
	// 分页数据
	private Object rows;
	// 总页数
	private int pages;

	public PageBean(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public int getStart() {
		return (getPageNum() - 1) * getPageSize();
	}

}
