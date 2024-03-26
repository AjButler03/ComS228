package cgpt4;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OpenAIAPIExample {

    public static void main(String[] args) {
        // Replace "YOUR_API_KEY" with your actual OpenAI API key
        String apiKey = "YOUR_API_KEY";

        try {
            // Replace this prompt with your desired input
            String prompt = "What is the Most Beautiful Equation of Math?";

            // Construct the URL with the API key
            String apiUrl = "https://api.openai.com/v1/engines/text-davinci-003/completions";
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Set the Content-Type and Authorization headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);

            // Enable input/output streams
            connection.setDoOutput(true);

            // Build the request payload in JSON format
            String jsonPayload = "{\"prompt\":\"" + prompt + "\",\"max_tokens\":50}"; // Adjust parameters as needed

            // Write the payload to the request
            try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
                byte[] input = jsonPayload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Read the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                
                while ((inputLine = in.readLine()) != null) {
                	System.out.println(inputLine);
                    // response.append(inputLine);
                }


                in.close();

                // Print the response
                // System.out.println(response.toString());
            } else {
                System.out.println("Error: " + responseCode);
            }

            // Disconnect the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
