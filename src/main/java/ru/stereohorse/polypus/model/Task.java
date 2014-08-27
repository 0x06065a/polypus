package ru.stereohorse.polypus.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_seq_gen")
    @SequenceGenerator(name = "tasks_seq_gen", sequenceName = "tasks_id_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "date", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "priority")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "journal")
    @JsonIgnore
    private Journal journal;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Step> steps;

    @Column(name = "is_deleted", insertable = false)
    private Boolean isDeleted;


    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return name;
    }
}
