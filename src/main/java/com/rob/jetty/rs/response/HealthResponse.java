package com.rob.jetty.rs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rob.jetty.rs.resource.Health;
import com.rob.jetty.rs.response.entity.HealthEntity;

/**
 * HealthResponse is the representation of a response from the
 * {@link Health} resource.
 *
 * @author Rob Benton
 */
public final class HealthResponse extends AbstractRestfulResponse<HealthEntity>
{
    /**
     * Creates a new instance.
     *
     * @param httpStatus HTTP status code
     * @param entity     {@link HealthEntity}
     */
    public HealthResponse(int httpStatus, HealthEntity entity)
    {
        super(httpStatus, entity);
    }

    /**
     * Gets the details of the health status.
     *
     * @return HealthEntity
     */
    @Override
    @JsonProperty("health")
    public HealthEntity getEntity()
    {
        return entity;
    }
}
