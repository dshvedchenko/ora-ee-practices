package org.demo.models;

import lombok.Data;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.Set;

/**
 * @author dshvedchenko on 5/24/16.
 */
@Data
@Entity
@Table(name = "AUDIOS")
public class Audio {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column (name = "TITLE")
    private String title;

    @Column (name = "YEAR")
    private Integer year;

    @Column (name = "DURATION")
    private Integer duration;

    @ManyToOne()
    @JoinColumn( name = "GANRE_ID")
    private Ganre ganre;


    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn( name = "GROUP_ID")
    private Group group;

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
            @JoinTable (name ="AUTHORS_AUDIOS"
                    , joinColumns = @JoinColumn (name = "AUDIO_ID")
                    , inverseJoinColumns = @JoinColumn (name = "AUTHOR_ID"), foreignKey = @ForeignKey (name="AUDIOS_FK"), inverseForeignKey = @ForeignKey (name = "AUTHORS_FK"))
    Set<Author> authors;
}
