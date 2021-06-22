/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.yt4j.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 字典(SysDict)表实体类
 *
 * @author gyv12345@163.com
 * @since 2020-08-10 08:43:31
 */
@ApiModel(value = "SysDict对象", description = "字典")
@Getter
@Setter
@ToString
public class SysDict extends Model<SysDict> {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 字典编码
	 */
	@ApiModelProperty(value = "字典关键字")
	private String code;

	/**
	 * 字典名称
	 */
	@ApiModelProperty(value = "字典名称")
	private String name;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private Integer state;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;

	@NotNull
	@ApiModelProperty(hidden = true)
	@TableField(exist = false)
	private List<SysDictItem> items;


}
