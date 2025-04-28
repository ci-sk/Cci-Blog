# Cci-Blog 博客系统

## 项目概述

Cci-Blog 是一个前后端分离的博客系统，包含三个主要模块：

- **后端服务(blog-backend)**：基于 Spring Boot 的 RESTful API 服务
- **管理员前端(blog-frontend-admin)**：博客管理系统，用于内容管理
- **用户前端(blog-frontend-blog)**：博客展示系统，面向普通用户

## 技术栈

### 后端技术栈

- **核心框架**：Spring Boot 3.3.3
- **安全框架**：Spring Security + JWT 认证
- **数据库**：MySQL
- **缓存**：Redis
- **ORM 框架**：MyBatis-Plus
- **文件存储**：七牛云、阿里云 OSS
- **JDK 版本**：Java 17

### 管理员前端技术栈

- **核心框架**：Vue 3.4 + Vite 5.4
- **状态管理**：Pinia
- **UI 组件库**：Element Plus
- **HTTP 客户端**：Axios
- **路由**：Vue Router 4.4
- **富文本编辑器**：md-editor-v3
- **数据可视化**：ECharts

### 用户前端技术栈

- **核心框架**：Vue 3.4 + Vite 5.4
- **状态管理**：Zustand
- **UI 框架**：Tailwind CSS + DaisyUI
- **HTTP 客户端**：Axios
- **路由**：Vue Router 4.5
- **Markdown 渲染**：marked + highlight.js
- **数据可视化**：ECharts

## 项目结构

```
Cci-Blog-master/
├── blog-backend/               # 后端服务
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/org/example/
│   │   │   │   ├── config/      # 配置类
│   │   │   │   ├── controller/  # 控制器
│   │   │   │   ├── entity/      # 实体类
│   │   │   │   ├── filter/      # 过滤器
│   │   │   │   ├── mapper/      # MyBatis映射接口
│   │   │   │   ├── service/     # 服务接口及实现
│   │   │   │   └── utils/       # 工具类
│   │   │   └── resources/
│   │   │       ├── mapper/      # MyBatis XML映射文件
│   │   │       └── application.yaml  # 应用配置文件
│   │   └── test/                # 测试代码
│   └── pom.xml                  # Maven配置文件
├── blog-frontend-admin/         # 管理员前端
│   ├── src/
│   │   ├── assets/              # 静态资源
│   │   ├── components/          # 组件
│   │   ├── router/              # 路由配置
│   │   ├── store/               # 状态管理
│   │   ├── views/               # 页面视图
│   │   └── main.js              # 入口文件
│   ├── package.json             # 依赖配置
│   └── vite.config.js           # Vite配置
└── blog-frontend-blog/          # 用户前端
    ├── src/
    │   ├── assets/              # 静态资源
    │   ├── components/          # 组件
    │   ├── router/              # 路由配置
    │   ├── store/               # 状态管理
    │   ├── views/               # 页面视图
    │   └── main.js              # 入口文件
    ├── package.json             # 依赖配置
    └── vite.config.js           # Vite配置
```

## 功能模块

### 后端功能模块

- **用户管理**：注册、登录、权限控制
- **文章管理**：发布、编辑、删除文章
- **分类管理**：文章分类的 CRUD 操作
- **标签管理**：文章标签的 CRUD 操作
- **评论管理**：评论的发布、审核、回复
- **友链管理**：友情链接的 CRUD 操作
- **留言管理**：用户留言的处理
- **文件管理**：支持七牛云和阿里云 OSS 的文件上传
- **系统设置**：博客基本信息配置
- **流量控制**：请求限流
- **日志记录**：请求日志记录

### 管理员前端功能
- **仪表盘**：数据统计和可视化
- **文章管理**：Markdown 编辑器支持
- **用户管理**：用户信息管理
- **评论管理**：评论审核和回复
- **系统设置**：网站基本信息配置

### 用户前端功能
- **文章浏览**：按分类、标签浏览文章
- **文章搜索**：搜索文章内容
- **评论互动**：发表评论、回复
- **用户留言**：向博主留言
- **响应式设计**：适配不同设备

## 安装部署

### 后端部署

1. 确保已安装 JDK 17 和 Maven
2. 配置 MySQL 数据库，创建名为`bolg_db`的数据库
3. 配置 Redis 服务
4. 修改`application.yaml`中的数据库连接信息和其他配置
5. 在 blog-backend 目录下执行：

```bash
mvn clean package
java -jar target/my-project-backend-0.0.1-SNAPSHOT.jar
```

### 前端部署

#### 管理员前端

1. 确保已安装 Node.js
2. 在 blog-frontend-admin 目录下执行：

```bash
npm install
npm run dev  # 开发环境
npm run build  # 生产环境构建
```

#### 用户前端

1. 确保已安装 Node.js
2. 在 blog-frontend-blog 目录下执行：

```bash
npm install
npm run dev  # 开发环境
npm run build  # 生产环境构建
```

## 配置说明

### 文件存储配置

项目支持两种文件存储方式：七牛云和阿里云 OSS，可在`application.yaml`中配置：

```yaml
# 七牛云配置
qiniu:
  accessKey: 你的accessKey
  secretKey: 你的secretKey
  bucket: 存储空间名称
  directory: 上传目录
  domain: 访问域名

# 阿里云OSS配置
aliyun:
  oss:
    endpoint: OSS端点
    accessKeyId: 你的accessKeyId
    accessKeySecret: 你的accessKeySecret
    bucketName: 存储空间名称
```

### 邮件服务配置

```yaml
mail:
  host: smtp服务器地址
  username: 邮箱账号
  password: 邮箱密码
```

## 安全注意事项

- 部署到生产环境前，请修改 JWT 密钥
- 更新所有 API 密钥和敏感信息
- 配置适当的 CORS 策略
- 启用 HTTPS

## 许可证

[MIT License]()
