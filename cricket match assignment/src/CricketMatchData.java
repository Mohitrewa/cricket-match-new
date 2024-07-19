import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class CricketMatchData {

    private static final String API_URL = "https://api.cuvora.com/car/partner/cricket-data";
    private static final String API_KEY = "test-creds@2320";

    public static void main(String[] args) {
        try {
            // Step 1: Make GET request to the API
            String jsonResponse = getApiResponse(API_URL);

            // Step 2: Parse JSON response
            JSONArray matches = new JSONArray(jsonResponse);

            // Step 3: Perform computations
            String highestScoreTeam = "";
            int highestScore = 0;
            int matchesWith300PlusScore = 0;

            for (int i = 0; i < matches.length(); i++) {
                JSONObject match = matches.getJSONObject(i);
                int t1Score = parseScore(match.getString("t1s"));
                int t2Score = parseScore(match.getString("t2s"));

                // Find the highest score
                if (t1Score > highestScore) {
                    highestScore = t1Score;
                    highestScoreTeam = match.getString("t1");
                }
                if (t2Score > highestScore) {
                    highestScore = t2Score;
                    highestScoreTeam = match.getString("t2");
                }

                // Count matches with total 300+ score
                if ((t1Score + t2Score) >= 300) {
                    matchesWith300PlusScore++;
                }
            }

            // Step 4: Print the results
            System.out.println("Highest Score : " + highestScore);
            System.out.println("Highest Score and Team Name is : " + highestScoreTeam);
            System.out.println("Number Of Matches with total 300 Plus Score : " + matchesWith300PlusScore);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getApiResponse(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("apiKey", API_KEY);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        
        in.close();
        conn.disconnect();
        
        return content.toString();
    }

    private static int parseScore(String score) {
        // Parse score in format "123/4" or "123"
        if (score == null || score.isEmpty() || score.equals("N/A")) {
            return 0;
        }
        return Integer.parseInt(score.split("/")[0]);
    }
}
