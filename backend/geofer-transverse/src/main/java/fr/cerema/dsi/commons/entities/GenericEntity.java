package fr.cerema.dsi.commons.entities;

import jakarta.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Set;


@MappedSuperclass
public abstract class GenericEntity implements Serializable {

    protected static Logger logger;

    /**
     * Generic Constructor for the entity
     * <p>For the moment, contains only loggers initialization
     */
    protected GenericEntity() {
        logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
    }

    @Override
    public String toString() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE) {

            @Override
            protected boolean accept(Field field) {

                return !GenericEntity.class.isAssignableFrom(field.getType()) && !Set.class.isAssignableFrom(field.getType());
            }
        };

        return builder.toString();
    }

}
