<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--                                    CHỌN TRANG--%>
<template id="tpl-pagination-item">
    <li class="pagination-item">
        <a href="javascript:void(0)" class="pagination-item__link js-page-number"></a>
    </li>
</template>

<template id="tpl-pagination-dots">
    <li class="pagination-item">
        <span class="pagination-item__link">...</span>
    </li>
</template>

<template id="tpl-pagination-arrow">
    <li class="pagination-item">
        <a href="javascript:void(0)" class="pagination-item__link js-page-arrow">
            <i class="fa-solid"></i>
        </a>
    </li>
</template>
<%--                                    CHỌN TRANG--%>
</body>
</html>
