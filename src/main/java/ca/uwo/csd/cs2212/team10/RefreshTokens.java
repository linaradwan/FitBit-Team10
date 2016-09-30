package ca.uwo.csd.cs2212.team10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import com.github.scribejava.apis.FitbitApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuthService;
import com.github.scribejava.core.model.*; //Request Verb
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.apis.service.FitbitOAuth20ServiceImpl;
import java.awt.Desktop;

import java.net.URI;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * This class will be used to refresh tokens, handle calls to Fitbit and make sure tokens are good
 * @author Pearson, Patrick
 */
public class RefreshTokens
{
	private static String CALL_BACK_URI = "http://localhost:8080";
	private static int CALL_BACK_PORT = 8080;
	private static String units = null;

	/**
	 * Returns the unit type we are using for fitbit
	 * @return String units
	 */
	public static String getUnits()
	{
		return units;
	}

	/**
	 * Sets the unit type specified for fitbit call
	 * @param newUnits String set to imperial
	 */
	public static void setUnits(String newUnits)
	{
		if (newUnits.compareTo("imperial") == 0)
			RefreshTokens.units = "en_US";
		else
			// The default will be metric
			units = null;
	}

	/**
	 * Method gets tokens from /src/main/resources/Team10Tokens.txt
	 * @param requestUrl String that contains a specified FitBit URL
	 * @return String containing the data downloaded from the URL request from the API
	 * @throws TokensException Method uses tokens to interface with API which can throw this error
	 * @throws RateExceededException thrown if rate limit is hit
	 */
	public static String getTokens(String requestUrl) throws TokensException, RateExceededException
	{
		units = RefreshTokens.getUnits();

		// Read credentials from a file
		BufferedReader bufferedReader = null;
		// To reference a line
		String line = null;

		// Service credentials for Fitbit
		String apiKey = null;
		String apiSecret = null;
		String clientID = null;

		// Holder for elements that we make access tokens (authenticated session)
		String accessTokenItself = null;
		String tokenType = null;
		String refreshToken = null;
		String rawResponse = null;
		Long expiresIn = null;

		// Only scope to access currently
		String scope = "activity%20heartrate";
		try
		{
			// File with service credentials
			FileReader fileReader = new FileReader("src/main/resources/Team10Credentials.txt");
			bufferedReader = new BufferedReader(fileReader);
			clientID = bufferedReader.readLine();
			apiKey = bufferedReader.readLine();
			apiSecret = bufferedReader.readLine();
			bufferedReader.close();

			fileReader = new FileReader("src/main/resources/Team10Tokens.txt");
			bufferedReader = new BufferedReader(fileReader);
			accessTokenItself = bufferedReader.readLine();
			tokenType = bufferedReader.readLine();
			refreshToken = bufferedReader.readLine();
			expiresIn = Long.parseLong(bufferedReader.readLine());
			rawResponse = bufferedReader.readLine();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Unable to open file\n" + e.getMessage());
			System.exit(1);
		}
		catch (IOException e)
		{
			System.out.println("Error reading/write file\n" + e.getMessage());
			System.exit(1);
		}
		finally
		{
			try
			{
				// Always close files
				if (bufferedReader != null)
					bufferedReader.close();
			}
			catch (Exception e)
			{
				System.out.println("Error closing file\n" + e.getMessage());
			}
		}

		// Create the Fitbit service - you will ask this to ask for access/refresh pairs
		// and to add authorization infomration to the requests to the API
		FitbitOAuth20ServiceImpl service = (FitbitOAuth20ServiceImpl) new ServiceBuilder().apiKey(clientID)
				.apiSecret(apiSecret).callback("http://localhost:8080").scope(scope).grantType("authorization_code")
				.build(FitbitApi20.instance());

		// The access token contains everything you will need to authenticate your request
		// It can expire - at which you will use the refresh token to refresh it
		// See: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
		OAuth2AccessToken accessToken = new OAuth2AccessToken(accessTokenItself, tokenType, refreshToken, expiresIn,
				rawResponse);

		// System.out.println("Now we're going to access a protected resource...");
		// System.out.println();

		OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);

		// This adds the information required by Fitbit to add the authorization information to the HTTP request
		// YOU MUST DO THIS BEFORE THE REQUEST WILL WORK
		// See: https://dev.fitbit.com/docs/oauth2/#making-requests
		service.signRequest(accessToken, request);
		// Set the units we want fitbit to send us
		request.addHeader("Accept-Language", units);
		// If you are curious, since it has a header, body...
		// System.out.println(request.toString());
		// System.out.println(request.getHeaders());
		// System.out.println(request.getBodyContents());
		// To send the requestURL
		Response response = request.send();

		// The HTTP response from gitbit will be in HTTP format. meaning that is has a numeric code indicating
		// whether it was successfull (200) or not (400's or 500's)
		System.out.println("\nHTTP response code: " + response.getCode());
		int statusCode = response.getCode();

		switch (statusCode)
		{
		case 200:
			System.out.println("HTTP response body:\n" + response.getBody());
			break;
		case 400:
			throw new TokensException("Bad Request\n HTTP resonse body:\n" + response.getBody());
		case 401:
			System.out.println("Likely Expired Token\n HTTP resonse body:\n" + response.getBody());
			System.out.println("Try to refresh");

			// This uses the refresh token to get a completely new accessToken object
			// See: https://dev.fitbit.com/docs/oauth2/#refreshing-tokens
			// This accessToken is now the current one, and the old ones will not work again
			// You should save the contents of accessToken
			accessToken = service.refreshOAuth2AccessToken(accessToken);

			// Now we can try to access the service again
			// Make sure you create a new OAuthRequest obejct each time!

			request = new OAuthRequest(Verb.GET, requestUrl, service);
			service.signRequest(accessToken, request);
			response = request.send();

			// Check again to see what response you got
			System.out.println("HTTP response code: " + response.getCode());
			System.out.println("HTTP response body: " + response.getBody());
			break;
		case 429:
			throw new RateExceededException("Rate Limit Exceeded\n HTTP response body\n" + response.getBody());
		default:
			System.out.println("HTTP response code: " + response.getCode());
			System.out.println("HTTP response body: " + response.getBody());
		}

		BufferedWriter bufferedWriter = null;
		// Save the current accessToken information for next time
		// If you do not save the currently active token info you will not be able to refresh
		// Contact Beth if this happens and she can reissue you a fresh set

		try
		{
			FileWriter fileWriter;
			fileWriter = new FileWriter("src/main/resources/Team10Tokens.txt");
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(accessToken.getToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getTokenType());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRefreshToken());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getExpiresIn().toString());
			bufferedWriter.newLine();
			bufferedWriter.write(accessToken.getRawResponse());
			bufferedWriter.newLine();
			bufferedWriter.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Unable to open file\n" + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("Error reading/write file\n" + e.getMessage());
		}
		finally
		{
			try
			{
				if (bufferedWriter != null)
					bufferedWriter.close();
			}
			catch (Exception e)
			{
				System.out.println("Error closing file\n" + e.getMessage());
			}
		}
		if (response.getCode() != 200)
			throw new TokensException("Tokens refresh failed");
		else
			return response.getBody();
	}
}
