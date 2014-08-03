package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.content.Context;
import android.os.AsyncTask;

public class AsynchDownloader<T, S> extends AsyncTask<Void, Void, Void> {
	private final String url;
	private DownloaderProgressDialog dialog;
	private Class<?> forInit;
	private AsyncTaskCompleteListener<S> context;
	private JsonStringRealization callback;

	public <T extends JsonStringRealization> AsynchDownloader(
			AsyncTaskCompleteListener<S> context, final String url, Class<T> forInit) {
		this.url = url;
		this.context = context;
		this.dialog = new DownloaderProgressDialog((Context)context);
		this.forInit = forInit;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		this.dialog.show();
	}

	public static String getStringFromResponse(HttpResponse response) {
		String responseBody = "";
		try {
			InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				str.append(line);
			}
			in.close();
			responseBody = str.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseBody;
	}

	private final String getRequest() {
		HttpResponse response = null;
		int code = -1;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			response = httpclient.execute(httpGet);
			code = response.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		final String returnString = getStringFromResponse(response);
		return returnString;
	}

	public JsonStringRealization getJsonStringRealization() {
		return callback;
	}

	@Override
	protected Void doInBackground(Void... params) {
		final String jsonString = getRequest();
		callback = (JsonStringRealization) JsonFastTransformer.getObjects(
				jsonString, forInit);
		
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		dialog.dismiss();
        context.onTaskComplete((S)callback);
	}
}
