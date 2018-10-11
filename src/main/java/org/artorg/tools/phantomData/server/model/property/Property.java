package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER, name = "CLASS_TYPE", columnDefinition = "TINYINT(1)")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "itemClass")
@JsonSubTypes({ @Type(value = BooleanProperty.class, name = "boolean"),
		@Type(value = DateProperty.class, name = "date"), @Type(value = DoubleProperty.class, name = "double"),
		@Type(value = IntegerProperty.class, name = "integer"), @Type(value = StringProperty.class, name = "string") })

//@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class Property<PROPERTY extends Property<PROPERTY, VALUE>, VALUE extends Comparable<VALUE>>
		implements DbPersistentUUID<PROPERTY>, Serializable {
	private static final long serialVersionUID = -6436598935465710135L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();

	@OneToOne
	private PropertyField propertyField;

	@Column(name = "VALUE", nullable = false)
	private VALUE value;

	public abstract String toString(VALUE value);

	public abstract VALUE fromStringToValue(String s);

	public Property() {
	}

	public Property(PropertyField propertyField, VALUE value) {
		this.propertyField = propertyField;
		this.value = value;
	}

//	@Override
//	protected String createName() {
//		return propertyField.getName() +": " +toString(value);
//	}

	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}

	public VALUE getValue() {
		return value;
	}

	public void setValue(VALUE value) {
		this.value = value;
	}

	public int compareTo(PROPERTY that) {
		int i = this.getPropertyField().compareTo(that.getPropertyField());
		if (i != 0)
			return i;
		i = this.getValue().compareTo(that.getValue());
		return i;
	}

	@Override
	public String toString() {
		return String.format("[propertyField: %s, value: %s, id: %s]", getPropertyField().toString(),
				getValue().toString(), getId().toString());
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(UUID id) {
		this.id = id;
	}

}
