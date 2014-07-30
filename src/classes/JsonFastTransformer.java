package classes;

import com.google.gson.Gson;

public class JsonFastTransformer {

	public static <T> T getObjects(String jsonString, Class<T> forInit) {
		 T result = null;
		 Gson gson = new Gson();
		 try {
		    	result = gson.fromJson(jsonString, forInit);
		     } 
		 catch (Exception e) {
		    	e.printStackTrace();
		    }

		 return result;
	}

	public static String objectsToJson(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}
}