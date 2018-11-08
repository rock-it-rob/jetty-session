package com.rob.jetty.session;

import java.util.HashMap;
import java.util.Map;

/**
 * SessionContents is a POJO for holding the contents of an HttpSession.
 *
 * @author Rob Benton
 */
public final class SessionContents
{
    private HashMap<String, Object> attributes;
    private Object contents;

    public SessionContents()
    {
    }

    public SessionContents(Map<String, Object> attributes, Object contents)
    {
        this.attributes = new HashMap<>(attributes);
        this.contents = contents;
    }

    public Map<String, Object> getAttributes()
    {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes)
    {
        this.attributes = new HashMap<>(attributes);
    }

    public Object getContents()
    {
        return contents;
    }

    public void setContents(Object contents)
    {
        this.contents = contents;
    }
}
