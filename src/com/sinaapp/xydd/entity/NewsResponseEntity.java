package com.sinaapp.xydd.entity;

import java.util.List;

import com.sinaapp.xydd.entity.base.BaseResponseData;


public class NewsResponseEntity extends BaseResponseData {
	
	private List<NewsCategoryListEntity> list;

	public List<NewsCategoryListEntity> getList() {
		return list;
	}

	public void setList(List<NewsCategoryListEntity> list) {
		this.list = list;
	}
	
	
}
