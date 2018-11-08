package com.rob.jetty.rs.response;

/**
 * AbstractRestfulResponse is the base class for all JAX-RS response classes.
 *
 * @param <T> the entity type contained in the response.
 * @author Rob Benton
 */
public abstract class AbstractRestfulResponse<T>
{
    protected final int httpStatus;
    protected final T entity;

    AbstractRestfulResponse(int httpStatus, T entity)
    {
        this.httpStatus = httpStatus;
        this.entity = entity;
    }

    /**
     * Gets the http status code of the response.
     *
     * @return int indicating the HTTP status
     */
    public int getHttpStatus()
    {
        return httpStatus;
    }

    /**
     * Gets the entity embedded in this reponse.
     * <p>
     * Extending classes should use the {@link com.fasterxml.jackson.annotation.JsonProperty}
     * annotation to change the property name.
     *
     * @return T
     */
    public abstract T getEntity();
}
