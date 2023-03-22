package com.skokov.start.domain.entity;


import javax.persistence.*;

@Entity
@Table(name="student")
public class Student {
    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name",length = 100)
    private String name;

    @Column(name="groupp")
    private Integer groupp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User studentuser;

    public String getStudentuser(){
        return studentuser !=null ? studentuser.getUsername() : "отсутствует";
    }


    public void setStudentuser(User studentuser){
        this.studentuser=studentuser;
    }

    public Student(String name,int groupp,User studentuser) {
        this.name = name;
        this.groupp=groupp;
        this.studentuser=studentuser;
    }

    public Student(){
        name="безымянный";
    }

    public int getGroupp() {
        return this.groupp;
    }

    public void setGroupp(int groupp) {
        this.groupp = groupp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
