package sterben.scheduling.demo.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import sterben.scheduling.demo.CommonUtil.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "job")
public class Job {
    @Id
    private long id;

    @Field(value = "content")
    private String content;

    @Field(value = "location")
    private String location;

    @Field(value = "deadline")
    private LocalDateTime deadline;

    @Field(value = "idUser")
    private List<Long> idUser;

    @Field(value = "comment")
    private List<String> comment;

    @Field(value = "status")
    @Enumerated(EnumType.STRING)
    private Status.STATUS_JOB status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Long> getIdUser() {
        return idUser;
    }

    public void setIdUser(List<Long> idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> Comment) {
        this.comment = Comment;
    }

    public Status.STATUS_JOB getStatus() {
        return status;
    }

    public void setStatus(Status.STATUS_JOB status) {
        this.status = status;
    }

    public Job(long id, String content, String location, List<Long> idUser, LocalDateTime deadline, List<String> comment) {
        this.id = id;
        this.content = content;
        this.location = location;
        this.idUser = idUser;
        this.deadline = deadline;
        this.comment = comment;
    }

    public Job() {
    }
}
