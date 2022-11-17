# 校车自动订座系统的设计与实现v1（施工中）

>项目目前仍在进行中，以下内容仅供参考

## 一、题目要求

类似电影院的订座系统，设计一定的取消功能和预订功能，设计优化的可视化界面以及简单的数据库。

## 二、功能需求+接口设计

### 01 用户管理

- 用户初次登录进行注册操作，设置新密码。
- 用户第二次之后登录，采用密码登录。
- 在用户个人页面可以进行个人信息修改。

|    接口名称    |       接口地址       | 接口类型 | 接口方法 | 接口参数 |
| :------------: | :------------------: | :------: | :------: | :------: |
|  获取用户信息  |  /user/getUserInfo   |   查询   |   GET    |          |
|  获取微信登录  |    /user/wxLogin     |   查询   |   GET    |          |
| 获取手机验证码 |   /user/phoneLogin   |   查询   |   GET    |          |
|  修改用户信息  | /user/updateUserInfo |   修改   |   POST   |          |
|   注册新用户   |   /user/createList   |   添加   |   POST   |          |

### 02 校车管理

- 校车增删改查操作。

|      接口名称      |    接口地址     | 接口类型 | 接口方法 | 接口参数 |
| :----------------: | :-------------: | :------: | :------: | :------: |
|    获取校车列表    | /bus/getBusList |   查询   |   GET    |          |
| 获取指定ID校车信息 | /bus/getBusInfo |   查询   |   GET    |          |
|    添加校车信息    | /bus/createBus  |   添加   |   POST   |          |
|    更新校车信息    | /bus/updateBus  |   修改   |   POST   |          |
|    删除校车信息    | /bus/deleteBus  |   删除   |   POST   |          |

### 03 路线管理

- 路线的增删改查操作。

|      接口名称      |        接口地址         | 接口类型 | 接口方法 | 接口参数 |
| :----------------: | :---------------------: | :------: | :------: | :------: |
|    获取路线列表    |   /route/getRouteList   |   查询   |   GET    |          |
| 获取指定ID路线信息 |   /route/getRouteByID   |   查询   |   GET    |          |
|  获取指定校区路线  | /route/getRouteByCampus |   查询   |   GET    |          |
|    添加路线信息    |   /route/createRoute    |   添加   |   POST   |          |
|    更新路线信息    |   /route/updateRoute    |   修改   |   POST   |          |
|    删除路线信息    |   /route/deleteRoute    |   删除   |   POST   |          |

### 04 排班管理（包括座位）

- 排班的增删改查操作。

|      接口名称      |         接口地址          | 接口类型 | 接口方法 | 接口参数 |
| :----------------: | :-----------------------: | :------: | :------: | :------: |
|    获取排班列表    | /schedule/getScheduleList |   查询   |   GET    |          |
| 获取指定ID排班信息 | /schedule/getScheduleByID |   查询   |   GET    |          |
|    添加排班信息    | /schedule/createSchedule  |   添加   |   POST   |          |
|    更新选座信息    |    /schedule/updateSet    |   修改   |   POST   |          |
|    更新排班信息    | /schedule/updateSchedule  |   修改   |   POST   |          |
|    删除排班信息    | /schedule/deleteSchedule  |   删除   |   POST   |          |

### 05 订单管理

- 订单的增删改查操作。

|       接口名称       |       接口地址        | 接口类型 | 接口方法 | 接口参数 |
| :------------------: | :-------------------: | :------: | :------: | :------: |
|     获取订单列表     |  /order/getOrderList  |   查询   |   GET    |          |
|  获取指定ID订单信息  |  /order/getOrderByID  |   查询   |   GET    |          |
| 获取指定用户订单信息 | /order/getOrderByUser |   查询   |   GET    |          |
|     创建订单信息     |  /order/createOrder   |   添加   |   POST   |          |
|     更新订单信息     |  /order/updateOrder   |   修改   |   POST   |          |
|     取消订单信息     |  /order/deleteOrder   |   删除   |   POST   |          |

### 06 校区管理

- 校区增删查操作。
- 不设计改操作。

|      接口名称      |       接口地址        | 接口类型 | 接口方法 | 接口参数 |
| :----------------: | :-------------------: | :------: | :------: | :------: |
|    获取校区列表    | /campus/getCampusList |   查询   |   GET    |          |
| 获取指定ID校区信息 |  /campus/queryCampus  |   查询   |   GET    |          |
|    创建校区信息    | /campus/createCampus  |   添加   |   POST   |          |
|    删除校区信息    | /campus/deleteCampus  |   删除   |   POST   |          |

## 三、数据库设计

### 01 用户表设计（sys_user）

| 数据项名称 |  类型   | 长度 |    外键    |  注释   |
| :--------: | :-----: | :--: | :--------: | :-----: |
|  user_id   | varchar |  12  |            | 用户ID  |
| user_name  | varchar | 100  |            | 用户名  |
|   avatar   | varchar | 100  |            |  头像   |
|   phone    | varchar |  20  |            |  电话   |
|  work_id   | varchar |  20  |            | 学/工号 |
| campus_id  | varchar |  12  | sys_campus | 校区ID  |

### 02 校区表设计（sys_campus）

| 数据项名称  |  类型   | 长度 | 外键 |   注释   |
| :---------: | :-----: | :--: | :--: | :------: |
|  campus_id  | varchar |  12  |      |  校区ID  |
| campus_name | varchar | 100  |      | 校区名称 |

### 03 校车表设计（sys_bus）

| 数据项名称 |  类型   | 长度 | 外键 |         注释         |
| :--------: | :-----: | :--: | :--: | :------------------: |
|   bus_id   | varchar |  12  |      |        校车ID        |
|  bus_name  | varchar | 100  |      |       校车名称       |
| bus_image  | varchar | 100  |      |       校车图片       |
|  bus_type  | varchar | 100  |      | 显示座位型号（行列） |

### 04 路线表设计（sys_route）

|  数据项名称   |  类型   | 长度 | 外键 |  注释  |
| :-----------: | :-----: | :--: | :--: | :----: |
|   route_id    | varchar |  12  |      | 路线ID |
| start_station | varchar | 255  |      | 始发站 |
|  end_station  | varchar | 255  |      | 终点站 |
| stop_station  | varchar | 255  |      | 经停站 |

### 05 排班表设计（sys_schedule）

|   数据项名称   |  类型   | 长度 |   外键    |   注释   |
| :------------: | :-----: | :--: | :-------: | :------: |
|  schedule_id   | varchar |  12  |           |  排班ID  |
| start_location | varchar | 255  |           | 始发地点 |
|  end_location  | varchar | 255  |           | 结束地点 |
|    route_id    | varchar |  12  | sys_route |  路线ID  |
|   start_time   | varchar |  20  |           | 发车时间 |
|      date      | varchar | 100  |           | 运行日期 |
|     bus_id     | varchar |  12  |  sys_bus  | 车辆的ID |
|   seat_info    |  text   |      |           |  座位ID  |

### 06 订单表设计（sys_order）

|  数据项名称  |  类型   | 长度 |     外键     |   注释   |
| :----------: | :-----: | :--: | :----------: | :------: |
|   order_id   | varchar |  12  |              |  订单ID  |
|   user_id    | varchar |  12  |   sys_user   |  用户ID  |
| schedule_id  | varchar |  12  | sys_schedule |  排班ID  |
|  seat_info   | varchar | 100  |              | 座位信息 |
|  order_time  | varchar | 100  |              | 订单时间 |
| order_status | varchar |  12  |              | 订单状态 |

### 07 管理员表设计（sys_admin）

| 数据项名称 |  类型   | 长度 | 外键 |   注释   |
| :--------: | :-----: | :--: | :--: | :------: |
|  admin_id  | varchar |  12  |      | 管理员ID |
| admin_name | varchar | 100  |      |   名称   |
|  password  | varchar |  20  |      |   密码   |
|   avatar   | varchar | 100  |      |   头像   |

## 四、软件框架设计

### 01 前端页面项目

#### 技术选型

- 使用vue和uniapp进行页面设计
- ajax进行前端接口链接

#### 页面设计

1. **首页**：展示当天的校车信息，可以点击进行选票。
2. **个人主页**：展示个人身份信息，包含个人订单、个人信息卡片、个人信息修改等栏目。
3. **个人信息修改页面**：进行个人身份信息的修改。
4. **个人订单页面**：展示个人订单，并可以取消预定等操作。
5. **选票页面**：从首页进入，可以选择指定座位。
6. **出票页面**：成功选票后进行出票，

### 02 后台管理系统

#### 技术选型

- 使用vue和uniapp进行页面设计
- ajax进行前端接口链接

#### 页面设计

1. **管理员登录页面**：
2. **用户管理页面**：
3. **校车管理页面**：
4. **路线管理页面**：
5. **排班管理页面**：
6. **订单管理页面**：
7. **校区管理页面**：
8. 信息统计界面（暂定）：



### 03 后台接口系统

#### 技术选型

- 使用spring boot v2.3.7.RELEASE
- 使用mybati-plus 



## 五、设计说明

### 01 项目地址

项目地址：[mox-hub/campus_bus_sys: 校车订票系统 (github.com)](https://github.com/mox-hub/campus_bus_sys)

接口文档：https://www.apifox.cn/apidoc/shared-1469efdc-0a67-4ebd-afce-5a0506039129

### 02 参考项目

[mox-hub/database-manager: 数据库管理系统，使用前后端分离技术。前端使用Vue 3 + Element Plus，后端使用Spring Boot + Mybatis-plus，使用mySQL (github.com)](https://github.com/mox-hub/database-manager)

[J1ong/FilmSys: 一个使用Vue全家桶和后台Express框架结合Mysql数据库搭建起来的移动端电影售票和管理系统，实现了热映、即将上映、电影和影院全局搜索、评论、选座、购票、点赞、收藏、订单等一系列购票和管理流程功能 (github.com)](https://github.com/J1ong/FilmSys)