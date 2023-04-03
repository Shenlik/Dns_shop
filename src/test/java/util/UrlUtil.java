package util;

import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class UrlUtil {

    public static Map<String, String> getQueryParams(String url) {
        var pairs =  URLEncodedUtils.parse(url, StandardCharsets.UTF_8);
        var queryParams = new HashMap<String, String>();
        for (var param: pairs) {
            queryParams.put(param.getName(), param.getValue());
        }
        return queryParams;
    }
}
