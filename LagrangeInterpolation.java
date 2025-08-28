import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONTokener;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class LagrangeInterpolation {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("test_case.json");
            JSONObject json = new JSONObject(new JSONTokener(fis));

            JSONObject keys = json.getJSONObject("keys");
            int n = keys.getInt("n");
            int k = keys.getInt("k");

            List<long[]> points = new ArrayList<>();
            for (String key : json.keySet()) {
                if (!key.equals("keys")) {
                    int x = Integer.parseInt(key);
                    JSONObject obj = json.getJSONObject(key);
                    int base = Integer.parseInt(obj.getString("base"));
                    String value = obj.getString("value");
                    BigInteger y = new BigInteger(value, base);
                    points.add(new long[]{x, y.longValue()});
                }
            }

            points = points.subList(0, k);

            double c = lagrangeInterpolation(points);
            System.out.println((long)c);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static double lagrangeInterpolation(List<long[]> points) {
        double result = 0.0;
        int n = points.size();
