package com.example.dd2composetest.utils.htx;

import android.text.TextUtils;

import com.thoughtworks.xstream.XStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HTXService {
    public static final String textStreamAlias = "<deve.pub.htx.bean.TextRichBean>";
    public static final String textStreamAliasEnd = "</deve.pub.htx.bean.TextRichBean>";

    public String toXML(String html, ArrayList<File> files, ArrayList<Integer> videos) {
        html = html.replaceAll("<div>", "").replaceAll("</div>", "").replaceAll("<a>", "").replaceAll("</a>", "");
        try {
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select("body");
            int videoIndex = 0;
            int imageIndex = 0;
            ArrayList datas = new ArrayList();
            boolean hasVideoTag = false;
            for (Element element : elements) {
                List<Node> childNodes = element.childNodes();
                for (Node node : childNodes) {
                    if (node instanceof TextNode) {
                        System.out.println(node.toString());
                        TextRichBean edit = new TextRichBean();
                        edit.text = node.toString().replace("\n", "");
                        datas.add(edit);
                    } else if (node instanceof Element) {
                        Element childE = (Element) node;
                        if ("img".equals(childE.tagName())) {
//                            System.out.println("image url=" + childE.attr("src"));
                            if (childE.attributes().hasKey("id")) {
                                // 編輯文章時才會出現有id的圖片，代表資料庫已有該圖片，不需再上傳
                                ImageIdBean image = new ImageIdBean();
                                image.id = childE.attributes().get("id");
                                datas.add(image);
                            } else {
                                ImageBean image = new ImageBean();
                                image.index = imageIndex++;
                                datas.add(image);
                                files.add(new File(childE.attributes().get("src")));
                            }
                        } else if ("video".equals(childE.tagName())) {
//                            System.out.println("video url=" + childE.attr("src"));
                            VideoBean video = new VideoBean();
                            video.index = videoIndex++;
                            datas.add(video);
                            hasVideoTag = true;
                        }

                    }
                }

            }
            if (!hasVideoTag) videos.clear();
            ArticleUpload articleBean = new ArticleUpload();
            articleBean.list = datas;
            XStream xstream = new XStream();
            xstream.autodetectAnnotations(true);//开始xstream的注解扫描
            String xml = xstream.toXML(articleBean);
            if (!TextUtils.isEmpty(xml) && xml.contains(textStreamAlias)) {
                xml = xml.replaceAll(textStreamAlias, "").replaceAll(textStreamAliasEnd, "");
            }
            return xml;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}



