package com.example.lab.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="operation")
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`real`")
    private Double real;

    @Column(name = "`image`")

    private Double image;
    @Column(name = "`phase`")

    private Double phase;

    @Column(name = "`module`")
    private Double module;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getReal() {
        return real;
    }

    public void setReal(Double real) {
        this.real = real;
    }

    public Double getImage() {
        return image;
    }

    public void setImage(Double image) {
        this.image = image;
    }

    public Double getPhase() {
        return phase;
    }

    public void setPhase(Double phase) {
        this.phase = phase;
    }

    public Double getModule() {
        return module;
    }

    public void setModule(Double module) {
        this.module = module;
    }
}
