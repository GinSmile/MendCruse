门捷列夫的诅咒
================================


项目简介
-------------------------------
###
    应用名称：门捷列夫的诅咒
    包名：com.teamir.mendcurse
    项目开始时间：2013.7.23
    功能：一款化学元素学习软件，主要有以下功能：
    
            1 元素周期表
            2 查询
            --化学元素查询
            --化学式查询
            --化学方程式查询
            3 计算
            --分子量计算
            4 测试
            5 锁屏功能，用户只有答对题目才能解锁
            

项目功能简图
---------------------------------
![github](http://gdynamic.qpic.cn/gdynamic/cmfdvtpgrtZINibESZor7bYQfQG4cuqibIfWXuuITqapg/628 "features")


项目进度
-----------------------------------
到8月1日，完成基本框架，xml文件作为数据文件，完成元素周期表那个activity。
*+ 大家加油！大家加油！+*


类介绍
###
    /MendCurse/src/com/teamir/mendcurse/AppData.java  存放数据，其中有诸如public Element getElement(int index)之类的方法
    /MendCurse/src/com/teamir/mendcurse/Element.java  Element类，单个元素的各种属性都在里面
    /MendCurse/src/com/teamir/mendcurse/ItemView.java  点击元素周期表的某个元素后弹出的那个Dialog形式的Activity
    /MendCurse/src/com/teamir/mendcurse/MainActivity.java   主界面，包含四个按钮
    /MendCurse/src/com/teamir/mendcurse/MyView.java  自定义的View 一个元素周期表中的方块
    /MendCurse/src/com/teamir/mendcurse/SearchElement.java   速查那个Activity，（四大主菜单之一）
    /MendCurse/src/com/teamir/mendcurse/Game.java	游戏功能
    /MendCurse/src/com/teamir/mendcurse/GameOptionListener.java		游戏Activity的选项监听器
    /MendCurse/src/com/teamir/mendcurse/GameResult.java		显示游戏结果
    
    /MendCurse/src/com/teamir/mendcurse/Table.java  元素周期表那个Activity，（四大主菜单之一）
    /MendCurse/src/com/teamir/mendcurse/Welcome.java  欢迎界面，一张图片而已
    /MendCurse/src/com/teamir/mendcurse/XmlParse.java  Xml处理类，共AppData类调用


test