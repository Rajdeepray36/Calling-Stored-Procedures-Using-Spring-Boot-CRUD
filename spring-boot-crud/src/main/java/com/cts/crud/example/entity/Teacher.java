package com.cts.crud.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "TeacherProcedure1", procedureName = "getTeachers"),
        @NamedStoredProcedureQuery(name = "TeacherProcedure2", procedureName = "getTeachersByName", parameters =
                {@StoredProcedureParameter(mode = ParameterMode.IN, name = "names", type = String.class)}),
        @NamedStoredProcedureQuery(name = "TeacherProcedure3", procedureName = "insertTeachers", parameters =
                {@StoredProcedureParameter(mode = ParameterMode.IN, name = "ids1", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "names1", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "mobiles", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "emails", type = String.class)}),
        @NamedStoredProcedureQuery(name = "TeacherProcedure4", procedureName = "updateTeachers", parameters =
                {@StoredProcedureParameter(mode = ParameterMode.IN, name = "ids2", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "names2", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "mobiles1", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "emails1", type = String.class)}),
        @NamedStoredProcedureQuery(name = "TeacherProcedure5", procedureName = "deleteTeachers", parameters =
                {@StoredProcedureParameter(mode = ParameterMode.IN, name = "ids", type = Integer.class)}),
        @NamedStoredProcedureQuery(name = "TeacherProcedure6", procedureName = "insertmulTeachers", parameters =
                {@StoredProcedureParameter(mode = ParameterMode.IN, name = "ids3", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "names3", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "mobiles2", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "emails2", type = String.class)})})

public class Teacher {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Column(unique = true)
    @Digits(integer = 10, fraction = 0)
    private long mobile;
    @Column(unique = true)
    @SuppressWarnings("deprecation")
    @Email
    private String email;
}
