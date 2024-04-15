package org.example;

import domain.*;
import org.junit.Test;
import repository.TemaRepository;
import repository.NotaRepository;
import repository.StudentRepository;
import validation.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BigBangIntegrationTest {
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
    public void testAddAssignmentValid() {
        temaRepo.save(new Tema("1", "Black box testing", 12, 10));
        Tema addedAssignment = temaRepo.findOne("1");
        assertEquals("Verifying assignment is added", "Black box testing", addedAssignment.getDescriere());
    }
    @Test
    public void testAddGradeValid(){
        studentRepo.save(new Student("1", "Emma", 123));

        temaRepo.save(new Tema("1", "Black box ", 12, 10));

        String studentID = "1", temaID = "1";
        Pair<String, String> notaID = new Pair<>(studentID, temaID);

        notaRepo.save(new Nota(notaID, 9, 13, "Good work"));
        Nota addedNota = notaRepo.findOne(notaID);
        assertEquals("Verify new nota is added", notaID, addedNota.getID());
    }

    @Test
    public void BigBangTest()
    {
        studentRepo.save(new Student("1", "Emma", 123));
        Student addedStudent = studentRepo.findOne("1");
        assertEquals("Verifying student is added", "Emma", addedStudent.getNume());

        temaRepo.save(new Tema("1", "Black box testing", 12, 10));
        Tema addedAssignment = temaRepo.findOne("1");
        assertEquals("Verifying assignment is added", "Black box testing", addedAssignment.getDescriere());

        String studentID = "1", temaID = "1";
        Pair<String, String> notaID = new Pair<>(studentID, temaID);

        notaRepo.save(new Nota(notaID, 9, 13, "Good work"));
        Nota addedNota = notaRepo.findOne(notaID);
        assertEquals("Verify new nota is added", notaID, addedNota.getID());
    }

}