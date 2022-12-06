package tracker;

public class User {


    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private int javaPoints;
    private int dsaPoints;
    private int databasesPoints;
    private int springPoints;

    User(String firstName, String lastName, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return firstName + " " + lastName;
    }

    public int getJavaPoints() {
        return javaPoints;
    }

    public int getDsaPoints() {
        return dsaPoints;
    }

    public int getDatabasesPoints() {
        return databasesPoints;
    }

    public int getSpringPoints() {
        return springPoints;
    }

    public void setJavaPoints(int javaPoints) {
        if (javaPoints > 0) {
            this.javaPoints = this.javaPoints + javaPoints;
            UserDatabase.getCourse("Java").setPoints(this.id, javaPoints);
        }
    }

    public void setDsaPoints(int dsaPoints) {
        if (dsaPoints > 0) {
            this.dsaPoints = this.dsaPoints + dsaPoints;
            UserDatabase.getCourse("DSA").setPoints(this.id, dsaPoints);
        }
    }

    public void setDatabasesPoints(int databasesPoints) {
        if (databasesPoints > 0) {
            this.databasesPoints = this.databasesPoints + databasesPoints;
            UserDatabase.getCourse("Databases").setPoints(this.id, databasesPoints);
        }
    }

    public void setSpringPoints(int springPoints) {
        if (springPoints > 0) {
            this.springPoints = this.springPoints + springPoints;
            UserDatabase.getCourse("Spring").setPoints(this.id, springPoints);
        }
    }
}
