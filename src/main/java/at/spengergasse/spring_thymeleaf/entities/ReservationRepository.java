package at.spengergasse.spring_thymeleaf.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    List<Reservation> findByDeviceId(Integer deviceId);

    boolean existsByDateAndTimeAndDevice_Id(LocalDate date, String time, Integer deviceId);

    boolean existsByDateAndTimeAndPatient_Id(LocalDate date, String time, Integer patientId);
}
