package ru.stereohorse.polypus.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "journals")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journals_seq_gen")
    @SequenceGenerator(name = "journals_seq_gen", sequenceName = "journals_id_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Transient
    private Set<Task> tasks;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = new LinkedHashSet<Task>(tasks);
    }

    @Override
    public String toString() {
        return name;
    }
}
