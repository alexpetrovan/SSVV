package org.example;

import domain.Tema;
import org.junit.Test;
import repository.TemaRepository;
import repository.TemaXMLRepository;

import validation.TemaValidator;
import validation.Validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestAssignments {
    Validator<Tema> temaValidator = new TemaValidator();

    TemaRepository repo = new TemaRepository(temaValidator);

    @Test
    public void testAddAssignmentValid() {
        repo.save(new Tema("1", "Empty", 12, 10));
        Tema addedAssignment = repo.findOne("1");
        assertEquals("Verifying assignment is added", "Empty", addedAssignment.getDescriere());
    }

    @Test
    public void testAddAssignmentInvalidStartEndDate(){
        repo.save(new Tema("1", "Empty", 12, 13));
        assertNull("Verifying assignment with invalid start date", repo.findOne("1"));
    }

    @Test
    public void testAddAssignmentInvalidStartDateTooSmall(){
        repo.save(new Tema("1", "Empty", 12, 0));
        assertNull("Verifying assignment with invalid start date", repo.findOne("1"));
    }

    @Test
    public void testAddAssignmentInvalidStartDateTooBig(){
        repo.save(new Tema("1", "Empty", 12, 15));
        assertNull("Verifying assignment with invalid start date", repo.findOne("1"));
    }

    @Test
    public void testAddAssignmentInvalidEndDateTooSmall(){
        repo.save(new Tema("1", "Empty", 0, 2));
        assertNull("Verifying assignment with invalid start date", repo.findOne("1"));
    }

    @Test
    public void testAddAssignmentInvalidEndDateTooBig(){
        repo.save(new Tema("1", "Empty", 15, 12));
        assertNull("Verifying assignment with invalid start date", repo.findOne("1"));
    }

    @Test
    public void testAddAssignmentDescriptionNull(){
        repo.save(new Tema("1", null, 15, 12));
        assertNull("Verifying assignment with invalid start date", repo.findOne("1"));
    }

    @Test
    public void testAddAssignmentDescriptionEmpty(){
        repo.save(new Tema("1", "", 15, 12));
        assertNull("Verifying assignment with invalid start date", repo.findOne("1"));
    }
}
