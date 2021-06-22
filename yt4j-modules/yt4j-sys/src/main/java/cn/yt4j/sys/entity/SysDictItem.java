/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.yt4j.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (SysDictItem)表实体类
 *
 * @author makejava
 * @since 2020-08-18 16:44:07
 */
@ApiModel(value = "SysDictItem对象", description = "")
@Getter
@Setter
@ToString
public class SysDictItem extends Model<SysDictItem> {

	@ApiModelProperty(value = "id", example = "1")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "字典编码", example = "1")
	private String code;

	@ApiModelProperty(value = "标题", example = "1")
	private String label;

	@ApiModelProperty(value = "值", example = "1")
	private String value;

	@ApiModelProperty(value = "创建时间", example = "1")
	private LocalDateTime createTime;

	@ApiModelProperty(value = "修改时间", example = "1")
	private LocalDateTime updateTime;


}
