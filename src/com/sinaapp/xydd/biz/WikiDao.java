package com.sinaapp.xydd.biz;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import com.sinaapp.xydd.config.Constants;
import com.sinaapp.xydd.config.Urls;
import com.sinaapp.xydd.entity.WikiJson;
import com.sinaapp.xydd.entity.WikiMoreResponse;
import com.sinaapp.xydd.entity.WikiResponseEntity;
import com.sinaapp.xydd.utils.RequestCacheUtil;
import com.sinaapp.xydd.utils.Utility;

import android.app.Activity;

public class WikiDao extends BaseDao {

	public WikiDao(Activity activity) {
		super(activity);
	}

	private WikiResponseEntity mWikiResponseEntity;

	public WikiResponseEntity getmWikiResponseEntity() {
		return mWikiResponseEntity;
	}

	public void setmWikiResponseEntity(WikiResponseEntity mWikiResponseEntity) {
		this.mWikiResponseEntity = mWikiResponseEntity;
	}

	public WikiResponseEntity mapperJson(boolean useCache) {
		// TODO Auto-generated method stub
		WikiJson wikiJson;
		try {
			String result = RequestCacheUtil.getRequestContent(mActivity,
					Urls.WIKI_LIST + Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, useCache);
			wikiJson = mObjectMapper.readValue(result,
					new TypeReference<WikiJson>() {
					});
			if (wikiJson == null) {
				return null;
			}
			this.mWikiResponseEntity = wikiJson.getResponse();
			return mWikiResponseEntity;
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
	
	public WikiMoreResponse getMore(String more_url) {
		WikiMoreResponse response;
		try {
			String result = RequestCacheUtil.getRequestContent(mActivity,
					more_url + Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, true);
			response = mObjectMapper.readValue(result,
					new TypeReference<WikiMoreResponse>() {
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
