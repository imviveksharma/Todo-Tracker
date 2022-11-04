package com.niit.TrashService.model;

import lombok.*;
import org.springframework.data.annotation.Id;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class TaskList
{
    @Id
    private long taskId;
    private String taskTitle;
    private String category;
    private String description;
    private String priority;
    private String date;
}
