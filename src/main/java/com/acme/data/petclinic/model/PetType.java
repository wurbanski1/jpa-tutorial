package com.acme.data.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "types")
public class PetType extends NamedEntity {

}
