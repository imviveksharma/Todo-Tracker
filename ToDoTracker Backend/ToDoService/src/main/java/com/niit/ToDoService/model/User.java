package com.niit.ToDoService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class User {

    private String name;
    @Id
    private String email;
    private String password;
    private long phoneNo;
    private List<TempTaskList> list;
    private List<ArchiveList> archive;
}
