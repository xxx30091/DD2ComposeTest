package com.example.dd2composetest.utils.htx;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("video")
public class VideoBean {
    @XStreamAsAttribute
    public int index;
}
