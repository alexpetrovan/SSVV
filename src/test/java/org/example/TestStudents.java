package org.example;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import domain.Student;
import org.junit.Test;
import repository.*;
import validation.StudentValidator;
import validation.Validator;

public class TestStudents {
    Validator<Student> studentValidator = new StudentValidator();

    StudentRepository repo = new StudentRepository(studentValidator);

    @Test
    public void testAddStudent() {
        repo.save(new Student("1", "Emma", 123));
        Student addedStudent = repo.findOne("1");
        assertEquals("Verifying student is added", "Emma", addedStudent.getNume());
    }

    @Test
    public void testAddStudentWithInvalidGroup() {
        repo.save(new Student("1", "John", 109));
        assertNull("Verifying student with invalid group is not added", repo.findOne("1"));
    }

    @Test
    public void testAddStudentInvalidId(){
        repo.save(new Student("", "Johnny", 123));
        assertNull("Verifying student with invalid group", repo.findOne(""));
    }

    @Test
    public void testAddStudentInvalidName(){
        repo.save(new Student("1", "", 123));
        assertNull("Verifying student with invalid group", repo.findOne("1"));
    }

    @Test public void testAddStudentValidGroupBoundaries(){
        repo.save(new Student("1", "Johnny 111", 111));
        Student addedStudent = repo.findOne("1");
        assertEquals("Verifying student is added", "Johnny 111", addedStudent.getNume());

        repo.save(new Student("2", "Johnny 112", 112));
        addedStudent = repo.findOne("2");
        assertEquals("Verifying student is added", "Johnny 112", addedStudent.getNume());

        repo.save(new Student("3", "Johnny 936", 936));
        addedStudent = repo.findOne("3");
        assertEquals("Verifying student is added", "Johnny 936", addedStudent.getNume());

        repo.save(new Student("4", "Johnny 937", 937));
        addedStudent = repo.findOne("4");
        assertEquals("Verifying student is added", "Johnny 937", addedStudent.getNume());
    }

    @Test public void testAddStudentInvalidGroupBoundaries(){
        repo.save(new Student("1", "Johnny 110", 110));
        assertNull("Verifying student with invalid group", repo.findOne("1"));

        repo.save(new Student("1", "Johnny 938", 938));
        assertNull("Verifying student with invalid group", repo.findOne("1"));

    }
}