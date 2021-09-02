package com.vnpt.eoffice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class VanBan {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String ten;
    private String ma;

    @ManyToOne
    @JoinColumn(name = "loai_van_ban_id")
    private LoaiVanBan loaiVanBan ;

}
