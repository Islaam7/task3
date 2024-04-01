package com.sci.models;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "regions", schema = "hr")
@Data
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
//@SequenceGenerator(name="locations_gen", sequenceName="hr.locations_seq", allocationSize = 1)
public class Region implements Serializable {

    private static final long serialVersionUID = -915428707036605461L;

    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="locations_gen")
    @Column(name = "region_id")
    private Integer regionId;
    @Column(name = "region_name")
    private String regionName;
}
