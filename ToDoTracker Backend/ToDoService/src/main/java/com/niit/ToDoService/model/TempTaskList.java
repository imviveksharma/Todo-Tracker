package com.niit.ToDoService.model;

import lombok.*;
import org.springframework.data.annotation.Id;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class TempTaskList {
    @Id
    private long taskId;
    private String taskTitle;
    private String category;
    private String description;
    private String priority;
    private String date;
}
