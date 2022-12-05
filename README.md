# 校车自动订座系统的设计与实现v1.0

>项目目前仍在进行中，以下内容仅供参考

## 一、题目要求

#### 题目要求

类似电影院的订座系统，设计一定的取消功能和预订功能，设计优化的可视化界面以及简单的数据库。

#### 完成页面展示

![登录页面](https://img.moxhub.cn/blog/image-20221205164653941.png)

![路线管理页面](https://img.moxhub.cn/blog/image-20221205164539438.png)

![编辑部分](https://img.moxhub.cn/blog/image-20221205164508048.png)

![image-20221205164947459](https://img.moxhub.cn/blog/image-20221205164947459.png)![image-20221205165119250](https://img.moxhub.cn/blog/image-20221205165119250.png)
![image-20221205165132551](https://img.moxhub.cn/blog/image-20221205165132551.png)![image-20221205165158208](https://img.moxhub.cn/blog/image-20221205165158208.png)



## 二、功能+接口设计

### 01 用户管理（未完成）

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

|     接口名称     |              接口地址               | 接口类型 | 接口方法 | 接口参数 |
| :--------------: | :---------------------------------: | :------: | :------: | :------: |
|   获取排班列表   |      /schedule/getScheduleList      |   查询   |   GET    |          |
| 获取联查排班列表 | /schedule/getScheduleListAssociated |   查询   |   GET    |          |
|   查询排班信息   |       /schedule/querySchedule       |   查询   |   GET    |          |
| 查询联查排班信息 |  /schedule/queryScheduleAssociated  |   查询   |   GET    |          |
|   添加排班信息   |      /schedule/createSchedule       |   添加   |   POST   |          |
|   更新选座信息   |         /schedule/updateSet         |   修改   |   POST   |          |
|   更新排班信息   |      /schedule/updateSchedule       |   修改   |   POST   |          |
|   删除排班信息   |      /schedule/deleteSchedule       |   删除   |   POST   |          |

### 05 订单管理

- 订单的增删改查操作。

|       接口名称       |           接口地址            | 接口类型 | 接口方法 | 接口参数 |
| :------------------: | :---------------------------: | :------: | :------: | :------: |
|     获取订单列表     |      /order/getOrderList      |   查询   |   GET    |          |
|   获取订单联查列表   | /order/getOrderListAssociated |   查询   |   GET    |          |
|     查询订单信息     |       /order/queryOrder       |   查询   |   GET    |          |
|   查询联查订单信息   |  /order/queryOrderAssociated  |   查询   |   GET    |          |
| 获取指定用户订单信息 |     /order/getOrderByUser     |   查询   |   GET    |          |
|     创建订单信息     |      /order/createOrder       |   添加   |   POST   |          |
|     更新订单信息     |      /order/updateOrder       |   修改   |   POST   |          |
|     取消订单信息     |      /order/deleteOrder       |   删除   |   POST   |          |

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
|  campus_id  |   int   |  11  |      |  校区ID  |
| campus_name | varchar | 100  |      | 校区名称 |

### 03 校车表设计（sys_bus）

| 数据项名称  |  类型   | 长度 | 外键 |    注释    |
| :---------: | :-----: | :--: | :--: | :--------: |
|   bus_id    |   int   |  11  |      |   校车ID   |
|  bus_name   | varchar | 255  |      |  校车名称  |
|  bus_image  | varchar | 255  |      |  校车图片  |
|  bus_rows   |   int   |  11  |      | 显示座位行 |
| bus_columns |   int   |  11  |      | 显示座位列 |

### 04 路线表设计（sys_route）

|  数据项名称   |  类型   | 长度 | 外键 |  注释  |
| :-----------: | :-----: | :--: | :--: | :----: |
|   route_id    |   int   |  11  |      | 路线ID |
| start_station | varchar | 255  |      | 始发站 |
|  end_station  | varchar | 255  |      | 终点站 |
| stop_station  | varchar | 255  |      | 经停站 |

### 05 排班表设计（sys_schedule）

|   数据项名称   |  类型   | 长度 |   外键    |   注释   |
| :------------: | :-----: | :--: | :-------: | :------: |
|  schedule_id   |   int   |  11  |           |  排班ID  |
| start_location | varchar | 255  |           | 始发地点 |
|  end_location  | varchar | 255  |           | 结束地点 |
|    route_id    |   int   |  11  | sys_route |  路线ID  |
|   start_time   | varchar |  20  |           | 发车时间 |
|      date      | varchar | 100  |           | 运行日期 |
|     bus_id     |   int   |  11  |  sys_bus  | 车辆的ID |
|   seat_info    |  text   |      |           |  座位ID  |

### 06 订单表设计（sys_order）

|  数据项名称  |  类型   | 长度 |     外键     |   注释   |
| :----------: | :-----: | :--: | :----------: | :------: |
|   order_id   | bigint  |  19  |              |  订单ID  |
|   user_id    |   int   |  11  |   sys_user   |  用户ID  |
| schedule_id  |   int   |  11  | sys_schedule |  排班ID  |
|  seat_info   | varchar |  20  |              | 座位信息 |
|  order_time  | varchar | 255  |              | 订单时间 |
| order_status | varchar | 255  |              | 订单状态 |

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

- 使用Vue2和Uniapp进行页面设计，使用了ThorUI组件。
- Uniapp的request进行前端接口链接。

#### 页面设计

1. **首页**：展示当天的校车信息，可以点击进行选票。
2. **个人主页**（未完成）：展示个人身份信息，包含个人订单、个人信息卡片、个人信息修改等栏目。
3. **个人信息修改页面**（未完成）：进行个人身份信息的修改。
4. **个人订单页面**：展示个人订单，并可以取消预定等操作。
5. **选票页面**：从首页进入，可以选择指定座位。
6. **出票页面**：成功选票后进行出票，

### 02 后台管理系统

#### 技术选型

- 使用Vue3进行页面设计
- ajax进行前端接口链接

#### 页面设计

1. **管理员登录页面**：进行管理员登录（目前静态）。==用户名：admin，密码：admin==
2. **用户管理页面**：进行用户的增删改查操作。
3. **校车管理页面**：进行校车的增删改查操作。
4. **路线管理页面**：进行路线的增删改查操作。
5. **排班管理页面**：进行排班的增删改查操作。
6. **订单管理页面**：进行订单的增删改查操作。
7. **校区管理页面**：进行校区的增删改查操作。
8. 信息统计界面（暂定）：目前未制作
9. 首页：目前仍未制作

### 03 后台接口系统

#### 技术选型

- 使用spring boot v2.3.7.RELEASE
- 使用mybati-plus v3.4.2
- 采用MVC框架进行设计

#### 接口设计

- 参考接口文档：https://www.apifox.cn/apidoc/shared-1469efdc-0a67-4ebd-afce-5a0506039129

## 五、设计说明

### 01 项目地址

项目地址：[mox-hub/campus_bus_sys: 校车订票系统 (github.com)](https://github.com/mox-hub/campus_bus_sys)

apifox接口文档：https://www.apifox.cn/apidoc/shared-1469efdc-0a67-4ebd-afce-5a0506039129

### 02 参考项目

[mox-hub/database-manager: 数据库管理系统，使用前后端分离技术。前端使用Vue 3 + Element Plus，后端使用Spring Boot + Mybatis-plus，使用mySQL (github.com)](https://github.com/mox-hub/database-manager)

[J1ong/FilmSys: 一个使用Vue全家桶和后台Express框架结合Mysql数据库搭建起来的移动端电影售票和管理系统，实现了热映、即将上映、电影和影院全局搜索、评论、选座、购票、点赞、收藏、订单等一系列购票和管理流程功能 (github.com)](https://github.com/J1ong/FilmSys)

### 03下一步更新计划

- 可能时间在23年寒假，补全未完成的功能，设计登录、后台数据展示等。
- 现在的项目排班存在只有一周的排班，无法循环的问题，之后会解决。
- 现在的订票展示页面是静态的已订座情况，之后会更新。