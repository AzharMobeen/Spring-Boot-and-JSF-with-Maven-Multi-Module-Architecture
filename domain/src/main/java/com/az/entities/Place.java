package com.az.entities;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@Entity
@XmlAccessorType(XmlAccessType.NONE)
public class Place {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@XmlElement
	private String phenomenon;

	@XmlElement
	private int tempmin;

	@XmlElement
	private String name;

	@ManyToOne
	@JoinColumn(name = "forecastDetail_id")
	private ForecastDetail forecastDetail;		

}
