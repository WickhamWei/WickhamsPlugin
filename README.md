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
 9. 可选血量随着等级而增加。

## 下载地址  

 [发布页面](https://github.com/WickhamWei/WickhamsPlugin/releases)

## API  
  
  1. chunkLoading  
  将指定位置的区块加载指定的秒数，使用方法：new WChunk().chunkLoading(Location chunkLocation, int keepSecond);  
  2. furnaveRecipe  
  将ItemStack作为furnaveRecipe的source，使用方法：new WFurnaceRecipe().furnaveRecipe(String recipeKeyNameString, ItemStack sourceItemStack, ItemStack result​ItemStack,
			float experience, int cookingTimeSecond);  
  3. teleport  
  传送时检查权限，未有权限的玩家在本插件配置文件中指定的秒数延迟后才进行传送，使用方法：new WTeleport().teleport(Player mainPlayer, Player targePlayer); 或者new WTeleport().teleport(Player mainPlayer, Location targeLocation, Boolean recordLocation); 前者自动记录/back，后者可以选择是否记录。  
    
## Event  

  1. WPlayerLoginEvent: 在玩家登陆时召唤
  2. WPlayerRegisterEvent: 在玩家注册时召唤