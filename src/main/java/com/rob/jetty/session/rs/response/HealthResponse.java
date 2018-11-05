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
    public HealthResponse()
    {
    }

    /**
     * Sets the http status and return this.
     *
     * @param status HTTP status
     * @return this
     */
    public HealthResponse setStatus(int status)
    {
        this.httpStatus = status;
        return this;
    }

    /**
     * Sets the entity and returns this.
     *
     * @param entity {@link HealthEntity}
     * @return this
     */
    public HealthResponse setEntity(HealthEntity entity)
    {
        this.entity = entity;
        return this;
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
