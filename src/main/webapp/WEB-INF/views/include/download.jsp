<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>


<!-- 下载按钮 -->
<div class="row">
    <%--<shiro:hasPermission name="/app/appIndex/downloadPageData/212">--%>
    <%--<button id="download" class="btn btn-primary" >导出本页</button>--%>
    <%--</shiro:hasPermission> --%>
    <%--<shiro:hasPermission name="/app/appIndex/downloadPageDataAll/213">--%>
    <%--<button id="downloadAll" class="btn btn-primary" >导出全部</button>--%>
    <%--</shiro:hasPermission>--%>
    <div style="display: flex; min-width: 1000px;margin-top: 30px;background-color: white;">
        <div style="flex: 1;padding-left: 25px;line-height: 45px;vertical-align: middle;">
            <span>数据详情</span>
        </div>
        <div style="flex: 0 100px;">
            <button id="downloadAll" class="btn btn-xs main-color-bluegreen txt-color-white" style="width: 60px;margin: 12px;height: 22px; font-size: 14px;">下载
            </button>
        </div>
    </div>
</div>

<script type="text/javascript">


    /**
     *
     * @param moduleId  用于记录日志
     * 导出文件通用方法
     *
     */
    function download(moduleId) {
        $.ajax({
            url: "${contextPath}/app/appIndex/downloadPageData/" + moduleId,
            type: "POST",
            data: getMyCondition(),
            success: function (response) {
                window.location.href = "${contextPath}/" + response;
            }
        });
    }

    function downloadAll(moduleId) {
        $.ajax({
            url: "${contextPath}/app/appIndex/downloadPageDataAll/" + moduleId,
            type: "POST",
            data: getMyCondition(),
            success: function (response) {
                window.location.href = "${contextPath}/" + response;
            }
        });
    }
</script>