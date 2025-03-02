public class DaoFactory {
    public UserDao userDao() {
        ConnectionMaker connectionMaker = getConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }

    private static DConnectionMaker getConnectionMaker() {
        return new DConnectionMaker();
    }
}
