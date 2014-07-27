package com.sinaapp.xydd.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import com.sinaapp.xydd.R;
import com.sinaapp.xydd.ui.base.BaseActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.HttpVersion;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

public class PostActivity extends BaseActivity {
	private DefaultHttpClient client = null;
	private EditText user = null;
	private EditText password = null;
	private EditText status = null;
	private String cat;
	private ImageView mBack;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);

		// begin new onCreate
		// view = (TextView) findViewById(R.id.spinnerText);
		spinner = (Spinner) findViewById(R.id.spinner01);
		// 将可选内容与ArrayAdapter连接起来
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m);

		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
		spinner.setAdapter(adapter);

		// 添加事件Spinner事件监听
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

		// 设置默认值
		spinner.setVisibility(View.VISIBLE);
		// end new onCreate

		user = (EditText) findViewById(R.id.user);
		password = (EditText) findViewById(R.id.password);
		status = (EditText) findViewById(R.id.status);

		Button send = (Button) findViewById(R.id.send);

		send.setOnClickListener(onSend);

		client = new DefaultHttpClient();
		mBack = (ImageView) findViewById(R.id.about_imageview_gohome);
		mBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		client.getConnectionManager().shutdown();
	}

	private String getCredentials() {
		String u = user.getText().toString();
		String p = password.getText().toString();

		return (Base64.encodeBytes((u + ":" + p).getBytes()));
	}

	private void updateStatus() {
		try {
			String s = status.getText().toString();

			HttpPost post = new HttpPost(
					"http://4.xydd.sinaapp.com/an/json.php");

			post.addHeader("Authorization", "Basic " + getCredentials());

			List<NameValuePair> form = new ArrayList<NameValuePair>();

			form.add(new BasicNameValuePair("status", s));

			post.setEntity(new UrlEncodedFormEntity(form, HTTP.UTF_8));

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = client.execute(post, responseHandler);
			JSONObject response = new JSONObject(responseBody);
		} catch (Throwable t) {
			Log.e("Patchy", "Exception in updateStatus()", t);
			goBlooey(t);
		}
	}

	private void goBlooey(Throwable t) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Exception!").setMessage(t.toString())
				.setPositiveButton("OK", null).show();
	}

	private View.OnClickListener onSend = new View.OnClickListener() {
		public void onClick(View v) {
			updateStatus();
		}
	};

	// spinner begin
	private static final String[] m = { "新鲜事", "校园知道", "跳蚤市场", "失物招领", "商家资讯" };
	private TextView view;
	private Spinner spinner;
	private ArrayAdapter<String> adapter;

	// 使用数组形式操作
	class SpinnerSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// view.setText("你的血型是："+m[arg2]);
			cat = m[arg2];
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}
}