package sterben.scheduling.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import sterben.scheduling.demo.entity.Job;
import sterben.scheduling.demo.entity.User;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, Long> {
    Job findById(long id);

    //List<Job> findByUserId(long idUser);

    //@Query(value = "SELECT comment FROM job WHERE id =?1")
    //List<String> findCommentById();
}
