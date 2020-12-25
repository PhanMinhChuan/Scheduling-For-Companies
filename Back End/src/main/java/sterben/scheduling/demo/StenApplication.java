package sterben.scheduling.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import sterben.scheduling.demo.entity.Job;
import sterben.scheduling.demo.entity.User;
import sterben.scheduling.demo.repository.JobRepository;
import sterben.scheduling.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StenApplication{

    public static void main(String[] args) {
        SpringApplication.run(StenApplication.class, args);
    }

    /*@Autowired
    JobRepository jobRepository;

    @Override
    public void run(String... args) throws Exception {
        *//*List<Long> asd = new ArrayList<Long>();
        asd.add((long) 1);
        asd.add((long) 2);
        asd.add((long) 5);
        asd.add((long) 7);*//*
        List<Long> asd1 = new ArrayList<Long>();
        asd1.add((long) 2);
        asd1.add((long) 3);
        asd1.add((long) 5);
        asd1.add((long) 9);
        List<String> a1 = new ArrayList<String>();
        a1.add("1: Nhân team 3 đã đọc");
        a1.add("5: Ok Anh");

        List<String> a = new ArrayList<String>();
        a.add("3: Chuẩn team 1 đã đọc");
        a.add("5: Đã xem");

        jobRepository.save(new Job(3, "Chuẩn bị đi công tác", "Lễ Tân", asd1
                , null, a1));

    }*/

    /*@Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<Long> asd1 = new ArrayList<>();
        asd1.add((long) 1);
        asd1.add((long) 2);
        User user = new User();
        user.setId(3);
        user.setUsername("Chippy");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setIdJob(asd1);
        user.setRole("ROLE_STAFF");
        userRepository.save(user);
    }*/

}
