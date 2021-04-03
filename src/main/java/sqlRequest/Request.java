package sqlRequest;

public class Request {
        public static final String INSERT_INTO_USERS = "INSERT INTO users (Id, Firstname, Lastname) values('%s','%s', '%s', '%s');";
        public static final String UPDATE_ROLE = "UPDATE users SET Role = '%s' WHERE id = %s;";
        public static final String SET_TYPE_OF_MSG = "UPDATE users SET type = '%s' WHERE id = %s;";
        public static final String SET_LAST_ID = "UPDATE users SET lastid = '%s' WHERE id = %s;";
        public static final String DELETE_USER = "DELETE FROM users WHERE id = %s;";
        public static final String GET_ALL_DATA = "SELECT * FROM users;";
        public static final String CREATE_TIME_TABLE = "CREATE TABLE IF NOT EXISTS table_%s (time text);";
        public static final String INSERT_INTO_TIME_TABLE = "INSERT INTO table_%s (time) values (%s)";
        public static final String DELETE_TIME_TABLE = "DROP TABLE IF EXISTS table_%s";
        public static final String GET_TIME_TABLE_DATA = "SELECT * FROM table_%s;";
    }
