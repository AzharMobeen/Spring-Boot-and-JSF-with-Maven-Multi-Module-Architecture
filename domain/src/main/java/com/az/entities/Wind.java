package com.az.entities;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@Entity
@XmlAccessorType(XmlAccessType.NONE)
public class Wind
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@XmlElement
    private String gust;

	@XmlElement
    private String direction;

	@XmlElement
    private String name;

	@XmlElement
    private int speedmax;

	@XmlElement
    private int speedmin;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    @JoinColumn(name = "forecastDetail_id")
    private ForecastDetail forecastDetail;

}