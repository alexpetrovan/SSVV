package org.example;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaRepository;
import repository.StudentRepository;
import repository.TemaRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.Assert.assertEquals;

public class IncrementalIntegrationTest {
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    TemaRepository temaRepo = new TemaRepository(temaValidator);
    StudentRepository studentRepo = new StudentRepository(studentValidator);
    NotaRepository notaRepo = new NotaRepository(notaValidator);

    @Test
    public void testAddStudent() {
        studentRepo.save(new Student("1", "Emma", 123));
        Student addedStudent = studentRepo.findOne("1");
        assertEquals("Verifying student is added", "Emma", addedStudent.getNume());
    }

    @Test
    public void testAddAssignmentIncremental(){
        studentRepo.save(new Student("1", "Emma", 123));
        Student addedStudent = studentRepo.findOne("1");
        assertEquals("Verifying student is added", "Emma", addedStudent.getNume());

        temaRepo.save(new Tema("1", "Empty", 12, 10));
        Tema addedAssignment = temaRepo.findOne("1");
        assertEquals("Verifying assignment is added", "Empty", addedAssignment.getDescriere());
    }

    @Test
    public void testAddGradeIncremental(){
        studentRepo.save(new Student("1", "Emma", 123));
        Student addedStudent = studentRepo.findOne("1");
        assertEquals("Verifying student is added", "Emma", addedStudent.getNume());

        temaRepo.save(new Tema("1", "Empty", 12, 10));
        Tema addedAssignment = temaRepo.findOne("1");
        assertEquals("Verifying assignment is added", "Empty", addedAssignment.getDescriere());

        notaRepo.save(new Nota(new Pair<>("1", "1"),
                8.5, // or any other double value for the grade
                2, // or any other int value for the week
                "Some feedback"));
        Nota addedGrade = notaRepo.findOne(new Pair<>("1", "1"));
        assertEquals("Verifying grade is added", "Some feedback", addedGrade.getFeedback());
    }
}
