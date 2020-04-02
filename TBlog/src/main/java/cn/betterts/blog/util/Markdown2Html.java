package cn.betterts.blog.util;

import com.youbenzi.mdtool.tool.MDTool;

public class Markdown2Html {
    public static String M2H(String markdownString){
       return MDTool.markdown2Html(markdownString);
    }
}
