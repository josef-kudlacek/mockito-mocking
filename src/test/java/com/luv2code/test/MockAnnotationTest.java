package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CollegeStudent collegeStudent;

    @Autowired
    private StudentGrades studentGrades;

    @MockBean
    private ApplicationDao applicationDao;

    @Autowired
    private ApplicationService applicationService;

    @BeforeEach
    public void beforeEach() {
        collegeStudent.setFirstname("Josef");
        collegeStudent.setLastname("Kudláček");
        collegeStudent.setEmailAddress("joseph.kudlacek@gmail.com");
        collegeStudent.setStudentGrades(studentGrades);
    }

    @DisplayName("When & Verify")
    @Test
    public void assertEqualsTestAddGrades() {
        when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults()))
                .thenReturn(100.00);

        assertEquals(100, applicationService.addGradeResultsForSingleClass(
                collegeStudent.getStudentGrades().getMathGradeResults()));

        Mockito.verify(applicationDao, times(1)).addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults());
    }

    @DisplayName("Find Gpa")
    @Test
    public void assertEqualsTestFindGpa() {
        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults()))
                .thenReturn(88.31);

        assertEquals(88.31, applicationService.findGradePointAverage(collegeStudent
                .getStudentGrades().getMathGradeResults()));
    }

}
