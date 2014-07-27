package com.sinaapp.xydd.biz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import com.sinaapp.xydd.config.Constants;
import com.sinaapp.xydd.config.Urls;
import com.sinaapp.xydd.entity.BlogsCategoryListEntity;
import com.sinaapp.xydd.entity.BlogsMoreResponse;
import com.sinaapp.xydd.entity.CategorysEntity;
import com.sinaapp.xydd.entity.NewsCategoryListEntity;
import com.sinaapp.xydd.entity.NewsMoreResponse;
import com.sinaapp.xydd.entity.WikiCategoryListEntity;
import com.sinaapp.xydd.entity.WikiMoreResponse;
import com.sinaapp.xydd.utils.RequestCacheUtil;
import com.sinaapp.xydd.utils.Utility;

import android.app.Activity;

public class TopDao extends BaseDao {

	private NewsCategoryListEntity newsCategorys;
	private BlogsCategoryListEntity blogsCategorys;
	private WikiCategoryListEntity wikiCategorys;

	List<CategorysEntity> tabs = new ArrayList<CategorysEntity>();

	public TopDao(Activity activity) {
		super(activity);
	}

	public List<Object> mapperJson(boolean useCache) {
		List<Object> topCategorys = new ArrayList<Object>();
		tabs.clear();
		try {
			String resultNews = RequestCacheUtil.getRequestContent(mActivity,
					Urls.TOP_NEWS_URL + Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, useCache);
			NewsMoreResponse newsMoreResponse = mObjectMapper.readValue(
					resultNews, new TypeReference<NewsMoreResponse>() {
					});
			if (newsMoreResponse != null) {
				this.newsCategorys = newsMoreResponse.getResponse();
			}
			String resultBlogs = RequestCacheUtil.getRequestContent(mActivity,
					Urls.TOP_BLOG_URL + Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, useCache);
			BlogsMoreResponse blogsMoreResponse = mObjectMapper.readValue(
					resultBlogs, new TypeReference<BlogsMoreResponse>() {
					});
			if (blogsMoreResponse != null) {
				this.blogsCategorys = blogsMoreResponse.getResponse();
			}
			String resultWiki = RequestCacheUtil.getRequestContent(mActivity,
					Urls.TOP_WIKI_URL + Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, useCache);
			WikiMoreResponse wikiMoreResponse = mObjectMapper.readValue(
					resultWiki, new TypeReference<WikiMoreResponse>() {
					});
			if (wikiMoreResponse != null) {
				this.wikiCategorys = wikiMoreResponse.getResponse();
			}
			Collections.addAll(topCategorys, newsCategorys, blogsCategorys,
					wikiCategorys);

			return topCategorys;
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
		CategorysEntity cate2 = new CategorysEntity();
		CategorysEntity cate3 = new CategorysEntity();
		cate1.setName("精选资讯");
		cate2.setName("精选博客");
		cate3.setName("精选教程");
		tabs.add(cate1);
		tabs.add(cate2);
		tabs.add(cate3);
		return tabs;
	}

}
