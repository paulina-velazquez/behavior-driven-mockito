package com.paulinavelazquez.testingjavajunit5.services.springdatajpa;

import com.paulinavelazquez.testingjavajunit5.model.Speciality;
import com.paulinavelazquez.testingjavajunit5.repositories.SpecialityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    // Return value from mock example (Tested method returns Object of type Speciality)
    @DisplayName("Find by Id Unit Test")
    @Test
    void findById() {
        Speciality speciality = new Speciality();
        when(specialityRepository.findById(1L)).thenReturn(Optional.of(speciality));
        Speciality foundSpeciality = service.findById(1L);
        // JUnit5
        assertNotNull(foundSpeciality);
        // AssertJ
        assertThat(foundSpeciality).isNotNull();
        verify(specialityRepository, times(1)).findById(1L);
    }

    @DisplayName("Find By Id BDD Test")
    @Test
    void findByIdBddTest() {
        // given (setup of the text)
        Speciality speciality = new Speciality();
        given(specialityRepository.findById(1L)).willReturn(Optional.of(speciality));

        // when (action of the test - ie when method is called)
        Speciality foundSpeciality = service.findById(1L);

        // then (verification of expected results)
        assertNotNull(foundSpeciality);
        then(specialityRepository).should().findById(anyLong());
        then(specialityRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }

    // Argument Matcher (Tested method returns void)
    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();
        service.delete(speciality);
        verify(specialityRepository).delete(any(Speciality.class));
    }

    // Verify Example
    @Test
    void deleteByIdAndVerify() {
        // values must match
        service.deleteById(1L);
        verify(specialityRepository).deleteById(1L);
    }

    @Test
    void deleteByIdTwice() {
        service.deleteById(1L);
        service.deleteById(1L);
        verify(specialityRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1L);
        service.deleteById(1L);
        verify(specialityRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);
        verify(specialityRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);
        service.deleteById(1L);
        verify(specialityRepository, atLeastOnce()).deleteById(1L);
        verify(specialityRepository, never()).deleteById(5L);
    }

    @Test
    void deleteById() {
        service.deleteById(1l);
    }
}


















