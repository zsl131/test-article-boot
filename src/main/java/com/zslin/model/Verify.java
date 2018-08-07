package com.zslin.model;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name="test_verify")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Verify {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String approval;
    private String time;
    private String result;
    @Column(name = "leave_id")
    private Integer leaveId;

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
