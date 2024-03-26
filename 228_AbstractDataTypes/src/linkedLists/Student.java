package linkedLists;

public class Student {

    private String firstName;
    private String lastName;
    private int id;
    private char section;
    private String classification;

    public Student(String firstName, String lastName, int id, char section, String classification) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.section = section;
        this.classification = classification;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getClassification() {
        return classification;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (this.id != student.id) return false;
        if (!this.firstName.equals(student.firstName)) return false;
        if (!this.lastName.equals(student.lastName)) return false;
        if (this.section != student.section) return false;
        
        return this.classification.equals(student.classification);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + id;       // id is integer primitive data type
        result = 31 * result + section;  // section is char primitive data type
        result = 31 * result + classification.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id  +
                ", section ='" + section + '\'' +
                ", classification ='" + classification + '\'' +
                '}';
    }

}
