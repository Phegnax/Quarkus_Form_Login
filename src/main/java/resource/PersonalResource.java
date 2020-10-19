package resource;

import io.quarkus.security.identity.SecurityIdentity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path("/me")
public class PersonalResource {

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    public String me() {
        //Should this return my name? ("User" / "Admin", depending on who is logged in)
        return securityIdentity.getPrincipal().getName();
    }

    @PUT
    public void newMe() {
        // in Case I want to change something, I need to know who I am
    }
}
