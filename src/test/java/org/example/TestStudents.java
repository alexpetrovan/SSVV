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
}