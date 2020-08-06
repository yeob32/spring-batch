package com.example.demo.scheduler.api;

import com.example.demo.scheduler.ScheduleService;
import com.example.demo.scheduler.ScheduleTask;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SchedulerApi {

    private final ScheduleService scheduleService;

    @GetMapping(value = "/schedule/stop")
    public void cancelSchedule(@RequestParam("id") String id) {
        scheduleService.stop(id);
    }

    @PostMapping(value = "/schedule/register")
    public void registerSchedule(String id, String name, String expression, String actionTarget) {
        ScheduleTask scheduleTask = ScheduleTask.cronBuilder()
                .id(id)
                .name(name)
                .expression(expression)
                .action(actionTarget)
                .build();

        scheduleService.register(scheduleTask);
    }
}
