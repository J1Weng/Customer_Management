package domain;

import java.util.List;

public class PageBean<Object>{
	private int pc;//当前页码 page code
	private int tr;//当前记录数total records
	private int pr;//页面记录数 page record
	//private int tp;//总页数total pages
	private List<Object> beanList;// 当前页的记录
	private String url;
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPr() {
		return pr;
	}
	public void setPr(int pr) {
		this.pr=pr;
	}
	
	public List<Object> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<Object> beanList) {
		this.beanList = beanList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getTp() {
		int tp=tr/pr;
		return tr%pr==0?tp:tp+1;
	}
	
}
