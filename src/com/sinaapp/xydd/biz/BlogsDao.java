package com.sinaapp.xydd.biz;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import com.sinaapp.xydd.config.Constants;
import com.sinaapp.xydd.config.Urls;
import com.sinaapp.xydd.entity.BlogsJson;
import com.sinaapp.xydd.entity.BlogsMoreResponse;
import com.sinaapp.xydd.entity.BlogsResponseEntity;
import com.sinaapp.xydd.utils.RequestCacheUtil;
import com.sinaapp.xydd.utils.Utility;

import android.app.Activity;

public class BlogsDao extends BaseDao {

	private BlogsResponseEntity _blogsResponse;

	public BlogsDao(Activity activity) {
		super(activity);
	}

	public BlogsResponseEntity getBlogsResponse() {
		return _blogsResponse;
	}

	public void setBlogsResponse(BlogsResponseEntity blogsResponse) {
		this._blogsResponse = blogsResponse;
	}

	public BlogsResponseEntity mapperJson(boolean useCache) {
		BlogsJson blogsJson_;
		try {
			String result = RequestCacheUtil.getRequestContent(mActivity,
					Urls.BLOGS_LIST + Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, useCache);
			blogsJson_ = mObjectMapper.readValue(result,
					new TypeReference<BlogsJson>() {
					});
			if (blogsJson_ == null) {
				return null;
			}
			_blogsResponse = blogsJson_.getResponse();
			return _blogsResponse;
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

	public BlogsMoreResponse getMore(String more_url) {
		BlogsMoreResponse response;
		try {
			String result = RequestCacheUtil.getRequestContent(mActivity,
					more_url + Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, true);
			response = mObjectMapper.readValue(result,
					new TypeReference<BlogsMoreResponse>() {
					});
			return response;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
