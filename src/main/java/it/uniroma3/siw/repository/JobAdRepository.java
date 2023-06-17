package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.JobAd;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface JobAdRepository extends CrudRepository<JobAd, Long> {
    Set<JobAd> findTop15ByOrderByIdDesc();


    //IO CI HO PROVATO, MA NON FUNZIONA, LO FACCIO DAL CONTROLLER
//    // prendi le jobads dalla tabella che hanno id uguale a quelli trovati dalla query dopo
//    // prendi gli id delle jobads nella tabella job_applications che hanno piu applicazioni
//    // ordina per numero di jobapplications
//    // il pageable e' per farci scegliere quante ne vogliamo
//    // VA RITORNATA UNA LISTA! SET DA ERRORE
//    @Query(value =
//            "SELECT * " +
//            "FROM job_ads " +
//            "WHERE id IN (" +
//                "SELECT id_job_ad " +
//                "FROM job_applications " +
//                "GROUP BY id_job_ad " +
//                "ORDER BY COUNT(*) DESC " +
//                "LIMIT 5" +
//            ")",
//            nativeQuery = true)
//    List<JobAd> findTop5JobAdsWithMostApplications();
}
