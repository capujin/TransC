# TransC

TransC 是一个基于 Vue 3 和 Spring Boot 开发的翻译后台管理系统，旨在帮助用户管理和处理翻译任务。

## 目录

- [TransC](#transc)
  - [目录](#目录)
  - [项目简介](#项目简介)
  - [技术栈](#技术栈)
  - [功能特性](#功能特性)
  - [安装与使用](#安装与使用)
    - [前端项目](#前端项目)
    - [后端项目](#后端项目)
  - [项目结构](#项目结构)
  - [贡献](#贡献)
  - [许可证](#许可证)

## 项目简介

TransC 是一个支持多语言翻译任务管理的系统，提供了高效的工作流程管理、任务分配和翻译内容管理功能。该系统基于 Vue 3 和 Spring Boot 构建，前端使用 Vite 作为构建工具，后端基于 Spring Boot 提供 RESTful API 服务。

## 技术栈

- **前端**:
  - [Vue 3](https://vuejs.org/)
  - [Vite](https://vitejs.dev/)
  - [Element Plus](https://element-plus.org/)
  - [Vue Router](https://router.vuejs.org/)
  - [Vuex](https://vuex.vuejs.org/)

- **后端**:
  - [Spring Boot](https://spring.io/projects/spring-boot)
  - [MyBatis](https://mybatis.org/mybatis-3/)
  - [Spring Security](https://spring.io/projects/spring-security)
  - [JWT](https://jwt.io/)
  
- **数据库**:
  - MySQL

## 功能特性

- 用户身份验证和权限管理
- 翻译任务的创建、分配和跟踪
- 支持多语言翻译管理
- 实时通知和消息系统
- 支持任务状态的自动更新
- 灵活的用户角色和权限配置

## 安装与使用

### 前端项目

1. 克隆仓库：

    ```sh
    git clone https://github.com/your-username/transc.git
    cd transc/frontend
    ```

2. 安装依赖：

    ```sh
    npm install
    ```

3. 运行开发服务器：

    ```sh
    npm run dev
    ```

4. 构建生产版本：

    ```sh
    npm run build
    ```

5. 使用 ESLint 检查代码：

    ```sh
    npm run lint
    ```

### 后端项目

1. 克隆仓库：

    ```sh
    git clone https://github.com/your-username/transc.git
    cd transc/backend
    ```

2. 导入 Maven 依赖：

    ```sh
    mvn clean install
    ```

3. 配置数据库连接：

    在 `application.properties` 或 `application.yml` 文件中配置数据库连接信息。

4. 运行后端服务：

    ```sh
    mvn spring-boot:run
    ```

## 项目结构

```plaintext
transc/
│
├── backend/                # 后端项目文件
│   ├── src/
│   ├── pom.xml             # Maven 配置文件
│   └── ...                 # 其他后端相关文件
│
├── frontend/               # 前端项目文件
│   ├── src/
│   ├── public/
│   ├── index.html
│   ├── vite.config.ts      # Vite 配置文件
│   ├── package.json        # Node.js 项目配置文件
│   └── ...                 # 其他前端相关文件
│
└── README.md               # 项目说明文件

Git Commit 提交消息规范
feat：新增功能；
fix：修复bug；
docs：修改文档；
refactor：代码重构，未新增任何功能和修复任何bug；
build：改变构建流程，新增依赖库、工具等（例如webpack修改）；
style：仅仅修改了空格、缩进等，不改变代码逻辑；
perf：改善性能和体现的修改；
chore：非src和test的修改；
test：测试用例的修改；
ci：自动化流程配置修改；
revert：回滚到上一个版本；