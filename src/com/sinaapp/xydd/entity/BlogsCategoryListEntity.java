package com.sinaapp.xydd.entity;

import java.util.List;

import com.sinaapp.xydd.entity.base.BaseContentList;


/**
 * 博客列表的分类的list的实体类
 * @author wangxin
 *
 */
public class BlogsCategoryListEntity extends BaseContentList{
	private List<BlogContentItem> items;

	public List<BlogContentItem> getItems() {
		return items;
	}

	public void setItems(List<BlogContentItem> items) {
		this.items = items;
	}
	
	
}
