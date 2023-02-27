package com.cts.crud.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name ="StudentProcedure1",procedureName ="getStudents" ), @NamedStoredProcedureQuery(name="StudentProcedure2",procedureName ="getStudentsByName",parameters = {@StoredProcedureParameter(mode =ParameterMode.IN, name ="names", type = String.class)}), @NamedStoredProcedureQuery(name = "StudentProcedure3", procedureName = "insertStudents", parameters ={@StoredProcedureParameter(mode =ParameterMode.IN, name="ids1",type = Integer.class), @StoredProcedureParameter(mode =ParameterMode.IN, name="names1",type = String.class), @StoredProcedureParameter(mode =ParameterMode.IN, name="mobiles",type = Long.class), @StoredProcedureParameter(mode =ParameterMode.IN, name="emails",type = String.class)}), @NamedStoredProcedureQuery(name = "StudentProcedure4", procedureName = "updateStudents", parameters ={@StoredProcedureParameter(mode =ParameterMode.IN, name="ids2",type = Integer.class), @StoredProcedureParameter(mode =ParameterMode.IN, name="names2",type = String.class), @StoredProcedureParameter(mode =ParameterMode.IN, name="mobiles1",type = Long.class), @StoredProcedureParameter(mode =ParameterMode.IN, name="emails1",type = String.class)}), @NamedStoredProcedureQuery(name = "StudentProcedure5", procedureName = "deleteStudents", parameters ={@StoredProcedureParameter(mode =ParameterMode.IN, name="ids",type = Integer.class)})})
public class Student {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private long mobile;
    private String email;
}
