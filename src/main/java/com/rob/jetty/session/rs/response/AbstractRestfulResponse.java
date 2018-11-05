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
     * Creates a new instance with the HTTP response and entity object.
     *
     * @param httpStatus int HTTP status code of the response
     * @param entity     object to embed in this response
     */
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
     * Extending classes are encouraged to use the {@link com.fasterxml.jackson.annotation.JsonProperty}
     * annotation to change the property name.
     *
     * @return T
     */
    public T getEntity()
    {
        return entity;
    }

    public abstract Builder<? extends AbstractRestfulResponse, T> newBuilder();


    /**
     * Builder is a helper class to create the required subclass of
     * {@link AbstractRestfulResponse}.
     * @param <R> the response subclass to build.
     * @param <T> the entity for the response.
     */
    static final class Builder<R extends AbstractRestfulResponse, T>
    {
        Integer httpStatus;
        T entity;

        /**
         * Sets the status to the given value.
         *
         * @param httpStatus int HTTP status code
         * @return this
         */
        public Builder status(int httpStatus)
        {
            this.httpStatus = httpStatus;
            return this;
        }

        /**
         * Sets the entity.
         *
         * @param entity T
         * @return this
         */
        public Builder entity(T entity)
        {
            this.entity = entity;
            return this;
        }

        /**
         * Subclasses of {@link AbstractRestfulResponse} should override this
         * method to return the exact type of instance needed.
         *
         * @return AbstractRestfulResponse<T>
         */
        public abstract AbstractRestfulResponse<T> build();
    }
}
