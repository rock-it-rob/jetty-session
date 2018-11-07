package com.rob.jetty.session.rs.response.entity;

/**
 * MongoSessionInfoEntity contains the information regarding the configuration
 * of the Mongo session settings in the current embedded Jetty server
 * instance.
 *
 * @author Rob Benton
 */
public final class MongoSessionInfoEntity
{
    private boolean enabled = false;
    private String host;
    private int port;
    private String databaseName;
    private String collectionName;

    /**
     * Default constructor.
     */
    public MongoSessionInfoEntity()
    {
    }

    public MongoSessionInfoEntity enabled(boolean enabled)
    {
        this.enabled = enabled;
        return this;
    }

    public MongoSessionInfoEntity host(String host)
    {
        this.host = host;
        return this;
    }

    public MongoSessionInfoEntity port(int port)
    {
        this.port = port;
        return this;
    }

    public MongoSessionInfoEntity databaseName(String databaseName)
    {
        this.databaseName = databaseName;
        return this;
    }

    public MongoSessionInfoEntity collectionName(String collectionName)
    {
        this.collectionName = collectionName;
        return this;
    }

    public boolean getEnabled()
    {
        return enabled;
    }

    public String getHost()
    {
        return host;
    }

    public int getPort()
    {
        return port;
    }

    public String getDatabaseName()
    {
        return databaseName;
    }

    public String getCollectionName()
    {
        return collectionName;
    }
}
