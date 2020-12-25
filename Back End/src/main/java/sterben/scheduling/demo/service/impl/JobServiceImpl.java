package sterben.scheduling.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sterben.scheduling.demo.CommonUtil.Status;
import sterben.scheduling.demo.entity.Job;
import sterben.scheduling.demo.entity.User;
import sterben.scheduling.demo.repository.JobRepository;
import sterben.scheduling.demo.repository.UserRepository;
import sterben.scheduling.demo.service.JobService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Job> findAllJobNotPage() {
        return jobRepository.findAll();
    }

    @Override
    public Page<Job> findAllJob(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return jobRepository.findAll(pageable);
    }

    @Override
    public Page<Job> getListByIdUser(long idUser, Integer page, Integer size) {
        List<Job> jobList = jobRepository.findAll();
        List<Job> jobListByUserId = new ArrayList<>();
        for(int i = 0; i < jobList.size(); i++) {
            List<Long> idUserList = jobList.get(i).getIdUser();
            if (idUserList != null) {
                for (int j = 0; j < idUserList.size(); j++ ) {
                    if (idUserList.get(j) == idUser) {
                        jobListByUserId.add(jobList.get(i));
                    }
                }
            }

        }
        Pageable pageable = PageRequest.of(page, size);
        int total = jobListByUserId.size();
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min((start + pageable.getPageSize()), total);

        List<Job> output = new ArrayList<>();
        if (start <= end) {
            output = jobListByUserId.subList(start, end);
        }
        return new PageImpl <>(
                output,
                pageable,
                total
        );
    }

    @Override
    public List<Job> findJobByStatus() {
        //LocalDateTime timeNow = LocalDateTime.now();
        List<Job> jobList = jobRepository.findAll();
        List<Job> jobNotDoneList = new ArrayList<>();
        for(int i = 0; i < jobList.size(); i++) {
            //LocalDateTime deadline = jobList.get(i).getDeadline();
            //System.out.println(jobList.get(i).getDeadline());
            if (jobList.get(i).getStatus() != Status.STATUS_JOB.DA_HOAN_THANH && jobList.get(i).getDeadline() != null) {
                //System.out.println("lan" + i +":"+timeNow.getDayOfYear() +":"+ deadline.getDayOfYear());
                //if (timeNow.getDayOfYear() >= deadline.getDayOfYear()) {
                    jobNotDoneList.add(jobList.get(i));
                //}
            }
        }
        /*if (!jobNotDoneList.isEmpty()) {
            Pageable pageable = PageRequest.of(page, size);
            int total = jobNotDoneList.size();
            int start = Math.toIntExact(pageable.getOffset());
            int end = Math.min((start + pageable.getPageSize()), total);

            List<Job> output = new ArrayList<>();
            if (start <= end) {
                output = jobNotDoneList.subList(start, end);
            }
            return new PageImpl <>(
                    output,
                    pageable,
                    total
            );
        }*/
        return jobNotDoneList;
    }

    @Override
    public void createJob(Job job) {
        job.setId(jobRepository.findAll().get(jobRepository.findAll().size() - 1).getId() + 1);
        jobRepository.save(job);
    }

    @Override
    public void UpdateJob(long id, Job job) {
        Job jobEx = jobRepository.findById(id);
        jobEx.setContent(job.getContent());
        jobEx.setLocation(job.getLocation());
        jobEx.setIdUser(job.getIdUser());
        jobEx.setDeadline(job.getDeadline());
        jobEx.setStatus(job.getStatus());
        jobRepository.save(jobEx);
    }

    @Override
    public void commentJob(long id, String comment) {
        Job job = jobRepository.findById(id);
        job.getComment().add(comment);
        jobRepository.save(job);
    }

    @Override
    public void deletedJob(long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public Optional<Job> findJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public String changeStatusJob(long id, long idUser) {
        boolean booleanCheck = true;
        if (jobRepository.findById(id) != null) {
            Job job = jobRepository.findById(id);
            List<Long> listIdUser = jobRepository.findById(id).getIdUser();
            if (listIdUser != null) {
                for (int i = 0; i < listIdUser.size(); i++) {
                    if (listIdUser.get(i) == idUser) {
                        User user = userRepository.findById(idUser);
                        String Comment = user.getUsername() + " ("+ idUser + ")" +": Đã hoàn thành xong công việc";
                        List<String> listComment = job.getComment();
                        if (listComment == null) {
                            listComment = new ArrayList<>();
                        } else {
                            for (int j = 0; j < listComment.size(); j++) {
                                if (Comment.equals(listComment.get(j))) {
                                    booleanCheck = false;
                                }
                            }
                        }
                        if (booleanCheck) {
                            listComment.add(Comment);
                            job.setComment(listComment);
                            jobRepository.save(job);
                            return "1";
                        } else {
                            return "2";
                        }
                    }
                }
            }
        }
        return "0";
    }
}
