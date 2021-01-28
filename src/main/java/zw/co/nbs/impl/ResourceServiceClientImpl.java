package zw.co.nbs.impl;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import zw.co.nbs.api.ResourceServiceClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class ResourceServiceClientImpl implements ResourceServiceClient {

    public void getResource(){
        ClientConfig clientConfig = new ClientConfig();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "password");
        clientConfig.register( feature) ;

        clientConfig.register(JacksonFeature.class);

        Client client = ClientBuilder.newClient( clientConfig );
        WebTarget webTarget = client.target("https://reqres.in/api/").path("users");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        System.out.println(response.getStatus());
        System.out.println(response.getStatusInfo());


        if(response.getStatus() == 200)
        {
          System.out.println(response.readEntity(String.class));
        }
    }

}
