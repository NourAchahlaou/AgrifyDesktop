package agrify.services;

import agrify.entities.Presence;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicePresence implements IServicePresence<Presence> {
    private Connection connection;

public ServicePresence(Connection connection) {
        this.connection = connection;
    }

public boolean savePresence(Presence presence) {
        String insertQuery = "INSERT INTO presence (user_id, date, presenceState) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) 
            {
            
                preparedStatement.setInt(1, presence.getUser_id());
                Date sqlDate = Date.valueOf(presence.getDate());
                preparedStatement.setDate(2, sqlDate);
                preparedStatement.setString(3, presence.getPresenceState());

                int rowsAffected = preparedStatement.executeUpdate();
                    return rowsAffected > 0;
            } 
        catch (SQLException ex)     
            {
                System.out.println("Error  while saving presence record: " + ex.getMessage());
                return false;
            }
    }
}
