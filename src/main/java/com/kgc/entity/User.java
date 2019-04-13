package com.kgc.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kgc.util.validateGroup.CheckDefault;
import com.kgc.util.validateGroup.CheckInsert;
import com.kgc.util.validateGroup.CheckUpdate;

public class User {
	
	@NotNull(message="ID不能为空", groups={CheckUpdate.class})
	private Long id; // id
	
	@NotEmpty(message="用户编码不能为空", groups={CheckInsert.class})
	private String userCode; // 用户编码
	
	@NotBlank(message="用户名称不能为空", groups={CheckDefault.class})
	private String userName; // 用户名称
	
	@NotNull(message="用户密码不能为空", groups={CheckInsert.class})
	@Size(max=20,min=6,message="用户密码必须在{min}-{max}之间", groups={CheckInsert.class})
	private String userPassword; // 用户密码
	
	private Integer gender; // 性别
	
	//接收前台参数字符串,转为Date
	@DateTimeFormat(pattern="yyyy-MM-dd")
	//后台传JSON到前台时转为指定格式的String类型
	@JsonFormat(pattern="yyyy-MM-dd")
	//参数校验  @Past过去的日期,@Future将来的日期
	@NotNull(message="出生日期不能为空", groups={CheckDefault.class})
	@Past(message="出生日期不正确", groups= {CheckDefault.class})
	private Date birthday; // 出生日期
	
	@NotNull(message="手机号码不能为空", groups={CheckDefault.class})
	@Pattern(regexp="^1(3|4|5|7|8)\\d{9}$", message="手机号码格式错误", groups={CheckDefault.class})
	private String phone; // 电话

    private String address;

    @NotNull(message="用户角色不能为空", groups={CheckDefault.class})
    private Integer userRole;

    private Long createdBy;

    private Date creationDate;

    private Long modifyBy;

    private Date modifyDate;
    
	//-------------------------------------华丽的分割线----------------
    private String userRoleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", userCode=" + userCode + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", gender=" + gender + ", birthday=" + birthday + ", phone=" + phone + ", address=" + address
				+ ", userRole=" + userRole + ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + "]";
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
    
}