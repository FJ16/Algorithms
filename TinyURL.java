import java.util.HashMap;
import java.util.Map;

public class TinyURL {
    public String designTinyURL() {
        return "";
    }

    Map<Integer, String> lts = new HashMap<>(); // key: long.hashCode() value: longUrl, 确保TinyURL是唯一对应(用作key)
    public String longToShort(String longURL) {
        if (longURL == null || longURL.length() == 0) return null;
        int hash = longURL.hashCode();

        lts.put(hash, longURL);
        return "http://tinyurl.com/" + hash;
    }
    // The hash code for a String object is computed(using int arithmetic) as −
    //
    //s[0]*31^(n - 1) + s[1]*31^(n - 2) + ... + s[n - 1], where s[i] is the ith character of the string, n is the length of the string.
    public String shortToLong(String shortURL) {
        return lts.get(Integer.valueOf(shortURL.replace("http://tinyurl.com/", "")));
    }
}
