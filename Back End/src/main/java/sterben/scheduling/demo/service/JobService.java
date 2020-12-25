package sterben.scheduling.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sterben.scheduling.demo.entity.Job;

import java.util.List;
import java.util.Optional;

@Service
public interface JobService {

    Page<Job> findAllJob(Integer page, Integer size);

    Page<Job> getListByIdUser(long idUser, Integer page, Integer size);

    List<Job> findJobByStatus();

    List<Job> findAllJobNotPage();

    void createJob(Job job);

    void commentJob(long id, String comment);

    void UpdateJob(long id, Job job);

    void deletedJob(long id);

    Optional<Job> findJobById(Long id);

    String changeStatusJob(long id, long idUser);
}
