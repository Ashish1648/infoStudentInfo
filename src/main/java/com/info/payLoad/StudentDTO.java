package com.info.payLoad;

import lombok.Data;

@Data
public class StudentDTO {

    private Long id;
    private String name;
    private String course;
    private double fee;
    private IdProofDTO idProof;

    // constructors, getters and setters
}