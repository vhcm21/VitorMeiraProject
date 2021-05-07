package com.school.mindera.vitor.meira.persistence.entity;

import com.school.mindera.vitor.meira.enumerators.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "samurais")
public class SamuraiEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private long birthYear;

    @Column(nullable = false)
    private long deathYear;

    @Column(nullable = false)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clanId", nullable = false)
    private ClanEntity clan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceId", nullable = false)
    private ProvinceEntity province;
}