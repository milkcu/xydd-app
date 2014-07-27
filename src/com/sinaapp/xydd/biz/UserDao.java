package com.sinaapp.xydd.biz;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import com.sinaapp.xydd.config.Urls;
import com.sinaapp.xydd.entity.UserJson;
import com.sinaapp.xydd.entity.UserResponse;
import com.sinaapp.xydd.https.HttpUtils;
import com.sinaapp.xydd.utils.Utility;

import android.content.Context;

public class UserDao extends BaseDao {
	private Context mContext;

	public UserDao(Context context) {
		mContext = context;
	}

	public UserResponse mapperJson(String key) {
		// TODO Auto-generated method stub
		UserJson userJson;
		try {
			if (!key.contains(":")) {
				return null;
			}
			String url = String.format(Urls.KEYBindURL, key)
					+ Utility.getParams(key);
			String result = HttpUtils.getByHttpClient(mContext, url);
			userJson = mObjectMapper.readValue(result,
					new TypeReference<UserJson>() {
					});
			if (userJson == null) {
				return null;
			}
			return userJson.getResponse();
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
}
