package com.kgc.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.kgc.entity.Role;
import com.kgc.entity.User;
import com.kgc.service.UserService;
import com.kgc.util.Common;
import com.kgc.util.validateGroup.CheckDefault;
import com.kgc.util.validateGroup.CheckInsert;
import com.kgc.util.validateGroup.CheckUpdate;

@Controller
//顶层映射路径
@RequestMapping("/user")
public class UserController {

	Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * 跳转用户列表页面
	 * @param request
	 * @param model
	 * @return view
	 */
	@RequestMapping("/userList")
	public String userList(HttpServletRequest request,Model model) {
		String queryname = request.getParameter("queryname");
		String queryUserRole = request.getParameter("queryUserRole");
		String pageIndex = request.getParameter("pageIndex");
	
		Integer pageNum = 1;
		//包含了null和空字符串的判断
		if(!StringUtils.isEmpty(pageIndex)) {
			pageNum = Integer.valueOf(pageIndex);
		}
		
		User param = new User();
		if(queryname!=null && !queryname.equals("")) {
			model.addAttribute("queryUserName", queryname);
			param.setUserName(queryname);
		}
		if(queryUserRole!=null && !queryUserRole.equals("0")) {
			model.addAttribute("queryUserRole", queryUserRole);
			param.setUserRole(Integer.valueOf(queryUserRole));
		}
		//依据条件分页获取用户列表
		PageInfo<User> userList = userService.getUserPageByParam(param, pageNum, 5);
		model.addAttribute("userList", userList);
		//获取所有角色
		List<Role> roleList = userService.getRoleList();
		model.addAttribute("roleList", roleList);
		return "user/userlist";
	}
	
	/**
	 * 跳转用户详情页面
	 * @param id
	 * @param model
	 * @return view
	 */
	@RequestMapping("/view/{id}")
	//REST风格,参数前需要指定@PathVariable
	public String view(@PathVariable Long id,Model model){
		logger.debug("view id == "+id);
		User user = userService.getUserById(id);
		//将数据set到请求对象里面
		model.addAttribute("user", user);
		return "user/userview";
	}

	/**
	 * 跳转用户添加页面
	 * @return view
	 */
	@RequestMapping("/userAdd")
	public String useradd() {
		return "user/useradd";
	}
	
	/**
	 * 保存用户信息和文件
	 * @param user
	 * @param result
	 * @param request
	 * @param multipartFile
	 * @return view
	 * @throws IOException
	 */
	@RequestMapping("/saveUser")
	public String saveUser(
			//数据校验  @Valid注释的对象后必须紧接BindingResult, @Validated 进行分组校验
			@Validated({CheckDefault.class,CheckInsert.class}) User user, 
			BindingResult result, 
			HttpServletRequest request,
			//使用MultipartFile接收一个文件,form表单需要指定enctype="multipart/form-data"
			//多个文件可以使用多个MultipartFile对象,或MultipartFile数组
			@RequestParam(value="a_idPicPath")MultipartFile multipartFile 
			) throws IOException {
		
		if (result.hasFieldErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                logger.debug(error.getDefaultMessage());
            }
            request.setAttribute("message", "非法的请求参数");
            return "user/useradd";
        }
		user.setCreatedBy( ((User)request.getSession().getAttribute(Common.USER_SESSION)).getId() );
		if(userService.addUser(user) == 1) {
			//判断文件是否为空
			if(!multipartFile.isEmpty()) {
				//获取上传的文件名称
				String fileName = multipartFile.getOriginalFilename();
				//获取文件格式
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				//判断后缀是否符合业务要求
				if(".jpg".equals(suffix) || ".png".equals(suffix)) {
					//获取真实的项目路径
					String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
					logger.debug("==>文件上传路径: "+path);
					//生成随机名称
					UUID uuid = UUID.randomUUID();
					//拼接新的文件名称
					String newFileName = uuid + suffix;
					//在服务器上创建文件
					File file  = new File(path + File.separator + newFileName);
					if(!file.exists()) {
						file.mkdirs();
					}
					//将文件保存
					multipartFile.transferTo(file);
				}
			}
			return "redirect:userList";
		}else {
			return "user/useradd";
		}
	}
	
	/**
	 * 查看用户账号是否存在
	 * @param userCode
	 * @return json
	 */
	@RequestMapping("/checkUserCode")
	@ResponseBody
	public Object checkUserCode(String userCode) {
		// 查看用户是否存在
		Map<String, String> map = new HashMap<String,String>();
		if(StringUtils.isEmpty(userCode)) {
			map.put("msg", "error");
			return map;
		}
		User param = new User();
		param.setUserCode(userCode);
		List<User> list = userService.getUserByParam(param);
		if(!list.isEmpty()) {
			map.put("msg", "exist");
		}
		return map;
	}
	
	/**
	 * 获取角色列表
	 * @return json
	 */
	@RequestMapping("/getRoleList")
	@ResponseBody
	public List<Role> getRoleList(){
		return userService.getRoleList();
	}
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return json
	 */
	//@GetMapping("/findUserById")
	//@PostMapping("/findUserById")
	@RequestMapping("/findUserById")
	@ResponseBody
	public User findUserById(Long id) {
		User user = userService.getUserById(id);
		//jackson toJsonString 对日期对象进行了格式化
		return user;
	}
	
	/**
	 * 跳转用户修改页面
	 * @param id
	 * @param model
	 * @return view
	 */
	@RequestMapping("modifyUser/{id}")
	public String modifyUser(@PathVariable Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user/usermodify";
	}
	
	/**
	 * 保存用户修改
	 * @param user
	 * @param result
	 * @param request
	 * @return view
	 */
	@RequestMapping("/saveUserModify")
	public String saveUserModify(
			@Validated({CheckDefault.class,CheckUpdate.class}) User user,
			BindingResult result,
			HttpServletRequest request) {
		
		if (result.hasFieldErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                logger.debug(error.getDefaultMessage());
            }
            request.setAttribute("message", "非法的请求参数");
            return "forward:modifyUser/" + user.getId();
        }
		user.setModifyBy( ((User)request.getSession().getAttribute(Common.USER_SESSION)).getId() );
		if(userService.updateUser(user)==1) {
			return "redirect:userList";
		}else {
			return "redirect:modifyUser/" + user.getId();
		}
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return json
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Object deleteUser(Long id) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("delResult", "false");
		if(userService.deleteUser(id)==1) {
			result.put("delResult", "true");
		}else{
			result.put("delResult", "notexist");
		}
		return result;
	}
	
	/**
	 * 跳转到用户密码修改页面
	 * @return view
	 */
	@RequestMapping("/pwdmodify")
	public String pwdModify() {
		return "user/pwdmodify";
	}
}
