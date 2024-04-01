package org.example;

import domain.Tema;
import org.junit.Test;
import repository.TemaRepository;
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
    public void testAddAssignmentInvalidStartDate(){
        repo.save(new Tema("1", "Empty", 12, 13));
        assertNull("Verifying assignment with invalid start date", repo.findOne("1"));
    }
}
