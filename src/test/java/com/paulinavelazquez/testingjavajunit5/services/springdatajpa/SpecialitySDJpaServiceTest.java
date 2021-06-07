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

    @DisplayName("Find By Id Test")
    @Test
    void findByIdTest() {
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

    @DisplayName("Delete By Object Test")
    @Test
    void deleteByObject() {
        // given
        Speciality speciality = new Speciality();

        // when
        service.delete(speciality);

        // then
        then(specialityRepository).should().delete(any(Speciality.class));
    }

    @DisplayName("Delete By Id Test")
    @Test
    void deleteById() {
        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialityRepository).should(times(2)).deleteById(anyLong());
    }

    @DisplayName("Delete By Id At Least Once")
    @Test
    void deleteByIdAtLeast() {
        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialityRepository).should(atLeastOnce()).deleteById(anyLong());
    }

    @DisplayName("Delete By Id At Most")
    @Test
    void deleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);
        verify(specialityRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        // given - none

        // when
        service.deleteById(1L);
        service.deleteById(1L);

        // then
        then(specialityRepository).should(atLeastOnce()).deleteById(anyLong());
        then(specialityRepository).should(never()).deleteById(5L);
    }

    @DisplayName("Delete Test")
    @Test
    void deleteTest() {
        // given
        Speciality speciality = new Speciality();

        // when
        service.delete(speciality);

        // then
        then(specialityRepository).should().delete(any(Speciality.class));
    }
}
