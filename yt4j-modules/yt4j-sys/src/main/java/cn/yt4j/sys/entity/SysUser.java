/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.yt4j.sys.entity;

import cn.yt4j.core.sensitive.Sensitive;
import cn.yt4j.core.sensitive.SensitiveTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 用户(SysUser)表实体类
 *
 * @author gyv12345@163.com
 * @since 2020-08-07 17:11:42
 */
@ApiModel(value = "SysUser对象", description = "用户")
@Getter
@Setter
@ToString
public class SysUser extends Model<SysUser> {

	@ApiModelProperty(value = "id")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "部门ID")
	private Long deptId;

	@ApiModelProperty(value = "昵称")
	private String nickName;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	@Sensitive(type = SensitiveTypeEnum.ID_CARD)
	@ApiModelProperty(value = "身份证号")
	private String idCard;

	@ApiModelProperty(value = "头像")
	private String avatar;

	@ApiModelProperty(value = "性别")
	private Integer sex;

	@Sensitive(type = SensitiveTypeEnum.EMAIL)
	@ApiModelProperty(value = "电子邮箱")
	private String email;

	@Sensitive(type = SensitiveTypeEnum.MOBILE_PHONE)
	@ApiModelProperty(value = "手机号")
	private String phone;

	@ApiModelProperty(value = "状态")
	private Boolean state;

	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;

	@ApiModelProperty(value = "角色Id")
	@TableField(exist = false)
	private List<Long> roleIds;


}
