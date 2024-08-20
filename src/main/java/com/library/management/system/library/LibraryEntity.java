package com.library.management.system.library;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;

@Entity
@Getter
@Setter
@Builder
@Table(name="libraries")
public class LibraryEntity {

    @jakarta.persistence.Id
    @GeneratedValue(generator = "IdGen", strategy = GenerationType.SEQUENCE)
    @Column(name="library_id")
    private Long Id;

    @Column(name="library_name")
    private String name;

    @Column(name ="library_contact_information")
    private String contactInformation ;

    @Column(name="library_location")
    private String location ;

}
