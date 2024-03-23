package com.valletta.pass.controller.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("job")
public class JobLauncherController {

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    @PostMapping("/launcher")
    public ExitStatus launchJob(@RequestBody JobLaunchRequest request) throws Exception {
//    public String launchJob(@RequestBody JobLaunchRequest request) throws Exception {

        try {
//            JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("time", System.currentTimeMillis())
//                .toJobParameters();
            Job job = jobRegistry.getJob(request.getName());
            return this.jobLauncher.run(job, request.getJobParameters()).getExitStatus();
//            return this.jobLauncher.run(job, jobParameters).getExitStatus();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return null;
//        return "Done";
    }
}
