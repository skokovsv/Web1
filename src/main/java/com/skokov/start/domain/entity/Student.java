package com.skokov.start.domain.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="student")
public class Student {
    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Заполните ФИО")
    @Length(max=100,message = "Поле не может содержать более 100 символов")
    @Column(name = "name",length = 100)
    private String name;

    @NotBlank(message = "Заполните поле номера группы")
    @Length(max = 100, message = "Поле не может содержать более 100 символов")
    @Column(name = "namegroup",length = 100)
    private String namegroup;

    public Student(){}
    public Student(String name, String namegroup,User studentuser,String avatarname) {
        this.name = name;
        this.namegroup = namegroup;
        this.studentuser=studentuser;
        this.avatarname=avatarname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamegroup() {
        return namegroup;
    }

    public void setNamegroup(String namegroup) {
        this.namegroup = namegroup;
    }
//    @Column(name="groupp")
//    private Integer groupp;

    @Column(name = "avatarname")
    private String avatarname;

//    @ManyToOne(fetch = FetchType.LAZY, optional = true,cascade = {CascadeType.ALL})
//    @JoinColumn(name = "firstname_id")
//    private Firstname firstname;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = true,cascade = {CascadeType.ALL})
//    @JoinColumn(name = "secondname_id")
//    private Secondname secondname;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = true,cascade = {CascadeType.ALL})
//    @JoinColumn(name = "lastname_id")
//    private Lastname lastname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User studentuser;

    public String getStudentuser(){
        return studentuser !=null ? studentuser.getUsername() : "отсутствует";
    }

//    public String getName() {
//        return lastname.getName() +" "+ firstname.getName() + " "+ secondname.getName();
//    }







//    public Firstname getFirstname() {return firstname;}
//    public void setFirstname(Firstname firstname) {this.firstname = firstname;}
//    public Secondname getSecondname() {return secondname;}
//    public void setSecondname(Secondname secondname) {this.secondname = secondname;}
//    public Lastname getLastname() {return lastname;}
//    public void setLastname(Lastname lastname) {this.lastname = lastname;}
    public String getAvatarname() {
        return avatarname;
    }
    public void setAvatarname(String avatarname) {
        this.avatarname = avatarname;
    }
    public void setStudentuser(User studentuser){
        this.studentuser=studentuser;
    }
//    public int getGroupp() {
//        return this.groupp;
//    }
//    public void setGroupp(int groupp) {
//        this.groupp = groupp;
//    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }




}
