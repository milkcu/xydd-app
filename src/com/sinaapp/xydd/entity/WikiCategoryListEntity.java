package com.sinaapp.xydd.entity;

import java.util.List;

import com.sinaapp.xydd.entity.base.BaseContentList;


public class WikiCategoryListEntity extends BaseContentList {

		private List<WikiContentItem> items;

		public List<WikiContentItem> getItems() {
			return items;
		}

		public void setItems(List<WikiContentItem> list) {
			this.items = list;
		}
		
		
}
