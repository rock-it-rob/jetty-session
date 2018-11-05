package com.rob.jetty.session.rs.response;

/**
 * AbstractRestfulResponse is the base class for all JAX-RS response classes.
 * The paramter <code>T</code> is the type of entity the response contains.
 *
 * @author Rob Benton
 */
public abstract class AbstractRestfulResponse<T>
{
    int httpStatus;
    T entity;

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
     * Extending classes are encouraged to use the {@link com.fasterxml.jackson.annotation.JsonProperty}
     * annotation to change the property name.
     *
     * @return T
     */
    public T getEntity()
    {
        return entity;
    }
}
