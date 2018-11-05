package com.rob.jetty.session.rs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rob.jetty.session.rs.response.entity.HealthEntity;

/**
 * HealthResponse is the representation of a response from the
 * {@link com.rob.jetty.session.rs.Health} resource.
 *
 * @author Rob Benton
 */
public final class HealthResponse extends AbstractRestfulResponse<HealthEntity>
{
    HealthResponse(int httpStatus, HealthEntity entity)
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
