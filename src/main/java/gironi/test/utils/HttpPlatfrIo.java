package gironi.test.utils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Give access to GET and POST HTTP request
 */
public final class HttpPlatfrIo {

    /**
     *
     * @param urlToRead: the URL to perform the GET HTTP request
     * @return the result of the request
     * @throws Exception
     */
    public static JsonObject getHTML(String urlToRead) throws Exception {
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Set the header for the request
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Auth-Schema", "S2S");
        conn.setRequestMethod("GET");

        // Now read the responce
        JsonReader jsonReader = Json.createReader(conn.getInputStream());
        return jsonReader.readObject();
    }

    /**
     *
     * @param urlToRead: the URL to perform the POST HTTP request
     * @return the result of the request
     * @throws Exception
     */
    public static JsonObject postHTML(String urlToRead, HashMap<String, String> bodyParamaters) throws Exception {
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Set the header for the request
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Auth-Schema", "S2S");

        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");

        JsonObjectBuilder objB = Json.createObjectBuilder();
        bodyParamaters.forEach( (k, v)->objB.add(k, v) );

        JsonObject obj = objB.build();
        byte[] postDataBytes = obj.toString().getBytes("UTF-8");
        conn.getOutputStream().write(postDataBytes);


        // Now read the responce
        JsonReader jsonReader = Json.createReader(conn.getInputStream());
        return jsonReader.readObject();
    }

}
