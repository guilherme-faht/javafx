package br.com.faht.javafx.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
@Data
@Entity
@EqualsAndHashCode
@Table(name = "tb_demo")
@Accessors(chain = true)
public class Demo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "ds_name")
    private String name;

    @Version
    @Column(name = "nr_versao")
    private Integer version;
}
