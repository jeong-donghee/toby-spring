import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {
    int counter = 0;
    private ConnectionMaker realConnectionMaker;

    public void setRealConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return realConnectionMaker.makeConnection();
    }

    public int getCounter() {
        return counter;
    }
}
