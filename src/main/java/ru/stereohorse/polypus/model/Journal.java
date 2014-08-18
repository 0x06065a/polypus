package ru.stereohorse.polypus.model;

import javax.persistence.*;
import java.util.List;

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


    public Journal() {
    }

    public Journal(Journal journal) {
        this.id = journal.id;
        this.name = journal.name;
    }


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
}
