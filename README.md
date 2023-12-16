# Final

Final

芝士大作业

**1.在写新的代码之前，请按照OOP的注释要求事先配置好注释模板!**

**2.在写新的代码之前，先明确自己写的内容，并且了解已经实现的方法和类，不要重复！**

3.为了降低类与类之间的耦合，非必要情况下，请将自己要实现的功能写成对应的类，并且放在相应的软件包下，并添加相应的异常抛出

4.对各个软件包的解释：

Clients，代表所有和客户相关的内容；

Servers：代表所有和服务器相关的内容；

Exceptions：代表所有自定义的异常；

Judges：代表各种判断类；

Lock：线程通信中可能用到的锁，目前只有服务器的锁；

Orders：所有用户或者服务器可以直接进行互动的类（添加监听者）；

Tool：底层实现类，用户和服务器都无法直接调用。

5.分工：

赵龙淳：负责整体结构的搭建、服务器端的实现、多线程通信的基础

杨天佑：负责实现GUI，直观清晰地反应程序功能

张育铨：主要负责实现客户端的事务

彭徵铭：负责处理和实现数据库的板块