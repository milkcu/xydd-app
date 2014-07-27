package com.sinaapp.xydd.biz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import com.sinaapp.xydd.config.Urls;
import com.sinaapp.xydd.entity.BlogSearchJson;
import com.sinaapp.xydd.entity.CategorysEntity;
import com.sinaapp.xydd.entity.NewsSearchJson;
import com.sinaapp.xydd.entity.WikiSearchJson;
import com.sinaapp.xydd.https.HttpUtils;
import com.sinaapp.xydd.utils.Utility;

import android.app.Activity;

public class SearchDao extends BaseDao {

	private String mTag;
	private String keyWord;
	private String cate_name;
	private boolean hasChild = true;

	public SearchDao(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();

	public SearchDao(String tag, String key) {
		this.mTag = tag;
		this.keyWord = key;
	}

	public void setValue(String mTag, String mKey) {
		this.mTag = mTag;
		this.keyWord = mKey;
	}

	private List<Object> categorys = new ArrayList<Object>();

	/**
	 * 这里的逻辑判断得优化 目前仅为实现功能编写的代码 不妥
	 * 
	 * @return
	 */
	public List<Object> mapperJson() {
		categorys.clear();
		tabs.clear();
		hasChild = false;
		try {
			if (mTag.equals("news")) {
				NewsSearchJson newsSearchJson = mObjectMapper.readValue(
						HttpUtils.getByHttpClient(
								mActivity,
								Urls.BASE_SEARCH_URL + "t=" + mTag + "&w="
										+ keyWord
										+ Utility.getScreenParams(mActivity)),
						new TypeReference<NewsSearchJson>() {
						});
				categorys.add(newsSearchJson.getResponse());
				cate_name = newsSearchJson.getResponse().getName();
				if (newsSearchJson.getResponse().getItems() != null) {
					hasChild = true;
				}
			} else if (mTag.equals("wiki")) {
				WikiSearchJson wikiSearchJson = mObjectMapper.readValue(
						HttpUtils.getByHttpClient(mActivity,
								Urls.BASE_SEARCH_URL + "t=" + mTag + "&w="
										+ keyWord),
						new TypeReference<WikiSearchJson>() {
						});
				categorys.add(wikiSearchJson.getResponse());
				cate_name = wikiSearchJson.getResponse().getName();
				if (wikiSearchJson.getResponse().getItems() != null) {
					hasChild = true;
				}
			} else if (mTag.equals("blog")) {
				BlogSearchJson blogSearchJson = mObjectMapper.readValue(
						HttpUtils.getByHttpClient(mActivity,
								Urls.BASE_SEARCH_URL + "t=" + mTag + "&w="
										+ keyWord),
						new TypeReference<BlogSearchJson>() {
						});
				categorys.add(blogSearchJson.getResponse());
				cate_name = blogSearchJson.getResponse().getName();
				if (blogSearchJson.getResponse().getItems() != null) {
					hasChild = true;
				}
			}
			return categorys;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<CategorysEntity> getCategorys() {
		CategorysEntity cate1 = new CategorysEntity();

		cate1.setName(cate_name);

		tabs.add(cate1);
		return tabs;
	}

	public boolean getHasChild() {
		return hasChild;
	}
}
