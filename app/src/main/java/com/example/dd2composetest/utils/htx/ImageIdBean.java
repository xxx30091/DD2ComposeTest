package com.example.dd2composetest.utils.htx;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("image")
public class ImageIdBean {
    @XStreamAsAttribute
    public String id;
}
