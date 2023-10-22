package com.info.service.impl;

import com.info.entity.IdProof;
import com.info.entity.Student;
import com.info.payLoad.IdProofDTO;
import com.info.payLoad.StudentDTO;
import com.info.repositry.IdProofRepository;
import com.info.repositry.StudentRepository;
import com.info.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private IdProofRepository idProofRepository;

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        // Map DTO to entity
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setCourse(studentDTO.getCourse());
        student.setFee(studentDTO.getFee());

        // Save IdProof entity first
        IdProof idProof = new IdProof();
        idProof.setId(studentDTO.getIdProof().getId());
        idProof.setPanCardNumber(studentDTO.getIdProof().getPanCardNumber());
        idProof = idProofRepository.save(idProof);

        // Set IdProof to Student entity
        student.setIdProof(idProof);

        // Save Student entity
        student = studentRepository.save(student);

        // Map entity back to DTO and return
        StudentDTO savedStudentDTO = new StudentDTO();
        savedStudentDTO.setId(student.getId());
        savedStudentDTO.setName(student.getName());
        savedStudentDTO.setCourse(student.getCourse());
        savedStudentDTO.setFee(student.getFee());

        IdProofDTO savedIdProofDTO = new IdProofDTO();
        savedIdProofDTO.setId(student.getIdProof().getId());
        savedIdProofDTO.setPanCardNumber(student.getIdProof().getPanCardNumber());
        savedStudentDTO.setIdProof(savedIdProofDTO);

        return savedStudentDTO;
    }
}