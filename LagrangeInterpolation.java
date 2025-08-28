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
            // Load JSON file
            FileInputStream fis = new FileInputStream("test_case.json");
            JSONObject json = new JSONObject(new JSONTokener(fis));

            JSONObject keys = json.getJSONObject("keys");
            int n = keys.getInt("n");
            int k = keys.getInt("k");

            // Decode points
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

            // Use only first k points
            points = points.subList(0, k);

            double c = lagrangeInterpolation(points);
            System.out.println("Constant term c = " + (long)c);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // Lagrange Interpolation
    private static double lagrangeInterpolation(List<long[]> points) {
        double result = 0.0;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            double xi = points.get(i)[0];
            double yi = points.get(i)[1];
            double term = yi;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double xj = points.get(j)[0];
                    term *= (0 - xj) / (xi - xj); // Evaluate at x=0 for constant term
                }
            }
            result += term;
        }
        return result;
    }
}
