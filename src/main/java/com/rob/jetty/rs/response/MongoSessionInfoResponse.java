package com.rob.jetty.rs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rob.jetty.rs.response.entity.MongoSessionInfoEntity;

/**
 * @author Rob Benton
 */
public class MongoSessionInfoResponse extends AbstractRestfulResponse<MongoSessionInfoEntity>
{
    public MongoSessionInfoResponse(int httpStatus, MongoSessionInfoEntity entity)
    {
        super(httpStatus, entity);
    }

    @Override
    @JsonProperty("mongoSessionInfo")
    public MongoSessionInfoEntity getEntity()
    {
        return this.entity;
    }
}
