package com.jk.pojo.tree;

import java.io.Serializable;
import java.util.List;

public class Tree implements Serializable{
	private static final long serialVersionUID = 2707096461861558113L;

	private Integer treeID;
	
	private String treeName;
	
	private Integer parentID;
	
	private String url;

	private Integer id;
	
	private String text;
	
	private List<Tree> nodes;
	
	private Integer pid;
	
	private Boolean checked;
	
	public Integer getTreeID() {
		return treeID;
	}

	public void setTreeID(Integer treeID) {
		this.treeID = treeID;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public Integer getParentID() {
		return parentID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<Tree> getNodes() {
		return nodes;
	}

	public void setNodes(List<Tree> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return "Tree [treeID=" + treeID + ", treeName=" + treeName + ", parentID=" + parentID + ", url=" + url + ", id="
				+ id + ", text=" + text + ", nodes=" + nodes + ", pid=" + pid + ", checked=" + checked + "]";
	}
	
}
