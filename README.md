# WickhamsPlugin
## 插件功能

 1. **登录系统** 使用join命令注册及登录。（**支持正版从你我做起**）
 2. 自动保存地图系统，可配置的保存时间间隔。
 3. 自动公告系统，简陋的自动定时循环公告。
 4. 加入游戏时的自定义通知，简陋的一句话加入通知。~~例：这个服务器吊炸天~~
 5. 保护耕地系统，防止**人为**踩坏耕地。
 6. 死亡是否保留背包内的物品开关，**统一设置，所有世界都生效。**
 7. OP指令：tp、tpall、gm。
 8. 玩家指令：tpa、tpayes、spawn、back、home、sethome。

## config.yml说明

* 登录系统
1. 登陆系统 false  
* 自动保存
1. 自动保存地图 true
2. 自动保存地图时间间隔，单位分钟 10
* 自动公告
1. 自动公告 false
2. 自动公告时间间隔，单位分钟 1
3. 公告1 _欢迎来到Wickham的服务器_
* 其他信息
1. 玩家加入时给玩家的信息开关 false
2. 玩家加入时给玩家的信息 _欢迎来到Wickham的服务器_
3. 玩家加入时的插件介绍 true   
//向玩家介绍本插件及插件版本，建议打开
* 玩家死亡
1. 死亡是否保留背包内的物品 false
2. 死亡后保留一半等级 false   
//默认不保留任何经验
* 玩家升级
1. 三十级后每升级一级加血量上限 false
2. 最大血量上限，一颗心为两个血 40	  
//低于20将设为20
* 地块保护
1. 保护耕地 true   
//只能防止人祸，不能防止天灾
* 传送系统
1. 非OP传送等待时间（秒） 3
2. tpa请求等待时间（秒） 20


## 下载地址
 [发布页面](https://github.com/WickhamWei/WickhamsPlugin/releases)
