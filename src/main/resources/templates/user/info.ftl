<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>用户信息</title>
</head>
<body>
头像：<img src="/user/profile/id"></br>
用户名：${user.name!""}</br>
密码：${user.password!""}</br>
注册时间：${user.displayRegisterDateTime!""}</br>

上传头像：
<form method="post" action="/user/profile/upload" enctype="multipart/form-data">
    <input type="hidden" name="userId" value="${user.id}">
    <input type="text" name="title" value="">
    <input type="file" name="profile">
    <input type="submit" name="提交">
</form>
</body>
</html>