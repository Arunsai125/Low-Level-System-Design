/*
    SOLID - Dependency Inversion Principle (DIP)

    Definition:
    High-level modules should not depend on low-level modules.
    Both should depend on abstractions.

    Abstractions should not depend on details.
    Details should depend on abstractions.

    Dependency should be towards interfaces rather than
    concrete implementations.
*/

/*
====================================================
BAD EXAMPLE - Violates Dependency Inversion Principle
====================================================

Problem:
UserRepository directly depends on MySQLDatabase.

If we want to switch from MySQL to PostgreSQL,
we need to modify UserRepository.

High-level module is tightly coupled with low-level module.


class MySQLDatabase {
    public void connect() {
        System.out.println("Connected to MySQL Database.");
    }
}

class UserRepository {

    private MySQLDatabase database;

    public UserRepository() {
        this.database = new MySQLDatabase();
    }

    public void getUser() {
        database.connect();
        System.out.println("Getting user from MySQL Database.");
    }
}
*/

//====================================================
// GOOD EXAMPLE - Dependency Inversion Principle
//====================================================

interface Database {
    void connect();
}

class MySQLDatabase implements Database {

    public void connect() {
        System.out.println("Connected to MySQL Database.");
    }
}

class PostgreSQLDatabase implements Database {

    public void connect() {
        System.out.println("Connected to PostgreSQL Database.");
    }
}

class UserRepository {

    private Database database;

    public UserRepository(Database database) {
        this.database = database;
    }

    public void getUser() {
        database.connect();
        System.out.println("Getting user from Database.");
    }
}

public class DependencyInversionPrinciple {
    public static void main(String[] args) {

        Database database = new MySQLDatabase();

        UserRepository repository = new UserRepository(database);

        repository.getUser();
    }
}