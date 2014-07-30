package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ParserURL {
	public static String parse(final String urlM3u) {

		final String format = "http";
		String urlString = null;

		try {
			URL urlPage = new URL(urlM3u);
			HttpURLConnection connection = (HttpURLConnection) urlPage
					.openConnection();
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));

			StringBuffer stringBuffer = new StringBuffer();

			while ((urlString = bufferedReader.readLine()) != null) {
				if (urlString.contains(format)) {
					connection.disconnect();
					bufferedReader.close();
					inputStream.close();
					return urlString;
				}
				stringBuffer.append(urlString);
			}

			connection.disconnect();
			bufferedReader.close();
			inputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
