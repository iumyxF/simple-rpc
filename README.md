# simple-rpc
从0实现rpc

# 版本迭代

## version001
使用socket通过网络发起一次远程调用

## version002
针对客户端的优化: 
1. 规范rpc请求和响应
2. 使用动态代理封装请求对象

## version 003
针对服务端的优化:
1. 增加简易注册中心，解耦服务的注册和调用
2. 实现WorkThread，负责处理socket连接和业务处理
3. 抽象化服务端，增加线程池实现的服务端