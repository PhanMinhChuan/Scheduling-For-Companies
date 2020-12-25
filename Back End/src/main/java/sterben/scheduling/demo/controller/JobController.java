package sterben.scheduling.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sterben.scheduling.demo.CommonUtil.Const;
import sterben.scheduling.demo.CommonUtil.Status;
import sterben.scheduling.demo.entity.Job;
import sterben.scheduling.demo.service.JobService;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("jobs")
@CrossOrigin
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Job>> findAllJobPage(@RequestParam(value="page", defaultValue = Const.NUMBER_PAGE_START_DEFAULT) Integer page,
                                                 @RequestParam(value="size", defaultValue = Const.NUMBER_SIZE_PAGE_DEFAULT) Integer size) {
        return new ResponseEntity<>(jobService.findAllJob(page, size).getContent(), HttpStatus.OK);
    }

    @GetMapping(path = "{idUser}", consumes = "application/json")
    @PreAuthorize("hasAnyRole('ADMIN, STAFF')")
    public ResponseEntity<List<Job>> findJobByIdUser(@RequestParam(value="page", defaultValue = Const.NUMBER_PAGE_START_DEFAULT) Integer page,
                                                     @RequestParam(value="size", defaultValue = Const.NUMBER_SIZE_PAGE_DEFAULT) Integer size,
                                                     @PathVariable Long idUser) {
        return new ResponseEntity<>(jobService.getListByIdUser(idUser, page, size).getContent(), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Job> findJobNotFinish() {
        //return new ResponseEntity<>(jobService.findJobByStatus(page, size).getContent(), HttpStatus.OK);
        return jobService.findJobByStatus();
    }

    @PutMapping("{id}/{idUser}")
    @PreAuthorize("hasAnyRole('ADMIN, STAFF')")
    public String changeStatusJob(@PathVariable Long id, @PathVariable Long idUser) {
        return jobService.changeStatusJob(id, idUser);
    }

    @PatchMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Job>> findAllJob() {
        return new ResponseEntity<>(jobService.findAllJobNotPage(), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Optional<Job>> findJoById(@PathVariable Long id) {
        return new ResponseEntity<>(jobService.findJobById(id), HttpStatus.OK);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> CreateJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "{id}", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> UpdateJob(@PathVariable Long id, @RequestBody Job job) {
        jobService.UpdateJob(id, job);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{id}/{comment}")
    @PreAuthorize("hasAnyRole('ADMIN, STAFF')")
    public ResponseEntity<Void> CommentJob(@RequestBody Long id, @RequestBody String comment) {
        jobService.commentJob(id, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletedJobs(@PathVariable Long id) {
        jobService.deletedJob(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}