Explanation
Fetching the API Response: The getApiResponse method makes a GET request to the provided API URL using HttpURLConnection and returns the JSON response as a string.

Parsing the JSON Data: The main method parses the JSON response into a JSONArray of matches.

Computations:

Highest Score: Iterate through each match, parse the scores, and update the highest score and the corresponding team name.
Matches with 300+ Total Score: Check if the sum of the scores of both teams is 300 or more and count such matches.
Printing the Results: The computed results are printed in the console.

Compilation and Execution
Save the code in a file named CricketMatchData.java.
Open a terminal or command prompt and navigate to the directory containing CricketMatchData.java.
Compile the code using the command:
sh
Copy code
javac CricketMatchData.java
Run the compiled Java program using the command:
sh
Copy code
java CricketMatchData
This will print the desired results to the console. You can zip the working code and send it as required.
