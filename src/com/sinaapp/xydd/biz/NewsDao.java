package com.sinaapp.xydd.biz;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import com.sinaapp.xydd.config.Constants;
import com.sinaapp.xydd.config.Urls;
import com.sinaapp.xydd.entity.NewsJson;
import com.sinaapp.xydd.entity.NewsMoreResponse;
import com.sinaapp.xydd.entity.NewsResponseEntity;
import com.sinaapp.xydd.utils.RequestCacheUtil;
import com.sinaapp.xydd.utils.Utility;

import android.app.Activity;

public class NewsDao extends BaseDao {

	public NewsDao(Activity activity) {
		super(activity);
	}

	private NewsResponseEntity _newsResponse;

	public NewsResponseEntity get_newsResponse() {
		return _newsResponse;
	}

	public void set_newsResponse(NewsResponseEntity _newsResponse) {
		this._newsResponse = _newsResponse;
	}

	public NewsResponseEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub
		NewsJson newsJson;
		try {
			//传递参数的时候把 + Utility.getScreenParams(mActivity)去掉了
			//useCache改为false
			String result = RequestCacheUtil.getRequestContent(mActivity,
					Urls.NEWS_LIST,
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, false);
			newsJson = mObjectMapper.readValue(result,
					new TypeReference<NewsJson>() {
					});
			if (newsJson == null) {
				return null;
			}
			this._newsResponse = newsJson.getResponse();
			return _newsResponse;

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

	public NewsMoreResponse getMore(String more_url) {
		NewsMoreResponse response;
		try {
			String result = RequestCacheUtil.getRequestContent(mActivity,
					more_url + Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, true);
			response = mObjectMapper.readValue(result,
					new TypeReference<NewsMoreResponse>() {
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
