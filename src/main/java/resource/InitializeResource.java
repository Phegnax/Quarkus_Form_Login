package resource;

import io.agroal.api.AgroalDataSource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Path("/initialize")
public class InitializeResource {

    @Inject
    AgroalDataSource defaultDataSource;

    @GET
    public void initializeDB() {
        String createQuery = "CREATE TABLE test_user (" +
                "id INT," +
                "username VARCHAR(255)," +
                "password VARCHAR(255)," +
                "role VARCHAR(255)" +
                ");";

        String insertAdminQuery = "INSERT INTO test_user (id, username, password, role) VALUES (1, 'admin', 'admin', 'admin');";
        String insertUserQuery = "INSERT INTO test_user (id, username, password, role) VALUES (2, 'user','user', 'user');";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = defaultDataSource.getConnection().prepareStatement(createQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            preparedStatement = defaultDataSource.getConnection().prepareStatement(insertAdminQuery);
            ResultSet resultSet1 = preparedStatement.executeQuery();

            preparedStatement = defaultDataSource.getConnection().prepareStatement(insertUserQuery);
            ResultSet resultSet2 = preparedStatement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
