package ua.company.training.kukuruza.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.company.training.kukuruza.dao.*;
import ua.company.training.kukuruza.entity.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SummaryRatingServiceTest {
    @InjectMocks
    private SummaryRatingService service;
    @Mock
    private AbstractDaoFactory factory;

    @Test
    void getUserRequestWithExistingRequestTest() {
        Long userId = 42L;
        Request request = new Request.Builder()
                .setUserId(userId)
                .setRating(175)
                .build();
        Optional<Request> expected = Optional.of(request);
        IDaoRequest daoRequest = mock(IDaoRequest.class);
        when(factory.getDaoRequest()).thenReturn(daoRequest);
        when(daoRequest.findByUserId(userId)).thenReturn(expected);

        Optional<Request> actual = service.getUserRequest(userId);

        assertEquals(expected, actual);
    }

    @Test
    void getUserRequestWithNotExistingRequestTest() {
        Long userId = 42L;
        IDaoRequest daoRequest = mock(IDaoRequest.class);
        when(factory.getDaoRequest()).thenReturn(daoRequest);
        when(daoRequest.findByUserId(userId)).thenReturn(Optional.empty());

        Optional<Request> actual = service.getUserRequest(userId);

        assertTrue(!actual.isPresent());
    }

    @Test
    void getUserIdToRatingTest() {
        Long educationOptionId = 42L;
        Long userId1 = 1L;
        Long userId2 = 2L;
        Integer userRating1 = 175;
        Integer userRating2 = 190;
        Request request1 = new Request.Builder()
                .setUserId(userId1)
                .setRating(userRating1)
                .build();
        Request request2 = new Request.Builder()
                .setUserId(userId2)
                .setRating(userRating2)
                .build();
        List<Request> requests = new ArrayList<>();
        requests.add(request1);
        requests.add(request2);
        Map<Long, Integer> expected = new HashMap<>();
        expected.put(userId1, userRating1);
        expected.put(userId2, userRating2);
        IDaoRequest daoRequest = mock(IDaoRequest.class);
        when(factory.getDaoRequest()).thenReturn(daoRequest);
        when(daoRequest.findByEducationOptionId(educationOptionId)).thenReturn(requests);

        Map<Long, Integer> actual = service.getUserIdToRatingOrderByRating(educationOptionId);

        assertEquals(expected, actual);
    }

    @Test
    void getUserIdToRatingWithWrongEducationOptionIdTest() {
        Long wrongEducationOptionId = 42L;
        IDaoRequest daoRequest = mock(IDaoRequest.class);
        when(factory.getDaoRequest()).thenReturn(daoRequest);
        when(daoRequest.findByEducationOptionId(wrongEducationOptionId)).thenReturn(Collections.emptyList());

        Map<Long, Integer> actual = service.getUserIdToRatingOrderByRating(wrongEducationOptionId);

        assertTrue(actual.isEmpty());
    }

    @Test
    void getUserIdToUserByIdSetTest() {
        Long userId1 = 1L;
        Long userId2 = 2L;
        Set<Long> usersId = new HashSet<>();
        usersId.add(userId1);
        usersId.add(userId2);
        User user1 = new User.Builder()
                .setId(userId1)
                .build();
        User user2 = new User.Builder()
                .setId(userId2)
                .build();
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Map<Long, User> expected = new HashMap<>();
        expected.put(userId1, user1);
        expected.put(userId2, user2);
        IDaoUser daoUser = mock(IDaoUser.class);
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByIdSet(usersId)).thenReturn(users);

        Map<Long, User> actual = service.getUserIdToUserByIdSet(usersId);

        assertEquals(expected, actual);
    }

    @Test
    void getUserIdToUserByIdSetWithEmptyIdSetTest() {
        Set<Long> usersId = Collections.emptySet();
        IDaoUser daoUser = mock(IDaoUser.class);
        when(factory.getDaoUser()).thenReturn(daoUser);
        when(daoUser.findByIdSet(usersId)).thenReturn(Collections.emptyList());

        Map<Long, User> actual = service.getUserIdToUserByIdSet(usersId);

        assertTrue(actual.isEmpty());
    }

    @Test
    void getUniversityNameTest() {
        Long educationOptionId = 42L;
        Integer universityId = 7;
        String expected = "universityName";
        EducationOption educationOption = new EducationOption.Builder()
                .setId(educationOptionId)
                .setUniversityId(universityId)
                .build();
        University university = new University.Builder()
                .setId(universityId)
                .setName(expected)
                .build();
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        IDaoUniversity daoUniversity = mock(IDaoUniversity.class);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(factory.getDaoUniversity()).thenReturn(daoUniversity);
        when(daoEducationOption.find(educationOptionId)).thenReturn(Optional.of(educationOption));
        when(daoUniversity.find(universityId)).thenReturn(Optional.of(university));

        String actual = service.getUniversityName(educationOptionId);

        assertEquals(expected, actual);
    }

    @Test
    void getUniversityNameWithWrongEducationOptionIdTest() {
        Long wrongEducationOptionId = 42L;
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(daoEducationOption.find(wrongEducationOptionId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getUniversityName(wrongEducationOptionId));
    }

    @Test
    void getSpecialtyNameTest() {
        Long educationOptionId = 42L;
        Integer specialtyId = 7;
        String expected = "specialtyName";
        EducationOption educationOption = new EducationOption.Builder()
                .setId(educationOptionId)
                .setSpecialtyId(specialtyId)
                .build();
        Specialty specialty = new Specialty.Builder()
                .setId(specialtyId)
                .setName(expected)
                .build();
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        IDaoSpecialty daoSpecialty = mock(IDaoSpecialty.class);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(factory.getDaoSpecialty()).thenReturn(daoSpecialty);
        when(daoEducationOption.find(educationOptionId)).thenReturn(Optional.of(educationOption));
        when(daoSpecialty.find(specialtyId)).thenReturn(Optional.of(specialty));

        String actual = service.getSpecialtyName(educationOptionId);

        assertEquals(expected, actual);
    }

    @Test
    void getSpecialtyNameWithWrongEducationOptionIdTest() {
        Long wrongEducationOptionId = 42L;
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(daoEducationOption.find(wrongEducationOptionId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getSpecialtyName(wrongEducationOptionId));
    }

    @Test
    void getStudentLimitTest() {
        Long educationOptionId = 42L;
        Integer expected = 50;
        EducationOption educationOption = new EducationOption.Builder()
                .setId(educationOptionId)
                .setStudentLimit(expected)
                .build();
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(daoEducationOption.find(educationOptionId)).thenReturn(Optional.of(educationOption));

        Integer actual = service.getStudentLimit(educationOptionId);

        assertEquals(expected, actual);
    }

    @Test
    void getStudentLimitWithWrongEducationOptionIdTest() {
        Long wrongEducationOptionId = 42L;
        IDaoEducationOption daoEducationOption = mock(IDaoEducationOption.class);
        when(factory.getDaoEducationOption()).thenReturn(daoEducationOption);
        when(daoEducationOption.find(wrongEducationOptionId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getStudentLimit(wrongEducationOptionId));
    }
}
