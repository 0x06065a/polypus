package ru.stereohorse.polypus.model;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {
    public static enum Value {
        OPEN, CLOSED
    }

    public static final String DEFAULT_VALUE = Value.OPEN.name();

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statuses_seq_gen")
    @SequenceGenerator(name = "statuses_seq_gen", sequenceName = "statuses_id_seq")
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

    public void setValue(Value val) {
        this.value = val.name();
    }
}
