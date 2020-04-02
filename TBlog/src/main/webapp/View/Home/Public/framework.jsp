<%--
  Created by IntelliJ IDEA.
  User: BetterTs
  To change this template use File | Settings | File Templates.
  用于其他页面继承
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html  lang="zh-CN">
<head>
<%--    设置编码方式--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--    指定加载模式--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<%--    最大加载时间--%>
    <meta http-equiv="Cache-Control" content="max-age=72000"/>
<%--    设置不同设备的布局--%>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<%--    指定设备--%>
    <meta name="applicable-device" content="pc,mobile">
<%--    手机上的宽带--%>
    <meta name="MobileOptimized" content="width"/>
    <meta name="HandheldFriendly" content="true"/>
<%--    logo--%>
    <link rel="shortcut icon" href="/img/logo.png">
<%--    网页描述--%>
    <rapid:block name="description">
        <meta name="description" content="${options.optionMetaDescrption}"/>
    </rapid:block>
    <rapid:block name="keywords">
        <meta name="keywords" content="${options.optionMetaKeyword}"/>
    </rapid:block>
    <rapid:block name="title">
        <title>
                ${options.optionSiteTitle}-${options.optionSiteDescrption}
        </title>
    </rapid:block>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">
    <rapid:block name="header-style">

    </rapid:block>
</head>
<body>
<div id="page" class="site" style="transform: none;">

    <%@ include file="part/header.jsp" %>
    <div id="content" class="site-content" style="transform: none;">
        <rapid:block name="left"></rapid:block>
        <rapid:block name="right">
            <%@ include file="part/sidebar-1.jsp" %>
        </rapid:block>
    </div>
    <div class="clear"></div>
    <rapid:block name="link"></rapid:block>
    <%@ include file="part/footer.jsp" %>

</div>
<%--加载资源--%>
<script type="text/javascript" src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>　　
<script src="/js/jquery.min.js"></script>
<script src="/js/superfish.js"></script>
<script src='/js/sticky.js'></script>
<script src="/js/script.js"></script>
<script src="/plugin/layui/layui.all.js"></script>



<rapid:block name="footer-script"></rapid:block>

</body>
</html>
