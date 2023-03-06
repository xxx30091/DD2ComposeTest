package com.example.dd2composetest.utils.htx;

import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamConverter(value = ToAttributedValueConverter.class,strings = {"text"})
//@XStreamAlias(HTXService.textStreamAlias)
public class TextRichBean {
    public String text;//text,imageurl,viderurl
}
