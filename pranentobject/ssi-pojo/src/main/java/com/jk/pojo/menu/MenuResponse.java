package com.jk.pojo.menu;

/**
 * 
 * <pre>项目名称：ssi-pojo    
 * 类名称：MenuResponse    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午3:21:34    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午3:21:34    
 * 修改备注：       
 * @version </pre>
 */
public class MenuResponse extends Menu {
	
	private int id;
	
	private String name;
	
	private boolean checked;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
