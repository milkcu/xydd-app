package com.sinaapp.xydd.entity;

import java.util.List;

import com.sinaapp.xydd.entity.base.BaseResponseData;

/**
 * blog response的实体类的封装
 * @author wangxin
 *
 */
public class BlogsResponseEntity extends BaseResponseData{
	
	private List<BlogsCategoryListEntity> list; //response 中的List的封装

	public List<BlogsCategoryListEntity> getList() {
		return list;
	}

	public void setList(List<BlogsCategoryListEntity> list) {
		this.list = list;
	}
	
}
