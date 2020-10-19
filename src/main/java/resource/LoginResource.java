package resource;

import io.agroal.api.AgroalDataSource;
import io.quarkus.security.identity.SecurityIdentity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Path("/loginResource")
public class LoginResource {

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    AgroalDataSource defaultDataSource;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public void login(Form form) {
        System.out.println(form.asMap().entrySet());
        form.asMap().get("username");
        form.asMap().get("password");
        System.out.println("Now what?");
        System.out.println("SecurityIdentity: " + securityIdentity.getPrincipal().getName());

        //Check for confirmation that database is indeed there
        PreparedStatement preparedStatement;
        try {
            preparedStatement = defaultDataSource.getConnection().prepareStatement("select *  from test_user;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                System.out.print(resultSet.getString("username"));
                System.out.print(" ");
                System.out.println(resultSet.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
