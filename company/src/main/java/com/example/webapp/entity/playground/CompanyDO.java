package com.example.webapp.entity.playground;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company", schema = "playground")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;
//    private String employeeLastName;
//    private Long employeeAge;
//    private String employeeAddress;
//    private Long activeInd;
//
//    private Date createDtTm;
//    private Date updtDtTm;

    @Override
    public String toString() {
        return "CompanyDO{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
