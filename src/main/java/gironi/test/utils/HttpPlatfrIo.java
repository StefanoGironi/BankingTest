package gironi.test.utils;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

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
        HttpClient cli = new HttpClient();

        GetMethod gm = new GetMethod(urlToRead);

        // Set the header for the request
        gm.setRequestHeader(new Header("Content-Type", "application/json"));
        gm.setRequestHeader(new Header("Auth-Schema", "S2S"));

        cli.executeMethod(gm);

        // Now read the responce
        JsonReader jsonReader = Json.createReader(gm.getResponseBodyAsStream());

        return jsonReader.readObject();
    }

    /**
     *
     * @param urlToRead: the URL to perform the POST HTTP request
     * @return the result of the request
     * @throws Exception
     */
    public static JsonObject postHTML(String urlToRead, HashMap<String, String> bodyParamaters) throws Exception {
        HttpClient cli = new HttpClient();

        PostMethod pm = new PostMethod(urlToRead);

        // Set the header for the request
        pm.setRequestHeader(new Header("Content-Type", "application/json"));
        pm.setRequestHeader(new Header("Auth-Schema", "S2S"));

        // Create the JSON object to send using bodyParamaters
        JsonObjectBuilder objB = Json.createObjectBuilder();
        bodyParamaters.forEach( (k, v)->objB.add(k, v) );

        JsonObject obj = objB.build();

        // Now create the request Body
        NameValuePair[] nvp = new NameValuePair[1];
        nvp[0] = new NameValuePair(null, obj.toString());
        pm.setRequestBody(nvp);

        cli.executeMethod(pm);

        // Read the responce
        JsonReader jsonReader = Json.createReader(pm.getResponseBodyAsStream());


        return jsonReader.readObject();
    }

}
