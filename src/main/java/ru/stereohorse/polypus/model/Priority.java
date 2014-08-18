package ru.stereohorse.polypus.model;

import javax.persistence.*;

@Entity
@Table(name = "priorities")
public class Priority {
    public static enum Value {
        MEDIUM
    }

    public static final String DEFAULT_VALUE = Value.MEDIUM.name();

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "priorities_seq_gen")
    @SequenceGenerator(name = "priorities_seq_gen", sequenceName = "priorities_id_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
