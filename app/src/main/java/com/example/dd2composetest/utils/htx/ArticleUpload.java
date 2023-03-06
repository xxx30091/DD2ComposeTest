package com.example.dd2composetest.utils.htx;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;

@XStreamAlias("article")
public class ArticleUpload {
    @XStreamImplicit
    public ArrayList list;
}
