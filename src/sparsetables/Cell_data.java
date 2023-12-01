package sparsetables;

import sparsetables.Students_linkedList.Student;

import sparsetables.CoursesList.Course;

public class Cell_data {

    private Student my_student;
    private Course my_course;

    private Cell_data nextCellRight; // Reference to the next cell in the right direction
    private Cell_data nextCellDown; // Reference to the next cell in the down direction

    private Cell_data nextCellleft; // Reference to the previous cell in the left direction
    private Cell_data nextCellup; // Reference to the previous cell in the up direction

    // Constructor to initialize cell data
    public Cell_data(Student student, Course course) {
        this.my_student = student;
        this.my_course = course;
    }

    // Getter method for the next cell in the right direction
    public Cell_data getNextCellRight() {
        return this.nextCellRight;
    }

    // Getter method for the next cell in the down direction
    public Cell_data getNextCellDown() {
        return this.nextCellDown;
    }

    // Setter method for the next cell in the down direction
    public void setNextCellDown(Cell_data nextCellDown) {
        this.nextCellDown = nextCellDown;
    }

    // Setter method for the previous cell in the left direction
    public void setNextCellleft(Cell_data nextCellleft) {
        this.nextCellleft = nextCellleft;
    }

    // Setter method for the previous cell in the up direction
    public void setNextCellup(Cell_data nextCellup) {
        this.nextCellup = nextCellup;
    }

    // Setter method for the next cell in the right direction
    public void setNextCellRight(Cell_data nextCellRight) {
        this.nextCellRight = nextCellRight;
    }

    public Students_linkedList.Student getMy_student() {
        return my_student;
    }

    public void setMy_student(Students_linkedList.Student my_student) {
        this.my_student = my_student;
    }

    public CoursesList.Course getMy_course() {
        return my_course;
    }

    public void setMy_course(CoursesList.Course my_course) {
        this.my_course = my_course;
    }

    public Cell_data getNextCellleft() {
        return nextCellleft;
    }

    public Cell_data getNextCellup() {
        return nextCellup;
    }

}
