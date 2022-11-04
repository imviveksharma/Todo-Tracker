package com.niit.AdminService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    @Id
    private long taskId;
    private String taskTitle;
}
