package com.rob.jetty.session.rs.response.entity;

/**
 * HealthEntity is a POJO containing the properties about the health of the
 * application.
 *
 * @author Rob Benton
 */
public final class HealthEntity
{
    private Boolean up;

    public HealthEntity()
    {
    }

    public HealthEntity(Boolean up)
    {
        this.up = up;
    }

    public Boolean getUp()
    {
        return up;
    }

    public void setUp(Boolean up)
    {
        this.up = up;
    }
}
