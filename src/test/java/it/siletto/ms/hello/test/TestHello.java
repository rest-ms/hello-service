package it.siletto.ms.hello.test;

import static org.fest.assertions.api.Assertions.assertThat;
import io.dropwizard.testing.junit.DropwizardAppRule;
import it.siletto.ms.hello.AppConfiguration;
import it.siletto.ms.hello.HelloServiceApp;

import org.junit.ClassRule;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestHello {

	@ClassRule
    public static final DropwizardAppRule<AppConfiguration> RULE = new DropwizardAppRule<AppConfiguration>(HelloServiceApp.class, "app.yml");
	
	@Test
	public void testHello() throws Exception {

		String token = "IBq8Hk9gsfGBzje/B3loex8c5Y1yCKlj+ExuKgwWuHUIY7bFJHYRFJ/6pkK1xGYE2dk3uHGWICCbUH81WuutH1xNbBbdrE25Rz2voszChTYJnzpkB98PGsZ3FBDiohSdeeb/UWEU7y/8CWbijsAI8bMFnzcSnFTcRjCi5O8mb0kDeK5onQg0YuvJKbKQjmDVoul1VXiM1aJHYTuJGewqHfNJccvTzuWTjlUaOXFs97vGMDhHeTE86MHYn5Mg5hxPttVcQh2DFGyMVZpBDdMoUj2r5qxsJXnj1BruS8tLhUoIH0M0VIUMqsorC10XkRTvkWFLr3kjSo/wCdlrhgEfTQ==";
		
		Client client = Client.create();

		System.out.println("saying hello...");
				
		WebResource whoami = client.resource(String.format("http://localhost:%d/hello", RULE.getLocalPort()));
		ClientResponse response = whoami.header("Authentication", "Bearer "+token).accept("application/json").get(ClientResponse.class);
		
		assertThat(response.getStatus()).isEqualTo(200);

		String hello = response.getEntity(String.class);
		System.out.println("response: "+hello);

		assertThat(hello).isEqualTo("{\"hello\":\"pippo\"}");
	}
}
