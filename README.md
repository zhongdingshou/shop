# Java Web 简单小商城

## 使用

1.创建数据库shop，导入数据库文件（shop.sql）

2.到 `src\www\shop\com\dao\utils` 目录下的 `JdbcUtil.java` 文件修改数据库连接的用户名和密码
```
private static String dbUser = "root"; // 登录sql的用户名
private static String dbPwd = ""; // 登录sql的密码
```
3.用IDE打开项目文件，检查有没有缺少一些库，之后再 `Run on Server`
