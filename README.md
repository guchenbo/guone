# GUONE CMS 后台管理系统

一个简洁易用的后台管理系统示例，提供内容管理、用户管理和基础权限控制功能，基于 Java Spring Boot + 前端框架（可自定义）实现。

---

## 功能特性

* 用户管理（增删改查、角色权限）
* 内容管理（文章、分类管理）
* 登录鉴权与会话管理
* 简单数据统计和仪表盘
* RESTful API 支持前端调用
* 可扩展的模块化设计，便于二次开发

---

## 技术栈

* **后端**: Java, Spring Boot, Spring Security, MyBatis / JPA
* **数据库**: MySQL
* **前端**: Vue / React / Thymeleaf (根据实际情况调整)
* **构建工具**: Maven / Gradle
* **其他**: Lombok, JWT (可选), Swagger (API 文档)

---

## 项目结构

```maven
guone/
├─ core         # core
├─ biz          # 业务
├─ web          # web
├─ sample       # 示例

``` 

---

## 快速开始

### 1. 克隆项目

```bash
git clone <项目地址>
cd guone
```

### 2. 配置数据库

* 修改 `application.yml` 或 `application.properties` 中的数据库配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/cms_db
    username: root
    password: password
```

* 初始化数据库，可使用 `schema.sql` 或项目自带的 Flyway / Liquibase 脚本

### 3. 构建项目

```bash
mvn clean install
```

### 4. 启动应用

```bash
mvn spring-boot:run
```

* 默认访问地址: `http://localhost:8080`
* API 文档: `http://localhost:8080/swagger-ui.html` （如果集成 Swagger）

---

## 使用说明

1. 登录系统
2. 添加用户与角色
3. 管理内容（文章、分类）
4. 查看统计仪表盘

> 可根据实际业务扩展模块和功能

---

## 贡献

欢迎提交 issues 和 pull requests，帮助完善系统功能和文档。

---

## 开源协议

MIT License

---

如果你愿意，我可以帮你再写一个 **更丰富的 README 版本**，带 **数据库初始化示例、API 示例、前端访问截图**，让项目开箱即用，非常适合开源或者团队交接。

你希望我写吗？
