package com.jk.controller.user;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jk.pojo.fingerprint.FingerprintRequest;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.pojo.tree.Tree;
import com.jk.pojo.user.UserRequest;
import com.jk.pojo.user.UserResponse;
import com.jk.service.user.UserService;

import common.base.MySessionContext;
import common.util.DateUtil;
import common.util.FTPUtil;
import common.util.FileUtil;
import common.util.SmsUtil;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("uploadUserPhoto")
	Map uploadUserPhoto(MultipartFile userPhoto) {
		Map resultMap = new HashMap<String, String>();
		try {
			//获取文件的输入流
			InputStream inputStream = userPhoto.getInputStream();
			String md5 = FileUtil.getMD5(inputStream, "md5");
			resultMap.put("fileFingerprint", md5);
			//如果存在，直接把地址返回给用户
			System.out.println(md5);
			//如果不存在，保存这个文件到FTP服务器，并且把保存的路径以及文件指纹存到数据库
			
			//获取上传文件的文件名
			String realFileName = userPhoto.getOriginalFilename();
			//切割获取后缀名
			String suffix = realFileName.substring(realFileName.lastIndexOf("."));
			//重命名文件，防止因为文件名重复而覆盖原有
			String fileName = UUID.randomUUID().toString()+suffix;
			//文件的全路径
			String path="1702A/"+DateUtil.formatDateToString(new Date(), "yyyy/MM/dd")+"/"+fileName;
			boolean uploadFile = FTPUtil.uploadFile(userPhoto.getInputStream(), fileName, path);
			FingerprintRequest fingerprint = new FingerprintRequest();
			fingerprint.setFileFingerprint(md5);
			fingerprint.setFileUrl(path);
			Integer fileFingerprintID = userService.insertUserPhoto(fingerprint);
			resultMap.put("fileFingerprintID", fileFingerprintID);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * <pre>registerAccount(注册用户)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月6日 下午8:44:54    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月6日 下午8:44:54    
	 * 修改备注： 
	 * @param userRequest
	 * @return</pre>
	 */
	@RequestMapping("registerCheckAccount")
	@ResponseBody
	Map<String, Object>  registerCheckAccount(UserRequest userRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer status = userService.registerCheckAccount(userRequest);
		if(status == 1){
			map.put("status", 1);
		}else{
			map.put("status", 2);
		}
		return map;
	}
	
	/**
	 * <pre>queryResponseCode(前台传入的验证码进行匹配后台发送的验证码)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月8日 上午10:33:31    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月8日 上午10:33:31    
	 * 修改备注： 
	 * @param userRequest
	 * @return</pre>
	 */
	@RequestMapping("CheckCodeNumber")
	@ResponseBody
	Map CheckCodeNumber(UserRequest userRequest) {
		Map<Object,Object> map = new HashMap<>();
		Integer codeStatus = userService.CheckCodeNumber(userRequest);
		map.put("codeStatus", codeStatus);
		return map;
	}
	/**
	 * <pre>registerCheckPhoneNumber(发送验证码并返回验证码)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月7日 下午2:42:31    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月7日 下午2:42:31    
	 * 修改备注： 
	 * @param userRequest
	 */
	@RequestMapping("registerCheckPhoneNumber")
	@ResponseBody
	void  registerCheckPhoneNumber(UserRequest userRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
//		//map.put("code", 666);
		try {
			SendSmsResponse response = SmsUtil.sendSms(userRequest.getPhoneNumber());
			map.put("code", response.getCode());
			map.put("telNum", userRequest.getPhoneNumber());
			//将验证码和手机号存入到redis
			userService.saveCode(map);
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>updateUser(修改用户,新增用户)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午7:35:27    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午7:35:27    
	 * 修改备注： 
	 * @param userRequest
	 * @return</pre>
	 */
	@RequestMapping("updateUser")
	@ResponseBody
	String updateUser(UserRequest userRequest,FingerprintRequest fingerprint) {
		userService.updateUser(userRequest,fingerprint);
		return "{}";
	}
	
	@RequestMapping("registerUser")
	@ResponseBody
	String registerUser(UserRequest userRequest) {
		userService.registerUser(userRequest);
		return "{}";
	}
	
	/**
	 * <pre>toAddUserPage(回显)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 上午10:41:26    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 上午10:41:26    
	 * 修改备注： 
	 * @param userRequest
	 * @return</pre>
	 */
	@RequestMapping("toAddUserPage")
	ModelAndView toAddUserPage(UserRequest userRequest) {
		ModelAndView mv = new ModelAndView();
		if(userRequest.getUserID() != null){
			System.out.println(userRequest.getUserID());
			UserResponse userInfo = userService.queryUserByUserID(userRequest);
			mv.addObject("user",userInfo);
		}
		mv.setViewName("pages/user/user_add");
		return mv;
	}
	
	/**
	 * <pre>insertUserRoleList(给用户赋角色)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午7:37:30    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午7:37:30    
	 * 修改备注： 
	 * @param roleRequestList
	 * @return</pre>
	 */
	@RequestMapping("insertUserRoleList")
	@ResponseBody
	String insertUserRoleList(@RequestBody List<RoleRequest> roleRequestList) {
		userService.insertUserRoleList(roleRequestList);
		return "{}";
	}
	
	/**
	 * <pre>toShowUserInfo(点击用户管理，去用户展示界面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午7:55:27    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午7:55:27    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toShowUserInfo")
	public String toShowUserInfo(){
		return "pages/user/showList";
	}
	
	/**
	 * <pre>toUserRolePage(用户赋角色的回显)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午7:40:35    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午7:40:35    
	 * 修改备注： 
	 * @param mm
	 * @param userRequest
	 * @return</pre>
	 */
	@RequestMapping("toUserRolePage")
	String toUserRolePage(ModelMap mm, UserRequest userRequest) {
		mm.addAttribute("userID", userRequest.getUserID());
		return "pages/user/user_role";
	}
	
	/**
	 * <pre>deleteMuchUser(批量删除用户)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午7:40:58    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午7:40:58    
	 * 修改备注： 
	 * @param user
	 * @return</pre>
	 */
	@RequestMapping("deleteMuchUser")
	@ResponseBody
	String deleteMuchUser(UserRequest user){
		userService.deleteMuchUser(user);
		return "{}";
	}
	
	/**
	 * <pre>selectUserList(查询用户数据)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午7:41:22    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午7:41:22    
	 * 修改备注： 
	 * @param pageNumber
	 * @param userRequest
	 * @return</pre>
	 */
	@RequestMapping("selectUserList")
	@ResponseBody
	Map<String,Object> selectUserList(String pageNumber,UserRequest userRequest){
		//查询总条数
		int totalCount = userService.selectUserCount(userRequest);
		userRequest.setTotalCount(totalCount);
		if (null == pageNumber || "".equals(pageNumber.trim())) {
			pageNumber = "1";
		}
		userRequest.setPageIndex(Integer.valueOf(pageNumber));
		//计算分页信息
		userRequest.calculate();
		//查询列表
		Map<String,Object> hashMap = new HashMap<String, Object>();
		List<Tree> list = userService.selectUserList(userRequest);
		hashMap.put("rows", list);
		hashMap.put("total", totalCount);
		return hashMap;
	}
	
	/**
	 * <pre>login(登录验证)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午7:43:29    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午7:43:29    
	 * 修改备注： 
	 * @param userRequest
	 * @param request
	 * @return</pre>
	 */
	@RequestMapping("loginUser")
	@ResponseBody
	Map<String, Object> login(UserRequest userRequest, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object codeObj = session.getAttribute("imageCode");
		if (null == codeObj) {
			codeObj = "";
		}
		String code = codeObj.toString();
		userRequest.setSysImgCode(code);
		Map<String, Object> map = userService.login(userRequest);
		Object userInfo = map.get("userInfo");
		if (null != userInfo) {
			UserResponse userResponse = (UserResponse) userInfo;
			//用户信息放进session中
			session.setAttribute("userInfo", userInfo);
			//设置session过期时间(单位：秒)
			session.setMaxInactiveInterval(600);
			
			String userID = userResponse.getUserID() + "";
			MySessionContext.removeSession(userID, session);
			MySessionContext.addSession(userID, session);
			
			//查询出用户的权限树
			MenuRequest menuRequest = new MenuRequest();
			menuRequest.setUserID(userResponse.getUserID());
			List<Map<String, Object>> treeList = userService.selectTreeListJson(menuRequest);;
			//放到redis中
			//JedisUtil.setString(userID + "#tree_list", JsonUtil.toJsonString(treeList), -1);
		}
		return map;
	} 
	
	/**
	 * <pre>toIndex(登录成功后跳转的页面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午7:43:46    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午7:43:46    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toIndex")
	public ModelAndView toIndex(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/pages/main");
		return mv;
	}
	
	/**
	 * <pre>loadRole(加载角色列表)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午7:44:42    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午7:44:42    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("loadRole")
	@ResponseBody
	List<RoleResponse> loadRole(RoleRequest roleRequest){
		List<RoleResponse> roleList = userService.selectUserRoleListJson(roleRequest);
		return roleList;
	}
	
	/**
	 * <pre>loadTree(登錄成功后加載菜單)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午2:08:26    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午2:08:26    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>
	 */
    @RequestMapping("loadTree")
    @ResponseBody
    List<Map<String, Object>> loadTree(MenuRequest menuRequest) {
		List<Map<String, Object>> treeList = userService.selectTreeListJson(menuRequest);
		return treeList;
	}
    
    /**
     * <pre>querySonTree(查询子树的节点)   
     * 创建人：徐叶     
     * 创建时间：2017年7月27日 下午7:45:17    
     * 修改人：徐叶       
     * 修改时间：2017年7月27日 下午7:45:17    
     * 修改备注： 
     * @param menu
     * @return</pre>
     */
	public List<Tree> querySonTree(List<Tree> menu){
		List<Tree> menuList = new ArrayList<Tree>();
		for (Tree tree : menu) {
			if(userService.querySonCount(tree.getId())!=0){
				List<Tree> findTree = getSonTree(tree.getId());
				tree.setNodes(findTree);
				menuList.add(tree);
			}else{
				menuList.add(tree);
			}
		}
		return menuList;
	}
	
	/**
	 * <pre>getSonTree(调用自身的方法进行递归)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午7:47:05    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午7:47:05    
	 * 修改备注： 
	 * @param id
	 * @return</pre>
	 */
	public List<Tree> getSonTree(Integer id){
		List<Tree> resultList = new ArrayList<Tree>();
		List<Tree> list = userService.findTree(id);
		for (Tree tree : list) {
			if(userService.querySonCount(tree.getId())!=0){
				List<Tree> findTree = getSonTree(tree.getId());
				tree.setNodes(findTree);
				resultList.add(tree);
			}else{
				resultList.add(tree);
			}
		}
		return resultList;
	}
	
	/**
	 * <pre>logout(退出登录)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月8日 下午2:34:05    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月8日 下午2:34:05    
	 * 修改备注： 
	 * @param request
	 * @return</pre>
	 */
	@RequestMapping("logout")
	String logout(HttpServletRequest request){
		request.getSession().removeAttribute("userInfo");
		return "../index";
	}
	
}
